package hooks;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import nicebank.AtmServer;
import nicebank.CashSlot;
import support.KnowsTheAccount;

public class ServerHooks {
    public static final int PORT = 8887;

    private AtmServer server;
    private CashSlot cashSlot;
    private KnowsTheAccount accountHelper;

    public ServerHooks (CashSlot cashSlot, KnowsTheAccount accountHelper) {
        this.cashSlot = cashSlot;
        this.accountHelper=accountHelper;
    }

    @Before
    public void startServer() throws Exception {
        server = new AtmServer(PORT, cashSlot, accountHelper.getMyAccount());
        server.start();
    }

    @After
    public void stopServer() throws Exception {
        server.stop();
    }
}
