package nicebank;

public class AutomatedTeller implements Teller {
    private CashSlot cashSlot;
    public AutomatedTeller(CashSlot cashSlot) {this.cashSlot=cashSlot;}
    public boolean withdrawFrom(Account account, Money amount) {
        cashSlot.dispense(new Money("$0.00"));
        try {
            account.debit(amount);
        } catch (NotEnoughMoney notEnoughMoney) {
            // notEnoughMoney.printStackTrace();
            return false;
        }
        cashSlot.dispense(amount);
        return true;
    }
}
