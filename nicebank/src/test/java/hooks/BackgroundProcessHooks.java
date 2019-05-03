package hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import nicebank.NotEnoughMoney;
import nicebank.TransactionProcessor;
public class BackgroundProcessHooks {
    public static boolean wantsToQuit = false;
    public static String errorMessage="No error message";
    private Thread transactionProcessorThread;
    @Before
    public void startBackgroundThread(final Scenario scenario) {
        transactionProcessorThread = new Thread() {
            public void run() {
                TransactionProcessor processor = new TransactionProcessor();
                try {
                    processor.start();
                } catch (NotEnoughMoney notEnoughMoney) {
                    notEnoughMoney.printStackTrace();
                    wantsToQuit=true;
                    errorMessage=notEnoughMoney.getMessage() + "\n\rIn the scenario: " + scenario.getName()
                            + "\n\rIn the class: " + scenario.getClass();
                    stopBackgroundThread();
                }
            }
        };
        transactionProcessorThread.start();
    }

    @After
    public void stopBackgroundThread() {
        transactionProcessorThread.interrupt();
    }
}
