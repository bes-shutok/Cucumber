package nicebank;

import cucumber.api.*;
import cucumber.api.java.en.*;
import nicebank.Money;
import org.testng.Assert;
import support.KnowsTheDomain;
import transforms.MoneyConverter;

public class Steps {
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
        helper.getTeller().withdrawFrom(helper.getMyAccount(), amount);
    }

    @Then("^(\\$\\d+\\.\\d+) should be dispensed$")
    public void $ShouldBeDispensed(@Transform(MoneyConverter.class) Money amount) throws Throwable {
        Assert.assertEquals(helper.getCashSlot().getContents(),amount,"Incorrect account balance - ");
    }
}
