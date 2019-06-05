package support;

import nicebank.CashSlot;
import org.apache.log4j.Logger;

public class KnowsTheCashSlot {
    private CashSlot cashSlot;
    private static final Logger logger = Logger.getLogger(CashSlot.class);
    public CashSlot getCashSlot() {
        if (cashSlot == null) {
            logger.info("Initializing CashSlot");
            cashSlot = new CashSlot();}
        logger.info("Returning CashSlot");
        return cashSlot;
    }
}
