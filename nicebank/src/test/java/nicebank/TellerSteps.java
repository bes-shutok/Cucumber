package nicebank;

import cucumber.api.Transform;
import cucumber.api.java.en.When;
import support.KnowsTheDomain;
import transforms.MoneyConverter;

public class TellerSteps {
    KnowsTheDomain helper;
    public TellerSteps(KnowsTheDomain helper) {
        this.helper = helper;
    }
    @When("^I withdraw (\\$\\d+\\.\\d+)$")
    public void iWithdraw$(@Transform(MoneyConverter.class) Money amount) throws Throwable {
        helper.getTeller().withdrawFrom(helper.getMyAccount(), amount);
        helper.getMyAccount().withdraw(amount);
    }
}
