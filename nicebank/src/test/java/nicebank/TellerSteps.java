package nicebank;

import cucumber.api.Transform;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import support.KnowsTheDomain;
import support.TellerWithState;
import transforms.MoneyConverter;

public class TellerSteps {
    private KnowsTheDomain helper;
    public TellerSteps(KnowsTheDomain helper) {
        this.helper = helper;
    }

    @When("^I check my balance$")
    public void iCheckMyBalance() {
        helper.getTeller().displayBalance();
        Assert.assertEquals(helper.getTeller().checkState(), TellerWithState.Status.DISPLAY, "Display balance check failure");
    }

    @Then("^I should see that my balance is (\\$\\d+\\.\\d+)$")
    public void iShouldSeeThatMyBalanceIs$(@Transform(MoneyConverter.class) Money amount) {
        Assert.assertEquals(helper.getMyAccount().getBallance(),amount,"Incorrect account balance - ");
        Assert.assertEquals(helper.getTeller().checkState(), TellerWithState.Status.DISPLAY, "Display balance check failure");
    }

    @When("^I withdraw (\\$\\d+\\.\\d+)$")
    public void iWithdraw$(@Transform(MoneyConverter.class) Money amount) {
        helper.getTeller().withdrawFrom(helper.getMyAccount(), amount);
    }

    @Then("^I should be told that I have insufficient funds in my account$")
    public void iShouldBeToldThatIHaveInsufficientFundsInMyAccount() {
        Assert.assertEquals(helper.getTeller().checkState(), TellerWithState.Status.OVERDRAW, "Insufficient funds check failure");
    }
}
