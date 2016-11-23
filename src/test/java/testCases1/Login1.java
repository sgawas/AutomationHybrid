package testCases1;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import core1.Core1;
import util1.TestUtil;

public class Login1 extends Core1{

	@Test(dataProvider="getData")
	public static void checkRunMode(Hashtable<String,String> table) throws InterruptedException{
		System.out.println("========================================");
		if(TestUtil.isTCExecutable("Login")){
			System.out.println("pass");
			System.out.println(table.get("RunMode"));
			TestUtil.executeKeywords("Login", table);
		}
		else{
			System.out.println("fail");
		}
	}
	
	@DataProvider
	public static Object[][] getData(){
		
		return TestUtil.getTestData("Login");
	}
}
