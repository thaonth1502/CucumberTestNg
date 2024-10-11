package thaonth7.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features/Categrories",
        glue = {"thaonth7.stepsDefinitions",
                "thaonth7.common"
        },
        plugin = {"pretty", "html:target/cucumber-html-report.html"}
)
@Test
public class TestRunnerCategoriesCMS extends AbstractTestNGCucumberTests {

}
