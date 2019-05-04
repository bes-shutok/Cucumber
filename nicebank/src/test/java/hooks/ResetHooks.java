package hooks;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import nicebank.TransactionQueue;
import org.javalite.activejdbc.Base;

public class ResetHooks {
    @Before
    public void reset() {
        TransactionQueue.clear();
    }

    @After
    public void rollback() {
        Base.rollbackTransaction();
    }
}