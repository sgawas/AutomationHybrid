package util1;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import core1.Core1;

public class Common {
	private String time=null;
	
	private void setTimeStamp(){
		long timeStamp = System.currentTimeMillis();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timeStamp);
		SimpleDateFormat date = new SimpleDateFormat("MMddyyyyHHmmss");
		time = date.format(calendar.getTime());
	}
	
	public String getTimeStamp(){
		setTimeStamp();
		return time; 
	}
	
	private void captureScreenshot(String path){
		File scrFile = ((TakesScreenshot) Core1.driver).getScreenshotAs(OutputType.FILE);
		try{
			FileUtils.copyFile(scrFile, new File(path));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getScreenshotCapture(String screenshotPath){
		captureScreenshot(screenshotPath);
	}
}
