package support;

import nicebank.Account;
import nicebank.Money;
import nicebank.Teller;

public interface TellerWithState extends Teller {
    Status checkState();
    boolean withdrawFrom(Account account, Money amount);

    void displayBalance();

    enum Status {
        DISPLAY,
        OVERDRAW,
        WITHDRAW
    }
}
