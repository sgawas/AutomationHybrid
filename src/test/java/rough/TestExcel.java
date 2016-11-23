package rough;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.annotations.Test;


import core.Core;
import util.Common;


public class TestExcel {

//private String path = null;
	
	
	@Test
	public void testLogin() throws AddressException, MessagingException, IOException{
		long timeInMillis = System.currentTimeMillis();
		String dateforrow;
		Calendar cal1 = Calendar.getInstance();
		cal1.setTimeInMillis(timeInMillis);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
		                                "MMddyyyyHHmmss");
		dateforrow = dateFormat.format(cal1.getTime());
		System.out.println(dateforrow);
		Core.initialize();
		Common.copyLogFile(Core.config.getProperty("appLog"),Core.config.getProperty("appLogNewPath"));
//		Common.copyLogFile("Selenium");
//		Core.initialize();
//		path = System.getProperty("user.dir") + "\\src\\test\\java\\properties\\report2.html";
//		System.out.println(path);
//		ExtentReports extent = new ExtentReports(path);
//		ExtentTest test = null;
//		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
//		WebDriver driver= new ChromeDriver();
//		try{
//			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
//			driver.manage().window().maximize();
//			test = extent.startTest("start automation");
////			driver.get("https://accounts.google.com/ServiceLogin?service=mail&continue=https://mail.google.com/mail/#identifier");
////			test.log(LogStatus.PASS, "Gmail page loaded");
////			driver.findElement(By.id("Email")).sendKeys("ntester26@gmail.com");
////			test.log(LogStatus.PASS, "Entered username");
////			driver.findElement(By.id("next")).click();
////			test.log(LogStatus.PASS, "clicked on next");
////			driver.findElement(By.id("Passwd")).sendKeys("abcddddd");
////			test.log(LogStatus.PASS, "entered password");
////			driver.findElement(By.id("link-signin-different")).click();
////			test.log(LogStatus.FAIL, "not able to sign in");
//			
//		}catch(Exception e){
//			
//		}finally{
//			extent.endTest(test);
//			extent.flush();
//			driver.quit();
//			MonitoringMail.sendMail(Configs.getSERVER(), Configs.getFROM_EMAIL(), Configs.getTO_EMAIL(), Configs.getSUBJECT(), 
//					Configs.getMESSAGE_BODY(), Configs.getAttachmentPath(), Configs.getAttachmentName());
//			
//		}
		
	}
}
