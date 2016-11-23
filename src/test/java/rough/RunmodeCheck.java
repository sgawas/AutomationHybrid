package rough;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import core.Core;

public class RunmodeCheck extends Core {

	// public static boolean isTCExecutable(String sheetName){
	// int rows = coreExcel.getRowCount(sheetName);
	//// int cols = coreExcel.getColumnCount(sheetName);
	// for(int rowNum=2;rowNum<=rows; rowNum++){
	// if(coreExcel.getCellData(sheetName, "TCID",
	// rowNum).equalsIgnoreCase("Login")){
	// if(coreExcel.getCellData(sheetName, "Runmode",
	// rowNum).equalsIgnoreCase("Y")){
	// return true;
	// }
	// else{
	// return false;
	// }
	// }
	// }
	//
	// return false;
	// }
	//
	// public static void main(String[] args) throws IOException {
	// Core.initialize();
	//// System.out.println(Core.coreExcel.getRowCount("TestCases"));
	//
	// System.out.println(isTCExecutable("UpdateUserDetails"));
	// }
	//

	@Test(dataProvider = "getTestData")
	public static void isTCExecutable(Hashtable<String, String> data) {

		System.out.println(data.get("YourName"));
		System.out.println(data.get("Email"));

	}

	@DataProvider
	public Object[][] getTestData() throws IOException {
		Core.initialize();
		Object[][] data = null;
		String sheetName = "Register";
		int rows = Core.dataExcel.getRowCount(sheetName);
		int cols = Core.dataExcel.getColumnCount(sheetName);
		int colStartRowNum = 1;

		data = new Object[rows - 1][1];
		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			Hashtable<String, String> table = new Hashtable<String, String>();
			for (int colNum = 0; colNum < cols; colNum++) {
				table.put(Core.dataExcel.getCellData(sheetName, colNum, colStartRowNum),
						Core.dataExcel.getCellData(sheetName, colNum, rowNum));
			}
			data[rowNum - 2][0] = table;
		}

		return data;
	}
}
