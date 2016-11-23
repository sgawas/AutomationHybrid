package testCases;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import util.Utils;

public class Login {
	
	@Test(dataProvider="getData")
	public static void LoginTC(Hashtable<String,String> table){
		if(Utils.isTCExecutable("Login")){
			util.Utils.executeKeyword("", table);
		}
		else{
			throw new SkipException("Login TC runmode set to No");
		}
	}
	
	@DataProvider
	public static Object[][] getData(){
		System.out.println("1");
		return Utils.getTestData("Login");
		
	}
}
