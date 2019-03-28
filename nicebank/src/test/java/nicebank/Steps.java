package nicebank;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.en.*;
import nicebank.Money;
import org.testng.Assert;
import transforms.MoneyConverter;

public class Steps {
    class Account {
        private Money balance = new Money();
        public void deposit(Money amount) {balance=balance.add(amount);}
        public Money getBallance(){return balance;}
    }
    @Given("^I have deposited (\\$\\d+\\.\\d+) in my account$")
    public void iHaveDeposited$InMyAccount(@Transform(MoneyConverter.class) Money amount) throws Throwable {
        Account myAccount = new Account();
        // Money amount = new Money(dollars, cents);
        myAccount.deposit(amount);
        Assert.assertEquals(myAccount.getBallance(),amount,"Incorrect account balance - ");
    }

    @When("^I request \\$(\\d+)$")
    public void iRequest$(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^\\$(\\d+) should be dispensed$")
    public void $ShouldBeDispensed(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
