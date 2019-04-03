package nicebank;

public class Account {
    private Money balance = new Money();
    public void credit(Money amount) {balance=balance.add(amount);}
    public void debit(Money amount) {
        // if (amount > balance) {throw an exception}
        balance=balance.minus(amount);
    }
    public Money getBallance(){return balance;}
}
