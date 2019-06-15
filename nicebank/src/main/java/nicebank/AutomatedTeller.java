package nicebank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutomatedTeller implements Teller {
    private CashSlot cashSlot;
    private static final Logger logger = LoggerFactory.getLogger(AutomatedTeller.class);

    public AutomatedTeller(CashSlot cashSlot) {this.cashSlot=cashSlot;}
    public boolean withdrawFrom(Account account, Money amount) {
        cashSlot.dispense(new Money("$0.00"));
        try {
            account.debit(amount);
        } catch (NotEnoughMoney e) {
            logger.info("NotEnoughMoney exception: " + e.toString());
            return false;
        }
        logger.info("Going to dispense: " + amount.toString());
        cashSlot.dispense(amount);
        return true;
    }
}
