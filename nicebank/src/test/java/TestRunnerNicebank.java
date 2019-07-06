import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@CucumberOptions(
        plugin={"pretty", "html:out"},
        // plugin={"progress"}, dryRun=true,
        snippets= SnippetType.CAMELCASE
        // , tags = {"@NewFeatures"}
        //,features={"src/test/resources"}, glue={"fruit"}
        //, tags={"@SmokeTests"}
)
@Test
public class TestRunnerNicebank extends AbstractTestNGCucumberTests {
        public void testRunner() {
        Assert.assertEquals(1, 1);

    }

}
