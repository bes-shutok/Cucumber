package nicebank;

import org.apache.log4j.Logger;

public class TransactionProcessor {
    private  TransactionQueue queue = new TransactionQueue();
    private static final Logger logger = Logger.getLogger(TransactionProcessor.class);
    public void start() throws NotEnoughMoney  {
        do {
            String message = queue.read();
            // Simulating flickering scenario
            try {Thread.sleep(1000);} catch (InterruptedException e) {
                logger.debug(e.getMessage());
            }
            if (message.length() > 0) {
                logger.info("Message from queue: " + message);
                Money balance = BalanceStore.getBalance();
                Money transactionAmount = new Money(message);
                if (isCreditTransaction(message)){
                    BalanceStore.setBalance(balance.add(transactionAmount));
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
                    BalanceStore.setBalance(balance.minus(transactionAmount));
                }
            }
        } while (!Thread.currentThread().isInterrupted());
    }
    private boolean isCreditTransaction(String message) {
        return !message.startsWith("-");
    }
}
