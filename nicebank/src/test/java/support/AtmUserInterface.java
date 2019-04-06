package support;

import nicebank.Account;
import nicebank.Teller;
import nicebank.Money;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import static hooks.ServerHooks.PORT;

class AtmUserInterface implements Teller {
    private final EventFiringWebDriver webDriver;
    public AtmUserInterface(){
        // For the driver installation see https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver
        System.setProperty("webdrive.chrome.driver","C:\\ProgramData\\chocolatey\\lib\\chromedriver\\tools");
        this.webDriver = new EventFiringWebDriver(new ChromeDriver());
    }
    public void withdrawFrom(Account account, Money amount) {
        try {
            webDriver.get("http://localhost:" + PORT);
            webDriver.findElement(By.id("Amount"))
                    .sendKeys(amount.toString());
            webDriver.findElement(By.id("Withdraw")).click();
        }
        finally {
            webDriver.close();
        }
    }
}
