package hooks;

import cucumber.api.java.Before;
import nicebank.TransactionQueue;
import nicebank.BalanceStore;
public class ResetHooks {
    @Before
    public void reset() {
        TransactionQueue.clear();
        BalanceStore.clear();
    }
}