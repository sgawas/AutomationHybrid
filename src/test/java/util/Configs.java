package util;

import java.io.IOException;

import core.Core;

public class Configs {

	private static String SERVER;
	private static String FROM_EMAIL;
	private static String PASSWORD;
	private static String[] TO_EMAIL;
	private static String SUBJECT;
	private static String MESSAGE_BODY;
	private static String attachmentPath=System.getProperty("user.dir") + "\\src\\test\\java\\properties\\report2.html";
	private static String attachmentName="Report.html";

	// SQL DATABASE DETAILS
	private static String DRIVER = "net.sourceforge.jtds.jdbc.Driver";
	private static String DB_CONNECTION_URL = "jdbc:jtds:sqlserver://192.101.44.22;DatabaseName=monitor_eval";
	private static String DB_USERNAME = "sa";
	private static String DB_PASSWORD = "$ql$!!1";

	// MYSQL DATABASE DETAILS
	private static String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	private static String MYSQL_USERNAME = "root";
	private static String MYSQL_PASSWORD = "selenium";
	private static String MYSQL_URL = "jdbc:mysql://localhost:3306/gaurav";

	
	private static void setSERVER() {
		SERVER = Core.config.getProperty("SERVER");
	}
	
	private static void setFROM_EMAIL() {
		FROM_EMAIL = Core.config.getProperty("FROM_EMAIL");
	}

	private static void setPASSWORD() {
		PASSWORD = Core.config.getProperty("PASSWORD");
	}

	private static void setSUBJECT() {
		SUBJECT = Core.config.getProperty("SUBJECT");
	}

	private static void setMESSAGE_BODY() {
		MESSAGE_BODY = Core.config.getProperty("MESSAGE_BODY");
	}

	private static void setTO_EMAIL() {
		TO_EMAIL= Core.config.getProperty("TO_EMAIL").split(",");
	}

	public static String getSERVER() {
		setSERVER();
		return SERVER;
	}

	public static String getFROM_EMAIL() {
		setFROM_EMAIL();
		return FROM_EMAIL;
	}

	public static String getPASSWORD() {
		setPASSWORD();
		return PASSWORD;
	}

	public static String[] getTO_EMAIL() {
		setTO_EMAIL();
		return TO_EMAIL;
	}

	public static String getSUBJECT() {
		setSUBJECT();
		return SUBJECT;
	}

	public static String getMESSAGE_BODY() {
		setMESSAGE_BODY();
		return MESSAGE_BODY;
	}
	
	public static String getAttachmentPath() {
		return attachmentPath;
	}

	public static String getAttachmentName() {
		return attachmentName;
	}

	public static String getDRIVER() {
		return DRIVER;
	}

	public static String getDB_CONNECTION_URL() {
		return DB_CONNECTION_URL;
	}

	public static String getDB_USERNAME() {
		return DB_USERNAME;
	}

	public static String getDB_PASSWORD() {
		return DB_PASSWORD;
	}

	public static String getMYSQL_DRIVER() {
		return MYSQL_DRIVER;
	}

	public static String getMYSQL_USERNAME() {
		return MYSQL_USERNAME;
	}

	public static String getMYSQL_PASSWORD() {
		return MYSQL_PASSWORD;
	}

	public static String getMYSQL_URL() {
		return MYSQL_URL;
	}
	public static void main(String arg[]) throws IOException{
		System.out.println("helo");
		Core.initialize();
		System.out.println("hi");
		Configs.getTO_EMAIL();
	}
}
