package StepDefinitions;

import Utilities.AutomationLog;
import automationframework.Constants;
import cucumber.api.java.en.When;

public class CreationXML {


	private static String CURRENCY;

	@When("^create a deal$")
	public void create_a_deal() throws Throwable {
		System.out.println("**************************************");
		System.out.println("**************************************");
		System.out.println("***************Data*******************");
		CreationXML.CURRENCY = Constants.TestDataSteps.deal_map.get("CURRENCY");
		System.out.println("DealCreationForMM.CURRENCY = " + CreationXML.CURRENCY);
		AutomationLog.info("DealCreationForMM.CURRENCY = " + CreationXML.CURRENCY);
		
		
		/*if (!Constants.TestDataSteps.deal_map.get("CODE")
				.equalsIgnoreCase(Constants.TestDataSteps._emptyFieldInExcel)) {
			DealCreationForMM.CURRENCY = Constants.TestDataSteps.deal_map.get("CURRENCY");
			AutomationLog.info("DealCreationForMM.CURRENCY = " + DealCreationForMM.CURRENCY);
		} else {
			AutomationLog.info("DealCreationForMM.CURRENCY blank for the scenario");
		}*/

	}

}
