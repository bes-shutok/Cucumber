package nicebank;

public class CashSlot {
    private Money contents;

    public Money getContents() {return contents;}
    // public Money getContents() {return new Money(0,0);}
    public void dispense(Money amount) {contents=amount;}
}
