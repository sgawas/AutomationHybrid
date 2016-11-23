package util1;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import core1.Core1;

public class Keywords extends Core1 {
	public static String error = null;

	public static boolean openBrowser(String browser) {
		try {
			if (config.getProperty(browser).equalsIgnoreCase("chrome")) {

				System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("chrome.switches", "--disable-extensions");
				driver = new ChromeDriver(options);

				return true;
			}
			if (config.getProperty(browser).equalsIgnoreCase("ie")) {

				return true;
			}
			if (config.getProperty(browser).equalsIgnoreCase("firefox")) {

				return true;
			}
			return false;
		} catch (Exception e) {
			logger.info("Exception in openBrowser : " + e.getMessage());
			return false;
		}
	}

	public static boolean closeBrowser() {
		try {
			if (driver != null) {
				driver.close();
				return true;
			}
			return false;
		} catch (Exception e) {
			logger.info("Exception in closeBrowser : " + e.getMessage());
			return false;
		}
	}

	public static boolean navigate(String url) {
		try {
			if (driver != null) {
				driver.get(config.getProperty(url));
				return true;
			}
			return false;
		} catch (Exception e) {
			logger.info("Exception in navigate : " + e.getMessage());
			return false;
		}
	}

	public static boolean click(String object, String locator) {
		try {
			if (locator.equalsIgnoreCase("id")) {
				driver.findElement(By.id(objects.getProperty(object))).click();
				return true;
			}
			if (locator.equalsIgnoreCase("xpath")) {
				driver.findElement(By.xpath(objects.getProperty(object))).click();
				return true;
			}
			return false;
		} catch (Exception e) {
			logger.info("Exception in click : " + e.getMessage());
			return false;
		}
	}

	public static boolean input(String object, String locator, String data) {
		try {
			if (locator.equalsIgnoreCase("id")) {
				driver.findElement(By.id(objects.getProperty(object))).sendKeys(data);
				return true;
			}
			if (locator.equalsIgnoreCase("xpath")) {
				driver.findElement(By.xpath(objects.getProperty(object))).sendKeys(data);
				return true;
			}
			return false;
		} catch (Exception e) {
			error = e.getMessage();
			System.out.println("Exception in input e.getLocalizedMessage(): " + e.getMessage());
			System.out.println("Exception in input e.getStackTrace(): " + e.getStackTrace().toString());
			return false;
		}

	}

}
