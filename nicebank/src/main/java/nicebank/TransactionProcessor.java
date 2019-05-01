package nicebank;

import org.apache.log4j.Logger;

public class TransactionProcessor implements Runnable {
    private  TransactionQueue queue = new TransactionQueue();
    private static final Logger Log = Logger.getLogger(TransactionProcessor.class);
    public void run() {
        do {
            String message = queue.read();
/*            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }*/
            if (message.length() > 0) {
                Log.info("Message from queue: " + message);
                Money balance = BalanceStore.getBalance();
                Money transactionAmount = new Money(message);
                if (isCreditTransaction(message)){
                    BalanceStore.setBalance(balance.add(transactionAmount));
                } else {
                    BalanceStore.setBalance(balance.minus(transactionAmount));
                }
            }
        } while (!Thread.currentThread().isInterrupted());
    }
    private boolean isCreditTransaction(String message) {
        return !message.startsWith("-");
    }
}
