package core1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;

import util1.Common;
import util1.Xls_Reader;

public class Core1 {
	private static Common common = new Common();
	public static Properties config = new Properties();
	public static Properties objects = new Properties();
	public static WebDriver driver = null;
	public static Xls_Reader coreExcel = null;
	public static Xls_Reader dataExcel = null;
	public static WebDriverWait wait = null;
	public static Logger logger = Logger.getLogger("devpinoyLogger");
	public static ExtentReports reports = null;
	private static String path = null;
	
	@BeforeSuite
	public static void startAutomation() throws IOException{
		FileInputStream file= null;
		logger.info("starting automation");
		if(driver == null){
			try{
			file = new FileInputStream("C:\\Users\\sgawas\\Documents\\Maven\\automateAmazon\\src\\test\\java\\properties1\\config1.properties");
			config.load(file);
			file = new FileInputStream("C:\\Users\\sgawas\\Documents\\Maven\\automateAmazon\\src\\test\\java\\properties1\\object1.properties");
			objects.load(file);
			path = config.getProperty("reportPath")+common.getTimeStamp()+".html";
			reports = new ExtentReports(path);
			coreExcel = new Xls_Reader("C:\\Users\\sgawas\\Documents\\Maven\\automateAmazon\\src\\test\\java\\excel1\\Core.xlsx");
			dataExcel = new Xls_Reader("C:\\Users\\sgawas\\Documents\\Maven\\automateAmazon\\src\\test\\java\\excel1\\testData.xlsx");
			
		}catch(Exception e){
			logger.info("Exception in startAutomation: "+e.getMessage());
		}
		}
	}
	
	@AfterSuite(alwaysRun=true)
	public static void closeAutomation(){
		reports.flush();
		logger.info("ending automation");
		driver.quit();
	}
}
