package Runner;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@ExtendedCucumberOptions(jsonReport = "target/cucumber.json"
		,retryCount = 1
		,detailedReport = true
		,detailedAggregatedReport = true
		,overviewReport = true
		,coverageReport = true
		,jsonUsageReport = "target/cucumber-usage.json"
		,usageReport = true
		,toPDF = true
		//,excludeCoverageTags = {"@flaky" }
		,includeCoverageTags = {"@passed" }
		,outputFolder = "target"
)

@CucumberOptions(
			features = {"classpath:Features/Folder1"} 
			, glue="StepDefinitions"
			,plugin = { "pretty", "html:target/cucumber-default-report",
					"json:target/cucumber.json"
					,"junit:target/cucumber-results.xml"
					,"usage:target/cucumber-usage.json"
					,"rerun:target/rerun.txt"}
//			,tags= {"@smoke"}  // Run tests in groups
			,monochrome = false
//			,dryRun = true
			)

public class TestRunner extends AbstractTestNGCucumberTests {
	
    @BeforeSuite
    public static void setUp()  {
    	System.out.println("In Before Suite ExtendedCucumberOptions");

    }
    
    @AfterSuite
    public static void tearDown() {
        System.out.println("In After Suite ExtendedCucumberOptions");
   }   

}
