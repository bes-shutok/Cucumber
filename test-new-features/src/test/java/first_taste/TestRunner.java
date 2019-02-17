package first_taste;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@CucumberOptions(
        plugin={"pretty"},
        // plugin={"progress"},
        // dryRun=true,
        snippets= SnippetType.CAMELCASE,
        features={"src/test/resources/tic_tac_toe"},
        glue={"tic_tac_toe"}
        //,tags={"~@Ignore"}
        )
@Test
public class TestRunner extends AbstractTestNGCucumberTests {


    public void testRunner() {
        Assert.assertEquals(1, 1);

    }
}
