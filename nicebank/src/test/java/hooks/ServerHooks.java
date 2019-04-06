package hooks;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import nicebank.AtmServer;

public class ServerHooks {
    public static final int PORT = 8887;
    private AtmServer server;
    @Before
    public void startServer() throws Exception {
        server = new AtmServer(PORT);
        server.start();
    }

    @After
    public void stopServer() throws Exception {
        server.stop();
    }
}
