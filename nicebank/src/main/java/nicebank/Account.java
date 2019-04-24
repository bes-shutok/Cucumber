package nicebank;

public class Account {
    private TransactionQueue queue =  new TransactionQueue();

    public void credit(Money amount) {
       queue.write("+" + amount.toString());
    }
    public void debit(Money amount) throws NotEnoughMoney {
        if (getBallance().lessThan(amount)) {
            throw new NotEnoughMoney();
        }
        queue.write("-" + amount.toString());
    }
    public Money getBallance(){
        return BalanceStore.getBalance();
    }
}
