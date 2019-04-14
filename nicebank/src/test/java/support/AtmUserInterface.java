package support;

import nicebank.Account;
import nicebank.Teller;
import nicebank.Money;
import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import static hooks.ServerHooks.PORT;

class AtmUserInterface implements Teller {
    private final EventFiringWebDriver webDriver;
    AtmUserInterface(EventFiringWebDriver webDriver){
        this.webDriver = webDriver;
    }
    public void withdrawFrom(Account account, Money amount) {
            webDriver.get("http://localhost:" + PORT);
            if (amount.equals(new Money("10.00"))) {
                webDriver.findElement(By.id("withdraw10")).click();
                return;
            }
            if (amount.equals(new Money("20.00"))) {
                webDriver.findElement(By.id("withdraw20")).click();
                return;
            }
            webDriver.findElement(By.id("amount")).sendKeys(amount.toString());
            webDriver.findElement(By.id("withdraw")).click();
    }
}
