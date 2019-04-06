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
            webDriver.findElement(By.id("Amount"))
                    .sendKeys(amount.toString());
            webDriver.findElement(By.id("Withdraw")).click();
    }
}
