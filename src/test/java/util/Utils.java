package util;

import java.util.Hashtable;
import core.Core;

public class Utils {

	@SuppressWarnings("unused")
	public static void executeKeyword(String testCaseName,Hashtable<String,String> table) {
		String sheetName = testCaseName;
		String TSID = null, keyword, description, object, locator, data, screenshotCapture;
		boolean status;
		int rows = Core.coreExcel.getRowCount(sheetName);
		for (int rowNum = 2; rowNum <= rows; rowNum++) {

			TSID = Core.coreExcel.getCellData(sheetName, "TSID", rowNum);
			keyword = Core.coreExcel.getCellData(sheetName, "Keyword", rowNum);
			description = Core.coreExcel.getCellData(sheetName, "Description", rowNum);
			object = Core.coreExcel.getCellData(sheetName, "Object", rowNum);
			locator = Core.coreExcel.getCellData(sheetName, "Locator", rowNum);
			data = Core.coreExcel.getCellData(sheetName, "Data", rowNum);
			screenshotCapture = Core.coreExcel.getCellData(sheetName, "ScreenshotCapture", rowNum);

			if (keyword.equalsIgnoreCase("openBrowser")) {
				status = Keywords.openBrowser(data);
			}
			if (keyword.equalsIgnoreCase("closeBrowser")) {
				status = Keywords.closeBrowser();
			}
			if (keyword.equalsIgnoreCase("navigate")) {
				status = Keywords.navigate(data);
			}
			if (keyword.equalsIgnoreCase("click")) {
				status = Keywords.click(object, locator);
			}
			if (keyword.equalsIgnoreCase("input")) {
				status = Keywords.input(object, locator, table.get(data));
			}
			if (keyword.equalsIgnoreCase("mouseOver")) {

			}
			if (keyword.equalsIgnoreCase("verifyErrorOnThePage")) {

			}
			if(screenshotCapture.equalsIgnoreCase("Yes")||screenshotCapture.equalsIgnoreCase("y")){
				Common.takeScreenshot();
			}
		}
	}

	public static boolean isTCExecutable(String sheetName) {
		int rows = Core.coreExcel.getRowCount(sheetName);
		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			if (Core.coreExcel.getCellData(sheetName, "TCID", rowNum).equalsIgnoreCase("Login")) {
				if (Core.coreExcel.getCellData(sheetName, "Runmode", rowNum).equalsIgnoreCase("Y")) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	public static Object[][] getTestData(String sheetName) {
		Object[][] data = null;
		int rows = Core.dataExcel.getRowCount(sheetName);
		System.out.println("222");
		int cols = Core.dataExcel.getColumnCount(sheetName);
		int colStartRowNum = 1;
		data = new Object[rows - 1][1];
		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			Hashtable<String, String> table = new Hashtable<String, String>();
			for (int colNum = 0; colNum < cols; colNum++) {
				table.put(Core.coreExcel.getCellData(sheetName, colNum, colStartRowNum),
						Core.coreExcel.getCellData(sheetName, colNum, rowNum));
			}
			data[rowNum - 2][0] = table;
		}
		return data;
	}
}
