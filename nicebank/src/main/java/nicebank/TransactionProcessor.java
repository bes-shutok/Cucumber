package nicebank;

import org.apache.log4j.Logger;
import org.javalite.activejdbc.Base;

public class TransactionProcessor {
    private  TransactionQueue queue = new TransactionQueue();
    private static final Logger logger = Logger.getLogger(TransactionProcessor.class);
    public void start() throws NotEnoughMoney  {
        do {
            if (!Base.hasConnection()){
                Base.open(
                        "com.mysql.cj.jdbc.Driver",
                        "jdbc:mysql://localhost/bank?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "teller", "password");
            }
            String message = queue.read();
/*            // Simulating flickering scenario
            try {Thread.sleep(2000);} catch (InterruptedException e) {
                logger.debug(e.getMessage());
            }*/
            if (message.length() > 0) {
                logger.info("Message from queue: " + message);
                String[] parts = message.split(",");
                Account account = Account.findFirst("number = ?", parts[1]);
                if (account == null) {
                    throw new RuntimeException("Account number not found: " + parts[1]);
                }
                Money transactionAmount = new Money(parts[0]);

                Money balance = account.getBalance();
                logger.info("Balance: " + balance.toString());
                if (isCreditTransaction(message)){
                    account.setBalance(balance.add(transactionAmount));
                } else {
                    // Possible Race Condition?
                    if (balance.lessThan(transactionAmount)){
                        Thread.currentThread().interrupt();
                        String errorMessage="Negative balance is not allowed! Possible Race Condition?" +
                                "\nFailed while trying to deduct " + transactionAmount.toString() +
                                "\nwhen balance = " + balance.toString();
                        logger.error(errorMessage);
                        throw new NotEnoughMoney(errorMessage);
                    }
                    account.setBalance(balance.minus(transactionAmount));
                }
            }
        } while (!Thread.currentThread().isInterrupted());
    }
    private boolean isCreditTransaction(String message) {
        return !message.startsWith("-");
    }
}
