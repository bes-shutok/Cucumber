package nicebank;

import cucumber.api.Transform;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import support.KnowsTheAccount;
import support.KnowsTheTeller;
import support.TellerWithState;
import transforms.MoneyConverter;

public class TellerSteps {
    private KnowsTheTeller tellerHelper;
    private KnowsTheAccount accountHelper;
    public TellerSteps(KnowsTheTeller tellerHelper, KnowsTheAccount accountHelper) {
        this.tellerHelper = tellerHelper;
        this.accountHelper = accountHelper;
    }

    @When("^I check my balance$")
    public void iCheckMyBalance() {
        tellerHelper.getTeller().displayBalance();
        Assert.assertEquals(tellerHelper.getTeller().checkState(), TellerWithState.Status.DISPLAY, "Display balance check failure");
    }

    @Then("^I should see that my balance is (\\$\\d+\\.\\d+)$")
    public void iShouldSeeThatMyBalanceIs$(@Transform(MoneyConverter.class) Money amount) {
        Assert.assertEquals(accountHelper.getMyAccount().getBalance(),amount,"Incorrect account balance - ");
        Assert.assertEquals(tellerHelper.getTeller().checkState(), TellerWithState.Status.DISPLAY, "Display balance check failure");
    }

    @When("^I withdraw (\\$\\d+\\.\\d+)$")
    public void iWithdraw$(@Transform(MoneyConverter.class) Money amount) {
        tellerHelper.getTeller().withdrawFrom(accountHelper.getMyAccount(), amount);
    }

    @Then("^I should be told that I have insufficient funds in my account$")
    public void iShouldBeToldThatIHaveInsufficientFundsInMyAccount() {
        Assert.assertEquals(tellerHelper.getTeller().checkState(), TellerWithState.Status.OVERDRAW, "Insufficient funds check failure");
    }
}
