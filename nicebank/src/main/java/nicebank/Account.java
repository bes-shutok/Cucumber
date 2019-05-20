package nicebank;

import org.apache.log4j.Logger;
import org.javalite.activejdbc.Model;

public class Account extends Model {
    private static final Logger logger = Logger.getLogger(Account.class);
    private TransactionQueue queue =  new TransactionQueue();

    public Account() {}

    public Account(int number){
        setInteger("number", number);
        setString("balance", "0.00");
    }

    public void credit(Money amount) {
        queue.write("+" + amount.toString() + ", " + getNumber());
    }
    public void debit(Money amount) throws NotEnoughMoney {
        // We need to open connection to DB in WithdrawalServlet if we want to do that in Account class
        if (getBalance().lessThan(amount)) {
            throw new NotEnoughMoney();
        }
        queue.write("-" + amount.toString() + ", " + getNumber());
    }
    public int getNumber() {
        return getInteger("number");
    }

    public Money getBalance() {
        refresh();
        return new Money(getString("balance"));
    }

    public void setBalance(Money amount) {
        logger.info("New balance: " + amount.toString());
        setString("balance", amount.toString().substring(1));
        saveIt();
    }
}
