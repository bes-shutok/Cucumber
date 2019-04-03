package nicebank;

import cucumber.api.Transform;
import cucumber.api.java.en.*;
import org.testng.*;
import support.KnowsTheDomain;
import transforms.MoneyConverter;

public class AccountSteps {
    KnowsTheDomain helper;
    public AccountSteps(KnowsTheDomain helper) {
        this.helper = helper;
    }
    @Given("^my account has been credited with (\\$\\d+\\.\\d+)$")
    public void iHaveDeposited$InMyAccount(@Transform(MoneyConverter.class) Money amount) throws Throwable {
        helper.getMyAccount().credit(amount);
    }
    @Then("^I should have left with (\\$\\d+\\.\\d+) in my account$")
    public void iShouldHaveLeftWith$InMyAccount(@Transform(MoneyConverter.class) Money amount) {
        Assert.assertEquals(helper.getMyAccount().getBallance(),amount,"Incorrect account balance - ");
    }
}
