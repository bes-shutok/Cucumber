package nicebank;

public class Account {
    private Money balance = new Money();
    public void credit(Money amount) {balance=balance.add(amount);}
    public void debit(Money amount) throws NotEnoughMoney {
        if (balance.lessThan(amount)) {
            throw new NotEnoughMoney();
        }
        balance=balance.minus(amount);
    }
    public Money getBallance(){return balance;}
}
