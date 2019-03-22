import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@CucumberOptions(
        plugin={"pretty", "html:out"},
        // plugin={"progress"},
        // dryRun=true,
        snippets= SnippetType.CAMELCASE,
        // features={"src/test/resources/tic_tac_toe"},
        features={"src/test/resources"},
        //glue={"tic_tac_toe"}
        glue={"fruit"}
        //,tags={"~@Ignore"}
)
@Test
public class TestRunnerFruits extends AbstractTestNGCucumberTests  {

    public void testRunner() {
        Assert.assertEquals(1, 1);

    }

}
