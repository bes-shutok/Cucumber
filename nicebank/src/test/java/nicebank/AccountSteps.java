package nicebank;

import cucumber.api.Transform;
import cucumber.api.java.en.*;
import org.testng.*;
import support.KnowsTheDomain;
import transforms.MoneyConverter;

public class AccountSteps {
    private KnowsTheDomain helper;
    public AccountSteps(KnowsTheDomain helper) {
        this.helper = helper;
    }

    @Given("^my account has been credited with (\\$\\d+\\.\\d+)$")
    public void iHaveDeposited$InMyAccount(@Transform(MoneyConverter.class) Money amount) {
        helper.getMyAccount().credit(amount);
    }
    @Then("^the balance of my account should be (\\$\\d+\\.\\d+)")
    public void iShouldHaveLeftWith$InMyAccount(@Transform(MoneyConverter.class) Money amount) throws Throwable {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) { e.printStackTrace();
        }
        Assert.assertEquals(helper.getMyAccount().getBallance(),amount,"Incorrect account balance - ");
    }
}
