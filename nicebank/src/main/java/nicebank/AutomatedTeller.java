package nicebank;

public class AutomatedTeller implements Teller {
    private CashSlot cashSlot;
    public AutomatedTeller(CashSlot cashSlot) {this.cashSlot=cashSlot;}
    public void withdrawFrom(Account account, Money amount) {
        try {
            account.debit(amount);
        } catch (Exception e) {
            e.printStackTrace();
            cashSlot.dispense(new Money("0.00"));
            return;
        }
        cashSlot.dispense(amount);
    }
}
