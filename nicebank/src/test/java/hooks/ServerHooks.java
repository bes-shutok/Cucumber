package hooks;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import nicebank.AtmServer;
import support.KnowsTheAccount;
import support.KnowsTheCashSlot;

public class ServerHooks {
    public static final int PORT = 8887;

    private AtmServer server;
    private KnowsTheCashSlot cashSlotHelper;
    private KnowsTheAccount accountHelper;

    public ServerHooks (KnowsTheCashSlot cashSlotHelper, KnowsTheAccount accountHelper) {
        this.cashSlotHelper = cashSlotHelper;
        this.accountHelper=accountHelper;
    }

    @Before
    public void startServer() throws Exception {
        server = new AtmServer(PORT, cashSlotHelper.getCashSlot(), accountHelper.getMyAccount());
        server.start();
    }

    @After
    public void stopServer() throws Exception {
        server.stop();
    }
}
