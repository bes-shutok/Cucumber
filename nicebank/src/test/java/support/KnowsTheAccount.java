package support;

import nicebank.Account;
import org.apache.log4j.Logger;
import org.javalite.activejdbc.Base;

public class KnowsTheAccount {
    private Account myAccount;
    private static final Logger logger = Logger.getLogger(KnowsTheAccount.class);
    public KnowsTheAccount() {
        logger.info("KnowsTheAccount initializing DB connection");
        if (!Base.hasConnection()){
            Base.open(
                    "com.mysql.cj.jdbc.Driver",
                    "jdbc:mysql://localhost/bank?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "teller", "password");
        }

        int result = Account.deleteAll();
        logger.info("Number of records deleted: " + result);
    }
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
