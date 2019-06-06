package hooks;

import cucumber.api.java.Before;
import nicebank.Account;
import nicebank.TransactionQueue;
import org.apache.log4j.Logger;
import org.javalite.activejdbc.Base;

public class ResetHooks {
    private static final Logger logger = Logger.getLogger(ResetHooks.class);
    @Before(order = 1)
    public void reset() {
        logger.info("Initializing DB connection");
        if (!Base.hasConnection()){
            Base.open(
                    "com.mysql.cj.jdbc.Driver",
                    "jdbc:mysql://localhost/bank?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "teller", "password");
        }

        int result = Account.deleteAll();
        logger.info("Number of records deleted: " + result);
        TransactionQueue.clear();
    }

}