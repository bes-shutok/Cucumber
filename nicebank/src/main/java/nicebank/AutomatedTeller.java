package nicebank;

public class AutomatedTeller implements Teller {
    private CashSlot cashSlot;
    public AutomatedTeller(CashSlot cashSlot) {this.cashSlot=cashSlot;}
    public void withdrawFrom(Account account, Money amount) {
        account.debit(amount);
        cashSlot.dispense(amount);
    }
}
