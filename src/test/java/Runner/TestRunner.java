package Runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features="src/main/resources/Features/Folder1"
                , glue="StepDefinitions"
               // ,tags= {"@FirstScenario,@SecondScenario"}
                )

public class TestRunner extends AbstractTestNGCucumberTests {

}
