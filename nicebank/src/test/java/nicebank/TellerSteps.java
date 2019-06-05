package nicebank;

import cucumber.api.Transform;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import support.AtmUserInterface;
import support.KnowsTheAccount;
import support.TellerWithState;
import transforms.MoneyConverter;

public class TellerSteps {
    private TellerWithState teller;
    private KnowsTheAccount accountHelper;
    public TellerSteps(AtmUserInterface teller, KnowsTheAccount accountHelper) {
        this.teller = teller;
        this.accountHelper = accountHelper;
    }

    @When("^I check my balance$")
    public void iCheckMyBalance() {
        teller.displayBalance();
        Assert.assertEquals(teller.checkState(), TellerWithState.Status.DISPLAY, "Display balance check failure");
    }

    @Then("^I should see that my balance is (\\$\\d+\\.\\d+)$")
    public void iShouldSeeThatMyBalanceIs$(@Transform(MoneyConverter.class) Money amount) {
        Assert.assertEquals(accountHelper.getMyAccount().getBalance(),amount,"Incorrect account balance - ");
        Assert.assertEquals(teller.checkState(), TellerWithState.Status.DISPLAY, "Display balance check failure");
    }

    @When("^I withdraw (\\$\\d+\\.\\d+)$")
    public void iWithdraw$(@Transform(MoneyConverter.class) Money amount) {
        teller.withdrawFrom(accountHelper.getMyAccount(), amount);
    }

    @Then("^I should be told that I have insufficient funds in my account$")
    public void iShouldBeToldThatIHaveInsufficientFundsInMyAccount() {
        Assert.assertEquals(teller.checkState(), TellerWithState.Status.OVERDRAW, "Insufficient funds check failure");
    }
}
