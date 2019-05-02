package nicebank;

import org.apache.log4j.Logger;

public class TransactionProcessor {
    private  TransactionQueue queue = new TransactionQueue();
    private static final Logger logger = Logger.getLogger(TransactionProcessor.class);
    public void start() {
        do {
            String message = queue.read();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//            }
            if (message.length() > 0) {
                logger.info("Message from queue: " + message);
                Money balance = BalanceStore.getBalance();
                Money transactionAmount = new Money(message);
                if (isCreditTransaction(message)){
                    BalanceStore.setBalance(balance.add(transactionAmount));
                } else {
                    if (balance.lessThan(transactionAmount)){
                        //ideally we should fail scenario here, but for now we just throw exception and interrupt
                        // the thread. Unfortunately new thread will continue.
                        Thread.currentThread().interrupt();
                        throw new RuntimeException("Negative balance is not allowed! \n\rTerminating the thread!");
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
