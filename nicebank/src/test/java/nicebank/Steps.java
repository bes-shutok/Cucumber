package nicebank;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.en.*;
import nicebank.Money;
import org.testng.Assert;
import transforms.MoneyConverter;

public class Steps {
    class Teller  {
        public void withdrawFrom(Account account, Money amount) {
            account.withdraw(amount);
        }
    }
    class CashSlot {
        public Money getContents() {
            return new Money(0,0);
        }
    }
    class Account {
        private Money balance = new Money();
        public void deposit(Money amount) {balance=balance.add(amount);}
        public void withdraw(Money amount) {balance=balance.minus(amount);}
        public Money getBallance(){return balance;}
    }
    class KnowsTheDomain {
        private Account myAccount;
        private CashSlot cashSlot;

        public Account getMyAccount() {
            if (myAccount == null) {myAccount = new Account();}
            return myAccount;
        }

        public CashSlot getCashSlot() {
            if (cashSlot == null) {cashSlot = new CashSlot();}
            return cashSlot;
        }
    }
    KnowsTheDomain helper;
    public Steps() {
        helper = new KnowsTheDomain();
    }

    @Given("^I have deposited (\\$\\d+\\.\\d+) in my account$")
    public void iHaveDeposited$InMyAccount(@Transform(MoneyConverter.class) Money amount) throws Throwable {
        helper.getMyAccount().deposit(amount);
        Assert.assertEquals(helper.getMyAccount().getBallance(),amount,"Incorrect account balance - ");
    }

    @When("^I withdraw (\\$\\d+\\.\\d+)$")
    public void iWithdraw$(@Transform(MoneyConverter.class) Money amount) throws Throwable {
        Teller teller = new Teller();
        teller.withdrawFrom(helper.getMyAccount(), amount);
    }

    @Then("^(\\$\\d+\\.\\d+) should be dispensed$")
    public void $ShouldBeDispensed(@Transform(MoneyConverter.class) Money amount) throws Throwable {
        Assert.assertEquals(helper.getCashSlot().getContents(),amount,"Incorrect account balance - ");
    }
}
