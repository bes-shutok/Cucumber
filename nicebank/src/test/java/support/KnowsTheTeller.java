package support;

import org.apache.log4j.Logger;

public class KnowsTheTeller {
    private TellerWithState teller;
    private MyWebDriver webDriver;
    private static final Logger logger = Logger.getLogger(KnowsTheTeller.class);
    public KnowsTheTeller(MyWebDriver webDriver) {
        this.webDriver=webDriver;
    }
    public TellerWithState getTeller() {
        if (teller == null) {
            logger.info("Initializing TellerWithState");
            teller = new AtmUserInterface(webDriver);
        }
        logger.info("Returning TellerWithState");
        return teller;
    }
}
