package nicebank;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import support.KnowsTheDomain;
import transforms.MoneyConverter;

public class TellerSteps {
    private KnowsTheDomain helper;
    public TellerSteps(KnowsTheDomain helper) {
        this.helper = helper;
    }

    @When("^I withdraw (\\$\\d+\\.\\d+)$")
    public void iWithdraw$(@Transform(MoneyConverter.class) Money amount) {
        helper.getTeller().withdrawFrom(helper.getMyAccount(), amount);
    }

    @Then("^I should be told that I have insufficient funds in my account$")
    public void iShouldBeToldThatIHaveInsufficientFundsInMyAccount() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
