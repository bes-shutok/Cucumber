package hooks;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.Scenario;
public class SomeTestHooks {
    @Before
    public void beforeCallingScenario() {
        System.out.println("*********** About to start the scenario.");
    }
    @After
    public void afterRunningScenario(Scenario scenario) {
        System.out.println("*********** Just finished running scenario: "
                + scenario.getStatus());
    }
}