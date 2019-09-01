package StepDefinitions;

import org.testng.Assert;

import cucumber.api.java.en.Given;

public class TestCucumberTestNG {

	@Given("^Test(\\d+)$")
	public void test(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("And finally its done");
		Assert.assertEquals(false, true);
	}
	
	@Given("^Book a date$")
	public void book_a_date() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("And finally its done");
	}

}
