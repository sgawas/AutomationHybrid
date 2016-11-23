package rough;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Soft_Assert {

	// Created object of testng SoftAssert class to use It's Properties.
	SoftAssert s_assert = new SoftAssert();
	String Actualtext;
	WebDriver driver = new FirefoxDriver();

	@BeforeClass
	public void load_url() {
		driver.manage().window().maximize();
		driver.get("http://only-testing-blog.blogspot.in/2014/01/textbox.html");
	}

	@Test
	// In this method, If any assertion fails then execution will be aborted.
	public void hard_assert_text() {
		Actualtext = driver.findElement(By.xpath("//h2/span")).getText();
		// Text on expected side Is written Incorrect intentionally to get fail
		// this assertion.
		Assert.assertEquals(Actualtext, "Tuesday, 01 January 2014", "1st assert failed.");
		System.out.println("Hard Assertion -> 1st pagetext assertion executed.");

		Assert.assertEquals(Actualtext, "Tuesday, 28 January 2014", "2nd assert failed.");
		System.out.println("Hard Assertion -> 2nd pagetext assertion executed.");

		driver.findElement(By.xpath("//input[@value='Show Me Alert']")).click();
		String Alert_text = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();

		Assert.assertEquals(Alert_text, "Hi.. is alert message!", "Alert Is InCorrect");
		System.out.println("Hard Assertion -> 1st alert assertion executed.");

		Assert.assertEquals(Alert_text, "Hi.. This is alert message!", "Alert Is Correct");
		System.out.println("Hard Assertion -> 2nd alert assertion executed.");
	}

	@Test
	// In this method, Test execution will not abort even If any assertion fail.
	// Full Test will be executed.
	public void soft_assert_text() {
		Actualtext = driver.findElement(By.xpath("//h2/span")).getText();
		// Text on expected side Is written Incorrect intentionally to get fail
		// this assertion.
		s_assert.assertEquals(Actualtext, "Tuesday, 01 January 2014", "1st assert failed.");
		System.out.println("Soft Assertion -> 1st pagetext assertion executed.");

		s_assert.assertEquals(Actualtext, "Tuesday, 28 January 2014", "2nd assert failed.");
		System.out.println("Soft Assertion -> 2nd pagetext assertion executed.");

		driver.findElement(By.xpath("//input[@value='Show Me Alert']")).click();
		String Alert_text = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();

		// Alert expected text Is written Incorrect intentionally to get fail
		// this assertion.
		s_assert.assertEquals(Alert_text, "Hi.. is alert message!", "Alert Is InCorrect");
		System.out.println("Soft Assertion -> 1st alert assertion executed.");

		s_assert.assertEquals(Alert_text, "Hi.. This is alert message!", "Alert Is Correct");
		System.out.println("Soft Assertion -> 2nd alert assertion executed.");
		s_assert.assertAll();
	}

	@Test
	public void wait_and_click() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='submitButton']")));
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
	}

	@AfterClass
	public void Closebrowser() {
		driver.quit();
	}

}
