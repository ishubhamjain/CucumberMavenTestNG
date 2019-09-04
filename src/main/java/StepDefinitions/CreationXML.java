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
//		CreationXML.CURRENCY = Constants.TestDataSteps.deal_map.get("CURRENCY");
//		System.out.println("DealCreationForMM.CURRENCY = " + CreationXML.CURRENCY);
//		AutomationLog.info("DealCreationForMM.CURRENCY = " + CreationXML.CURRENCY);
		System.out.println("Constants.TestDataSteps._emptyFieldInExcel"+Constants.TestDataSteps._emptyFieldInExcel);
		System.out.println("Constants.TestDataSteps.deal_map.get(\"CODE\")"+Constants.TestDataSteps.deal_map.get("CURRENCY"));
		
		if (!Constants.TestDataSteps.deal_map.get("CURRENCY")
				.equalsIgnoreCase(Constants.TestDataSteps._emptyFieldInExcel)) {
			CreationXML.CURRENCY = Constants.TestDataSteps.deal_map.get("CURRENCY");
			System.out.println("CreationXML.CURRENCY = " + CreationXML.CURRENCY);
			AutomationLog.info("CreationXML.CURRENCY = " + CreationXML.CURRENCY);
		} else {
			AutomationLog.info("CreationXML.CURRENCY blank for the scenario");
		}

	}

}
