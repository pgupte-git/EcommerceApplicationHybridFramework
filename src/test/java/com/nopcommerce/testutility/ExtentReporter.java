package com.nopcommerce.testutility;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter extends BaseTest2{
	
	public ExtentReporter()
	{
		super();
	}
	
	public static ExtentReports generateextentreport()
	{
		ExtentReports extentreport = new ExtentReports();
		File reportpath = new File(System.getProperty("user.dir")+commonfileprop.getProperty("extentReportPath"));
		ExtentSparkReporter sparkreporter = new ExtentSparkReporter(reportpath);
		
		sparkreporter.config().setTheme(Theme.DARK);
		sparkreporter.config().setReportName("NopCommerce TestAutomation Report");
		sparkreporter.config().setDocumentTitle("NOP Commerce Automation Report");
		sparkreporter.config().setTimeStampFormat("dd/MM/yyyy HH:mm:ss");
		sparkreporter.config().setEncoding("utf-8");
		
		extentreport.attachReporter(sparkreporter);
		
		extentreport.setSystemInfo("Application URL",url);
		extentreport.setSystemInfo("Browsername", browser);
		extentreport.setSystemInfo("Operating System",System.getProperty("os.name"));
		extentreport.setSystemInfo("Java Version", System.getProperty("java.version"));
		extentreport.setSystemInfo("Username", System.getProperty("user.name"));
		extentreport.setSystemInfo("Time",System.getProperty("user.dir"));
		
		
		return extentreport;
		
	}

}
