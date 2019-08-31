package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelUtils {

	private static FileInputStream ExcelFile;
	private static XSSFWorkbook ExcelWbook;
	private static XSSFSheet ExcelWSheet;
	private static String Cell;
	private static XSSFCell cell;
	private static XSSFRow row;

	public static void setExcel(String path) {
		try {

			ExcelFile = new FileInputStream(path);
			ExcelWbook = new XSSFWorkbook(ExcelFile);

		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
			AutomationLog.error("File does not exsist");
		} catch (IOException ie) {
			ie.printStackTrace();
			AutomationLog.error("File not readable");
		}
	}

	public static int getRow(String SheetName) {
		ExcelWSheet = ExcelWbook.getSheet(SheetName);
		int row = ExcelWSheet.getLastRowNum();
		return row;

	}

	public static int getTotalColumnsCount(String SheetName) {
		ExcelWSheet = ExcelWbook.getSheet(SheetName);
		XSSFRow row = ExcelWSheet.getRow(0);
		int colno = row.getLastCellNum();
		return colno;
	}

	public static void setCellData(int rowNum, int columnNum, String SheetName, String strDataValue) {
		ExcelWSheet = ExcelWbook.getSheet(SheetName);
		if (rowNum != 0) {
			ExcelWSheet.createRow(rowNum);
		}
		row = ExcelWSheet.getRow(rowNum);
		cell = row.getCell(columnNum);
		if (cell == null) {
			cell = row.createCell(columnNum);
			cell.setCellValue(strDataValue);
		} else {
			cell.setCellValue(strDataValue);
		}
	}

	// sj60527
	public static void setXLCellValue(String sheetName, int rowNum, int cellNum, String input) {
		try {
			/*
			 * //FileInputStream fis=new
			 * FileInputStream(Configs.getDealPushTemplatePathForMM());
			 * 
			 * ExcelWSheet=ExcelWbook.getSheet(sheetName);
			 * 
			 * ExcelWSheet.getRow(rowNum).createCell(cellNum).setCellValue(input);
			 * 
			 * FileOutputStream fos=new
			 * FileOutputStream(Configs.getDealPushTemplatePathForMM());
			 * 
			 * //ExcelWSheet.
			 * 
			 * fos.close();
			 */

			FileInputStream fis = new FileInputStream(Configuration.getFXTestDataSheetForMM());

			Workbook wb = WorkbookFactory.create(fis);

			wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum).setCellValue(input);

			FileOutputStream fos = new FileOutputStream(Configuration.getFXTestDataSheetForMM());

			wb.write(fos);

			fos.close();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	// Added by Ramana on 12-04-2018
	public static void setOutputData(String path, String SheetName, String ColumnName, String OputputValue) {

		// System.out.println(ColumnName);
		// System.out.println(OputputValue);

		ExcelUtils.setExcel(path);
		int row = ExcelUtils.getRow("Output");
		// System.out.println("Total Rows "+row);

		int colCount = ExcelUtils.getTotalColumnsCount("Output");
		// System.out.println("Total Columns "+colCount);

		boolean checkColumnExistence = false;
		int existingColumnIndex = 0;

		for (int i = 0; i < colCount; i++) {
			String stringColName = ExcelUtils.getCellData(0, i, "Output");
			// System.out.println("Column :"+i+ " Value :"+stringColName);
			if (stringColName.equalsIgnoreCase(ColumnName)) {
				checkColumnExistence = true;
				existingColumnIndex = i;
				break;
			}
		}

		// System.out.println("Checking Column Existence : "+checkColumnExistence);
		// System.out.println("Existing Column Index Value : "+existingColumnIndex);

		if (!checkColumnExistence) {
			setCellData(0, colCount, "Output", ColumnName);
			setCellData(row + 1, colCount, "Output", OputputValue);
			// System.out.println("Created New Column");
		} else {
			setCellData(row + 1, 1, "Output", "1");
			setCellData(row + 1, 2, "Output", "2");
			setCellData(row + 1, 3, "Output", "TC_001");
			setCellData(row + 1, existingColumnIndex, "Output", OputputValue);
			// System.out.println("Updated existing Column");
		}

		try {
			FileOutputStream ExcelOutputFile = new FileOutputStream(new File(path));
			ExcelWbook.write(ExcelOutputFile);
			ExcelOutputFile.flush();
			ExcelOutputFile.close();
		} catch (IOException e) {
			try {
				throw (e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static String getCellData(int rowNum, int columnNum, String SheetName) {
		ExcelWSheet = ExcelWbook.getSheet(SheetName);
		Cell = ExcelWSheet.getRow(rowNum).getCell(columnNum).getStringCellValue();
		return Cell;

	}

	public static void closeExcel(String path) {
		try {
			ExcelFile.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
