package support;

import nicebank.Account;
import nicebank.CashSlot;
import nicebank.Teller;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class KnowsTheDomain {
    private Account myAccount;
    private CashSlot cashSlot;
    private Teller teller;
    private EventFiringWebDriver webDriver;

    public EventFiringWebDriver getWebDriver() {
        if (webDriver == null) {
            // For the driver installation see https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver
            System.setProperty("webdrive.chrome.driver","C:\\ProgramData\\chocolatey\\lib\\chromedriver\\tools");
            webDriver = new EventFiringWebDriver(new ChromeDriver());
        }
        return webDriver;
    }

    public Teller getTeller() {
        if (teller == null) {teller = new AtmUserInterface(getWebDriver());}
        return teller;
    }

    public Account getMyAccount() {
        if (myAccount == null) {myAccount = new Account();}
        return myAccount;
    }

    public CashSlot getCashSlot() {
        if (cashSlot == null) {cashSlot = new CashSlot();}
        return cashSlot;
    }
}
