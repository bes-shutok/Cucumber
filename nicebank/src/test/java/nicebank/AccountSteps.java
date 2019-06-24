package nicebank;

import cucumber.api.Transform;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import transforms.MoneyConverter;

import static hooks.BackgroundProcessHooks.errorMessage;
import static hooks.BackgroundProcessHooks.wantsToQuit;

public class AccountSteps {
    @Autowired
    private Account account;

    @Given("^my account has been credited with (\\$\\d+\\.\\d+)$")
    public void iHaveDeposited$InMyAccount(@Transform(MoneyConverter.class) Money amount) throws Throwable {
        account.credit(amount);
        int timeoutMilliSecs = 500;
        int pollIntervalMilliSecs = 100;
        while (!account.getBalance().equals(amount) && timeoutMilliSecs>0) {
            Thread.sleep(pollIntervalMilliSecs);
            timeoutMilliSecs-=pollIntervalMilliSecs;
            // Scenario fails in case of internal error
            if (wantsToQuit) {throw new RuntimeException("Wants to quite with the message: " + errorMessage);}
        }
        Assert.assertEquals(account.getBalance(),amount,"Incorrect account balance - ");
    }
    @Then("^the balance of my account should be (\\$\\d+\\.\\d+)")
    public void theBalanceOfMyAccountShouldBe$(@Transform(MoneyConverter.class) Money amount) throws Throwable {
        int timeoutMilliSecs = 1000;
        int pollIntervalMilliSecs = 100;
        while (!account.getBalance().equals(amount) && timeoutMilliSecs>0) {
            Thread.sleep(pollIntervalMilliSecs);
            timeoutMilliSecs-=pollIntervalMilliSecs;
            // Scenario fails in case of internal error
            if (wantsToQuit) {throw new RuntimeException("Wants to quite with the message: " + errorMessage);}
        }
        Assert.assertEquals(account.getBalance(),amount,"Incorrect account balance - ");
    }
}
