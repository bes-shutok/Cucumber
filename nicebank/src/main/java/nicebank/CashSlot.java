package nicebank;

public class CashSlot {
    private Money contents;

    public Money getContents() {return contents;}
    public void dispense(Money amount) {contents=amount;}
}
