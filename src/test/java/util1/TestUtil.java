package util1;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import core1.Core1;

public class TestUtil extends Core1 {
	private static Common common = new Common();
	private static String screenshotPath = null;
	private static ExtentTest test = null;
	
	public static void executeKeywords(String sheetName, Hashtable<String,String> table) throws InterruptedException{
		test = reports.startTest(sheetName);
		test.log(LogStatus.INFO, "<span style='font-weight:bold;'>" + sheetName + " TC" + "</span>");
		String tsID, desciption,keyword, object, locator, data, screenshotCapture;
		boolean status=true;
		int rows = coreExcel.getRowCount(sheetName);
		for(int rowNum=2; rowNum<=rows; rowNum++){
			tsID = coreExcel.getCellData(sheetName, "TSID", rowNum);
			desciption = coreExcel.getCellData(sheetName, "Desciption", rowNum);
			keyword = coreExcel.getCellData(sheetName, "Keyword", rowNum);
			object = coreExcel.getCellData(sheetName, "Object", rowNum);
			locator = coreExcel.getCellData(sheetName, "Locator", rowNum);
			data = coreExcel.getCellData(sheetName, "Data", rowNum);
			screenshotCapture = coreExcel.getCellData(sheetName, "ScreenshotCapture", rowNum);
			if(object.trim().equals("")){
				object= "null";
			}
			if(locator.trim().equals("")){
				locator= "null";
			}
			if(data.trim().equals("")){
				data= "null";
			}
			logger.info("TSID:"+tsID+"\tDesciption:"+desciption+"\tKeyword:"+keyword+
					"\tObject:"+object+"\tLocator:"+ locator+"\tData:"+data+"\tscreenshotCapture:"+screenshotCapture);
			if(keyword.equalsIgnoreCase("openBrowser")){
				status = Keywords.openBrowser(data);
				driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				wait = new WebDriverWait(driver,20L);
				wait.pollingEvery(20L, TimeUnit.SECONDS);
				System.out.println(tsID+": "+status);
			}
			if(keyword.equalsIgnoreCase("navigate")){
				status = Keywords.navigate(data);
				System.out.println(tsID+": "+status);
				Thread.sleep(5000);
			}
			if(keyword.equalsIgnoreCase("click")){
				Thread.sleep(3000);
				status = Keywords.click(object,locator);
				System.out.println(tsID+": "+status);
			}
			if(keyword.equalsIgnoreCase("input")){
				status = Keywords.input(object,locator,table.get(data));
				System.out.println(tsID+": "+status);
			}
			if(keyword.equalsIgnoreCase("closeBrowser")){
				status = Keywords.closeBrowser();
				System.out.println(tsID+": "+status);
			}
			
			if(!status){
				screenshotPath=config.getProperty("screenshotPath")+sheetName+"-"+tsID+"-"+keyword+"-Error-"+common.getTimeStamp()+".jpeg";
				common.getScreenshotCapture(screenshotPath);
				logger.info("TSID:"+tsID+"\tDesciption:"+desciption+"\tKeyword:"+keyword+"\t\tFAILED");
				logger.info("Error Screenshot saved in location: "+screenshotPath);
				test.log(LogStatus.FAIL, "TSID:"+tsID+"\t\tDesciption:"+desciption+"\t\tKeyword:"+keyword, test.addScreenCapture(screenshotPath));
				test.log(LogStatus.ERROR, Keywords.error);
			}
			if(status){
				logger.info("TSID:"+tsID+"\tDesciption:"+desciption+"\tKeyword:"+keyword+"\t\tPASSED");
				if(screenshotCapture.equalsIgnoreCase("yes")||screenshotCapture.equalsIgnoreCase("y")){
					screenshotPath=config.getProperty("screenshotPath")+sheetName+"-"+tsID+"-"+keyword+"-"+common.getTimeStamp()+".jpeg";
					logger.info("Screenshot saved in location: "+screenshotPath);
					common.getScreenshotCapture(screenshotPath);
					test.log(LogStatus.PASS, "TSID:"+tsID+"\t\tDesciption:"+desciption+"\t\tKeyword:"+keyword, test.addScreenCapture(screenshotPath));
				}
				else{
					test.log(LogStatus.PASS, "TSID:"+tsID+"\t\tDesciption:"+desciption+"\t\tKeyword:"+keyword);
				}
			}
		}
		reports.endTest(test);
		
	}

	public static boolean isTCExecutable(String testCaseName) {
		String sheetName = "TestCases";
		int rows = coreExcel.getRowCount(sheetName);

		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			if (coreExcel.getCellData(sheetName, "TCID", rowNum).equalsIgnoreCase(testCaseName)) {
				if (coreExcel.getCellData(sheetName, "Runmode", rowNum).equalsIgnoreCase("Yes")
						|| coreExcel.getCellData(sheetName, "Runmode", rowNum).equalsIgnoreCase("y")) {

					return true;
				}
			}
		}

		return false;
	}

	public static Object[][] getTestData(String sheetName) {

		Object[][] data = null;
		int rows = dataExcel.getRowCount(sheetName);
		int cols = dataExcel.getColumnCount(sheetName);
		data = new Object[rows - 1][1];
		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			Hashtable<String, String> table = new Hashtable<String, String>();
			for (int colNum = 0; colNum < cols; colNum++) {
				String key = dataExcel.getCellData(sheetName, colNum, 1);
				String value = dataExcel.getCellData(sheetName, colNum, rowNum);
				table.put(key, value);
			}
			data[rowNum - 2][0] = table;
		}
		return data;
	}

}
