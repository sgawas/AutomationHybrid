package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import core.Core;

public final class Common {
	private static String timeStamp = null;

	private static void setTimeStamp() {
		long timeInMillis = System.currentTimeMillis();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timeInMillis);
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyyHHmmss");
		timeStamp = dateFormat.format(calendar.getTime());
	}

	public static String getTimeStamp() {
		setTimeStamp();
		return timeStamp;
	}

	private static void captureScreenshot() {
		String mailScreenshotPath = null;
		String timeStamp = getTimeStamp();
		String screenshotPath = Core.config.getProperty("screenshotPath") + timeStamp;
		File scrFile = ((TakesScreenshot) Core.driver).getScreenshotAs(OutputType.FILE);
		try {
			mailScreenshotPath = screenshotPath + ".jpeg";
			FileUtils.copyFile(scrFile, new File(mailScreenshotPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void takeScreenshot(){
		captureScreenshot();
	}
	
	public static void copyLogFile(String logFile,String newLogPath) throws IOException{
		String timeStamp = getTimeStamp();
		String logPath = newLogPath+timeStamp+".txt";
		FileInputStream fileIn = null;
		FileOutputStream fileOut = null;
		try{
			File file = new File(logFile );
			File tempFile = new File(logPath);
			fileIn = new FileInputStream(file);
			fileOut = new FileOutputStream(tempFile);
			int length =0;
			byte[] buffer = new byte[1024];
			while( (length = fileIn.read(buffer)) >0){
				fileOut.write(buffer, 0, length);
			}
			
		}catch(Exception e){
			
		}
		finally{
			fileIn.close();
			fileOut.close();
		}
	}
	
}
