package nicebank;


import org.javalite.activejdbc.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactionProcessor {
    private  TransactionQueue queue = new TransactionQueue();
    private static final Logger logger = LoggerFactory.getLogger(TransactionProcessor.class);
    public void start() throws NotEnoughMoney  {
        do {
            if (!Base.hasConnection()){
                logger.info("Trying to establish connection in TransactionProcessor");
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
                    balance=balance.add(transactionAmount);
                    logger.info("Is a credit transaction. New balance will be set to: " + balance.toString());
                    account.setBalance(balance);
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
                    balance=balance.minus(transactionAmount);
                    logger.info("Is a debit transaction. New balance will be set to: " + balance.toString());
                    account.setBalance(balance);
                }
            }
        } while (!Thread.currentThread().isInterrupted());
    }
    private boolean isCreditTransaction(String message) {
        return !message.startsWith("-");
    }
}
