package automationframework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import StepDefinitions.Hooks;
import Utilities.AutomationLog;
import Utilities.Configuration;
import Utilities.ExcelUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TestDataSteps {

	public boolean executeStep = false;

	public String _scenarioName;
	public int _totalScenario;
	public String _eScenario;
	public String _eTC_ID;
	public String _eExecutionFlag;
	public String _dealDetails;
	public int _totalData;
	public String _dealData;
	public HashMap<String, String> deal_map;
	public String _sheetPath = Configuration.getFXTestDataSheetForMM();
	public static String _testDataSheet = "TestData";
	public String _emptyFieldInExcel = "NA";
	public String dataSplitter = "tbl_";

	public String _applicationEnvironment;

	public boolean _flagCustomerInfo;
	public HashMap<String, String> map_custInfo;
	public String _custInfoSheet = "CustomerInfo";

	//
	// public static XSSFSheet worksheet2;
	public static int rowNumber;
	// public static short lastcellnumber;
	public static int columnsNumber;

	public String GetScenarioName() {

		String Scenarionname = Hooks.RunningScenarioName;
		return Scenarionname;
	}

	// public void getTotalScenarios() {
	//
	// String FXTestDataSheet = Configuration.getFXTestDataSheet();
	// ExcelUtils.setExcel(FXTestDataSheet);
	// int totalScenarios = ExcelUtils.getRow("TestCases");
	// _totalScenario = totalScenarios;
	// }

	public void getTotalScenariosForMM() {

		String MMTestDataSheet = Configuration.getFXTestDataSheetForMM();
		ExcelUtils.setExcel(MMTestDataSheet);
		int totalScenarios = ExcelUtils.getRow("TestCases");
		_totalScenario = totalScenarios;
	}

	public String getTestDataValue(String inputValue) {
		if (inputValue.startsWith(dataSplitter)) {
			if (deal_map.containsKey(inputValue.replace(dataSplitter, ""))) {
				return deal_map.get(inputValue.replace(dataSplitter, ""));
			}
		}
		return inputValue;
	}

	/**
	 * EXCEL Connection Test
	 * 
	 * @param arg1
	 * @throws Throwable
	 */

	@Given("^Check the run flag for \"([^\"]*)\" Tag level validations - New Deal$")
	public void check_the_run_flag_for_TC_and_read_testdata(String TC_Name, String TC_ID) throws Throwable {
		_scenarioName = TC_Name;
		// _totalScenario = getTotalScenarios();
		System.out.println("_scenarioName" + _scenarioName);
		System.out.println("_totalScenario" + _totalScenario);
		for (int scenario = 1; scenario <= _totalScenario; scenario++) {
			System.out.println("*****************************");
			System.out.println("_totalScenario" + _totalScenario);
			_eScenario = ExcelUtils.getCellData(scenario, 2, "TestCases");
			System.out.println("_eScenario" + _eScenario);
			if (_scenarioName.equalsIgnoreCase(_eScenario)) {
				System.out.println("*****************************");
				_eTC_ID = ExcelUtils.getCellData(scenario, 4, "TestCases");
				System.out.println("_eTC_ID" + _eTC_ID);
				if (_eTC_ID.equalsIgnoreCase(TC_ID)) {
					System.out.println("*****************************");
					_eExecutionFlag = ExcelUtils.getCellData(scenario, 1, "TestCases");
					System.out.println("_eExecutionFlag" + _eExecutionFlag);
					if (_eExecutionFlag.equalsIgnoreCase(Constants.STATUS_YES)) {
						System.out.println("*****************************");
						executeStep = true;
						_dealDetails = ExcelUtils.getCellData(scenario, 5, "TestCases");
						System.out.println("Running Deal for data " + _dealDetails);

						_totalData = ExcelUtils.getRow("TestData");
						System.out.println("_totalData" + _totalData);

						if (!(_totalData == 0)) {
							for (int data = 1; data <= _totalData; data++) {
								System.out.println("*****************************");
								_dealData = ExcelUtils.getCellData(data, 0, "TestData");
								System.out.println("_dealData" + _dealData);
								if (_dealData.equalsIgnoreCase(_dealDetails)) {

									System.out.println("Deal Data to be used is" + _dealData);

									InputStream fis;
									XSSFWorkbook wb = null;

									deal_map = new HashMap<String, String>();
									System.out.println("deal_map" + deal_map);
									try {
										fis = new FileInputStream(_sheetPath);
										wb = new XSSFWorkbook(fis);
									} catch (FileNotFoundException fe) {
										fe.printStackTrace();
										AutomationLog.error("File not found " + _sheetPath);
									} catch (IOException e) {
										e.printStackTrace();
										AutomationLog.error("File not readable " + _sheetPath);
									}

									XSSFSheet worksheet;
									worksheet = wb.getSheet(_testDataSheet);
									System.out.println("worksheet" + worksheet);

									int rows = worksheet.getLastRowNum();
									int columns = worksheet.getRow(0).getLastCellNum();
									rowNumber = data;

									for (int c = 0; c <= columns - 1; c++) {
										String k = func_genericExcelCellFormatConvIntoStr(
												worksheet.getRow(0).getCell(c));
										String v = func_genericExcelCellFormatConvIntoStr(
												worksheet.getRow(data).getCell(c));

										//
										if (k.equalsIgnoreCase("FO_DEAL")) {
											columnsNumber = c;
										}

										System.out.println("k" + k);
										System.out.println("v" + v);
										deal_map.put(k.trim(), v.trim());
										System.out.println("deal_map" + deal_map.toString());
									}

								}
							}
						}

					}
				}
			}
		}
	}

	/*
	 * public void func_mapCollectCustInfo(String custID, XSSFWorkbook wb) {
	 * XSSFSheet worksheet; worksheet = wb.getSheet(_custInfoSheet); int rows =
	 * worksheet.getLastRowNum(); int columns =
	 * worksheet.getRow(0).getLastCellNum(); //Collect column names
	 * ArrayList<String> clmn_map = new ArrayList<String>(); for (int c = 0; c <=
	 * columns - 1; c++) { String k =
	 * func_genericExcelCellFormatConvIntoStr(worksheet.getRow(0).getCell(c));
	 * clmn_map.add(k); }
	 * 
	 * String _branchSelect = Common.getValue("Branch_FX"); //find the particular
	 * customer for (int r = 1; r <= rows - 1; r++) { String env =
	 * func_genericExcelCellFormatConvIntoStr(worksheet.getRow(r).getCell(clmn_map.
	 * indexOf("Environment"))); String bra =
	 * func_genericExcelCellFormatConvIntoStr(worksheet.getRow(r).getCell(clmn_map.
	 * indexOf("Branch"))); String cus =
	 * func_genericExcelCellFormatConvIntoStr(worksheet.getRow(r).getCell(clmn_map.
	 * indexOf("CustomerID"))); if(custID.equals(cus) && bra.equals(_branchSelect)
	 * && env.equals(_applicationEnvironment)) { _flagCustomerInfo = true;
	 * map_custInfo = new HashMap<String, String>(); for (int c = 1; c <= columns -
	 * 1; c++) { String k =
	 * func_genericExcelCellFormatConvIntoStr(worksheet.getRow(0).getCell(c));
	 * String v =
	 * func_genericExcelCellFormatConvIntoStr(worksheet.getRow(r).getCell(c));
	 * 
	 * map_custInfo.put(k.trim(), v.trim()); } } } }
	 */

	@Given("^user enter application url with for data for TC_(\\d+)$")
	public void user_enter_application_url_with_for_data_for(String tc_id) throws Throwable {
		String customer = deal_map.get("deal_type");
		String branch = deal_map.get("customer");

		System.out.println(" **************** Customer to be used is " + customer);
		System.out.println(" **************** Branch to be used is " + branch);
	}

	@When("^user enters correct credentials$")
	public void user_enters_correct_credentials() throws Throwable {
		//
	}

	@Then("^user should be logged into application$")
	public void user_should_be_logged_into_application() throws Throwable {
		//
	}

	public String func_genericExcelCellFormatConvIntoStr(Cell testCell) {
		String test = "";
		switch (testCell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			// test = (testCell.getRichStringCellValue().getString());
			test = (testCell.getStringCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA:
		case Cell.CELL_TYPE_NUMERIC:
			// test = String.valueOf(testCell.getNumericCellValue());
			DecimalFormat format = new DecimalFormat("0.#");
			test = format.format(testCell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			test = Boolean.toString(testCell.getBooleanCellValue());
			break;
		default:
			test = "";
			break;
		}
		return test;
	}

}
