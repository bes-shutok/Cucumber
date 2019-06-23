package support;

import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class MyWebDriver extends EventFiringWebDriver {
    private static final Logger logger = Logger.getLogger(MyWebDriver.class);
    public MyWebDriver() {
        // For the driver installation see https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver
        // run command "choco update chromedriver" if driver version is outdated
        // System.setProperty("webdrive.chrome.driver","C:\\ProgramData\\chocolatey\\lib\\chromedriver\\tools");
        super(new ChromeDriver());
        logger.info("Returning MyWebDriver");
    }
}
