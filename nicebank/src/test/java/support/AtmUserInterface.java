package support;

import nicebank.Account;
import nicebank.Money;
import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import static hooks.ServerHooks.PORT;

public class AtmUserInterface implements TellerWithState {

    private final EventFiringWebDriver webDriver;
    private Status status= Status.WITHDRAW;

    @Autowired
    public AtmUserInterface(MyWebDriver webDriver){
        this.webDriver = webDriver;
    }
    public boolean withdrawFrom(Account account, Money amount) {
            webDriver.get("http://localhost:" + PORT);
            if (amount.equals(new Money("$10.00"))) {
                webDriver.findElement(By.id("withdraw10")).click();
                return true;
            }
            if (amount.equals(new Money("$20.00"))) {
                webDriver.findElement(By.id("withdraw20")).click();
                return true;
            }
            webDriver.findElement(By.id("amount")).sendKeys(amount.toString());
            webDriver.findElement(By.id("withdraw")).click();
            return true;
    }

    public void displayBalance() {
        webDriver.get("http://localhost:" + PORT);
        webDriver.findElement(By.id("displayBalance")).click();
        status=Status.DISPLAY;
    }

    // Uses webDriver response from above methods. Should not be called before withdrawFrom() or displayBalance()
    public Status checkState() {
        Assert.assertNotNull(webDriver, "checkState() cannot be used before other methods");
        // return status;
        if (webDriver.getPageSource().contains("You have insufficient funds in your account!")) {
            return Status.OVERDRAW;
        }
        if (webDriver.getPageSource().contains("Your balance: ")) {
            return Status.DISPLAY;
        }
        return Status.WITHDRAW;
    }
}
