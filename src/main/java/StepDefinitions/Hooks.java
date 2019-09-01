package StepDefinitions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.primitives.Bytes;

import automationframework.Constants;
import automationframework.TestDataSteps;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

	public static String RunningScenarioName;
	public static String runningEnv;
	public static boolean _initSetup = false;

	@Before
	public void beforeAll(Scenario scenario) throws Throwable {
		if (_initSetup == false) {
			_initSetup = true;
			initializeTestSuite();
		}

		String ScenarioName = scenario.getName();
		String TCID = ScenarioName.substring(ScenarioName.indexOf("\"") + 1,
				(ScenarioName.lastIndexOf("\"") - ScenarioName.indexOf("\"")));
		TCID = TCID.replace("\"", "").trim();
		ScenarioName = ScenarioName.substring(ScenarioName.indexOf("|") + 1, ScenarioName.length()).trim();
		RunningScenarioName = ScenarioName;
		System.out.println("=====================");
		System.out.println("READ SCENARIO INFORMATION -> " + RunningScenarioName);
		Constants.TestDataSteps.check_the_run_flag_for_TC_and_read_testdata(ScenarioName, TCID);

		if (Constants.TestDataSteps._eExecutionFlag.equalsIgnoreCase("N")) {
			// Assume.assumeTrue(false);
			// softAssertion.assertTrue(false);

			// Common.softAssert.assertTrue(false);
		}
	}

	@After
	public void embedScreenshot(Scenario scenario) {

		if (scenario.isFailed()) {
			try {
				/*byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");*/
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Constants.TestDataSteps.executeStep = false;
		// Login.logout();
	}
	/*
	 * public void afterAll(){ System.out.println("=====================");
	 * System.out.println("AFTER HOOK IS RUNNIN");
	 * System.out.println("====================="); Login.logout();
	 * 
	 * }
	 */

	public void initializeTestSuite() {
		System.out.println("=====================");
		System.out.println("Initialize TestSuite");
		//Common.propertyPath();  //
		   Constants.TestDataSteps = new TestDataSteps();


		/*    Constants.TestDataSteps._applicationEnvironment = Common.PropertyPathOR.getProperty("Env");
		    Constants.MQ_PumpingClassForMM = new MQ_Test_programForMM();
		    Constants.MQ_PumpingClassForMM.env=Constants.TestDataSteps._applicationEnvironment;
		    Common.readOR();*/
		    Constants.TestDataSteps.getTotalScenariosForMM();//Read scenario informations from excel sheet	
		}

}
