package nicebank;

public class Teller {
    private CashSlot cashSlot;
    public Teller(CashSlot cashSlot) {this.cashSlot=cashSlot;}
    public void withdrawFrom(Account account, Money amount) {
        account.debit(amount);
        cashSlot.dispense(amount);
    }
}
