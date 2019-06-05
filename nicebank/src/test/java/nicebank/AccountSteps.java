package nicebank;

import cucumber.api.Transform;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.testng.Assert;
import support.KnowsTheAccount;
import transforms.MoneyConverter;

import static hooks.BackgroundProcessHooks.errorMessage;
import static hooks.BackgroundProcessHooks.wantsToQuit;

public class AccountSteps {
    private KnowsTheAccount accountHelper;
    public AccountSteps(KnowsTheAccount accountHelper) {
        this.accountHelper = accountHelper;
    }

    @Given("^my account has been credited with (\\$\\d+\\.\\d+)$")
    public void iHaveDeposited$InMyAccount(@Transform(MoneyConverter.class) Money amount) throws Throwable {
        accountHelper.getMyAccount().credit(amount);
        int timeoutMilliSecs = 500;
        int pollIntervalMilliSecs = 100;
        while (!accountHelper.getMyAccount().getBalance().equals(amount) && timeoutMilliSecs>0) {
            Thread.sleep(pollIntervalMilliSecs);
            timeoutMilliSecs-=pollIntervalMilliSecs;
            // Scenario fails in case of internal error
            if (wantsToQuit) {throw new RuntimeException("Wants to quite with the message: " + errorMessage);}
        }
        Assert.assertEquals(accountHelper.getMyAccount().getBalance(),amount,"Incorrect account balance - ");
    }
    @Then("^the balance of my account should be (\\$\\d+\\.\\d+)")
    public void theBalanceOfMyAccountShouldBe$(@Transform(MoneyConverter.class) Money amount) throws Throwable {
        int timeoutMilliSecs = 1000;
        int pollIntervalMilliSecs = 100;
        while (!accountHelper.getMyAccount().getBalance().equals(amount) && timeoutMilliSecs>0) {
            Thread.sleep(pollIntervalMilliSecs);
            timeoutMilliSecs-=pollIntervalMilliSecs;
            // Scenario fails in case of internal error
            if (wantsToQuit) {throw new RuntimeException("Wants to quite with the message: " + errorMessage);}
        }
        Assert.assertEquals(accountHelper.getMyAccount().getBalance(),amount,"Incorrect account balance - ");
    }
}
