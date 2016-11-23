package util;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import core.Core;

public class Keywords {

	public static boolean openBrowser(String browser) {
		try {
			if (Core.config.getProperty(browser).equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
				Core.driver = new ChromeDriver();
			} else if (Core.config.getProperty(browser).equalsIgnoreCase("ie")) {
				Core.driver = new FirefoxDriver();
			} else if (Core.config.getProperty(browser).equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
				Core.driver = new InternetExplorerDriver();
			}
			return true;
		} catch (Exception e) {

			return false;
		}
	}

	public static boolean closeBrowser() {
		if (Core.driver != null) {
			Core.driver.close();
			return true;
		}
		return false;
	}

	public static boolean navigate(String URL) {
		if (Core.driver != null) {
			Core.driver.get(Core.config.getProperty(URL));
			return true;
		}
		return false;
	}

	public static boolean click(String object, String locator) {
		try {
			if (locator.equalsIgnoreCase("id")) {
				Core.driver.findElement(By.id(Core.object.getProperty(object))).click();
				return true;
			}
			if (locator.equalsIgnoreCase("xpath")) {
				Core.driver.findElement(By.xpath(Core.object.getProperty(object))).click();
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
	public static boolean input(String object,String locator,String data){
		try{
			if (locator.equalsIgnoreCase("id")) {
				Core.driver.findElement(By.id(Core.object.getProperty(object))).sendKeys("data");
				return true;
			}
			if (locator.equalsIgnoreCase("xpath")) {
				Core.driver.findElement(By.xpath(Core.object.getProperty(object))).sendKeys("data");
				return true;
			}
			
		}catch(Exception e){
			return false;
		}
		return false;
	}
}
