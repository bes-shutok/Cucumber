package support;

import nicebank.Account;
import nicebank.CashSlot;
import nicebank.Teller;

public class KnowsTheDomain {
    private Account myAccount;
    private CashSlot cashSlot;
    private Teller teller;

    public Teller getTeller() {
        if (teller == null) {teller = new Teller(getCashSlot());}
        return teller;
    }

    public Account getMyAccount() {
        if (myAccount == null) {myAccount = new Account();}
        return myAccount;
    }

    public CashSlot getCashSlot() {
        if (cashSlot == null) {cashSlot = new CashSlot();}
        return cashSlot;
    }
}
