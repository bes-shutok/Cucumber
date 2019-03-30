package nicebank;

public class Account {
    private Money balance = new Money();
    public void deposit(Money amount) {balance=balance.add(amount);}
    public void withdraw(Money amount) {balance=balance.minus(amount);}
    public Money getBallance(){return balance;}
}
