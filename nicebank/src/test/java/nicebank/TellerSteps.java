package nicebank;

import cucumber.api.Transform;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import support.KnowsTheDomain;
import transforms.MoneyConverter;

public class TellerSteps {
    private KnowsTheDomain helper;
    private boolean overDraw;
    public TellerSteps(KnowsTheDomain helper) {
        this.helper = helper;
    }

    @When("^I withdraw (\\$\\d+\\.\\d+)$")
    public void iWithdraw$(@Transform(MoneyConverter.class) Money amount) {
        overDraw=helper.getTeller().withdrawFrom(helper.getMyAccount(), amount);
    }

    @Then("^I should be told that I have insufficient funds in my account$")
    public void iShouldBeToldThatIHaveInsufficientFundsInMyAccount() {
        Assert.assertTrue(overDraw, "Insufficient funds check failure");
    }
}
