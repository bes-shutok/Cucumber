package hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.openqa.selenium.*;
import support.KnowsTheDomain;


public class WebDriverHooks {
    private KnowsTheDomain helper;

    public WebDriverHooks(KnowsTheDomain helper) {
        this.helper = helper;
    }

    @After
    public void finish(Scenario scenario) {
        try {
            byte[] screenshot = helper.getWebDriver().getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot,"image/png");
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            // somePlatformsDontSupportScreenshots.printStackTrace();
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());

        } finally {
            helper.getWebDriver().close();
        }
    }
}
