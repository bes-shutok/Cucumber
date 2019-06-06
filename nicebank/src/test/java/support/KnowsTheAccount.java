package support;

import nicebank.Account;
import org.apache.log4j.Logger;

public class KnowsTheAccount {
    private Account myAccount;
    private static final Logger logger = Logger.getLogger(KnowsTheAccount.class);
    public KnowsTheAccount() {}
    public Account getMyAccount() {
        if (myAccount == null) {
            logger.info("Initializing Account");
            myAccount = new Account(1234);
            myAccount.saveIt();
        }
        logger.info("Returning Account");
        return myAccount;
    }
}
