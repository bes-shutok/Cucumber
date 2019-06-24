package support;

import nicebank.Account;
import org.apache.log4j.Logger;

public class AccountFactory {
    private static final Logger logger = Logger.getLogger(AccountFactory.class);
    public static Account createTestAccount() {
        logger.info("Initializing Account");
        Account account = new Account(1234);
        account.saveIt();
        logger.info("Returning Account");
        return account;
    }
}
