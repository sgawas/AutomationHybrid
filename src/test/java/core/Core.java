package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


import com.relevantcodes.extentreports.ExtentReports;

import util.Common;
import util.Configs;
import util.MonitoringMail;
import util.Xls_Reader;

public class Core {

	public static Properties config = new Properties();
	public static Properties object = new Properties();
	public static Xls_Reader coreExcel = null;
	public static WebDriver driver = null;
	public static ExtentReports report =  null;
	public static String reportPath = null;
	public static Xls_Reader dataExcel = null;
	
	@BeforeSuite(alwaysRun=true)
	public static void initialize() throws IOException {
		System.out.println("2");
		FileInputStream fileIn = null;
		if (driver == null) {
			try {
				fileIn = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\java\\properties\\config.properties");
				config.load(fileIn);
				fileIn = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\java\\properties\\object.properties");
				object.load(fileIn);
				coreExcel = new Xls_Reader(config.getProperty("coreExcel"));
				dataExcel = new Xls_Reader(config.getProperty("dataExcel"));
				reportPath = config.getProperty("reportPath")+Common.getTimeStamp()+".html";
				report = new ExtentReports(reportPath);
				
			} catch (Exception e) {
				System.out.println("Exception :"+e.getMessage());
			}
		}
		System.out.println("2");
	}

	@AfterSuite(alwaysRun=true)
	public static void closeAutomation() throws IOException{
		driver.quit();
		report.flush();
		try {
			MonitoringMail.sendMail(Configs.getSERVER(), Configs.getFROM_EMAIL(), Configs.getTO_EMAIL(), Configs.getSUBJECT(), 
					Configs.getMESSAGE_BODY(), Configs.getAttachmentPath(), Configs.getAttachmentName());
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		Common.copyLogFile(config.getProperty("appLog"),config.getProperty("appLogNewPath"));
		Common.copyLogFile(config.getProperty("appSel"),config.getProperty("selLogNewPath"));
	}
	
	
}
