package hooks;

import cucumber.api.java.Before;
import nicebank.TransactionQueue;

public class ResetHooks {
    @Before
    public void reset() {
        TransactionQueue.clear();
    }

}