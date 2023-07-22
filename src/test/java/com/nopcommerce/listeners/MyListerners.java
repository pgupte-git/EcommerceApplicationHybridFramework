package com.nopcommerce.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.nopcommerce.testutility.BaseTest;
import com.nopcommerce.testutility.BaseTest2;
import com.nopcommerce.testutility.ExtentLogAppender;
import com.nopcommerce.testutility.ExtentReporter;
import com.nopcommerce.utilities.Utility;
//import com.nopcommerce.utilities.Utility;

public class MyListerners implements ITestListener{
	
	ExtentReports extentReport;
	ExtentTest extentTest;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	
	@Override
	public void onStart(ITestContext context) 
	{
		extentReport = ExtentReporter.generateextentreport();
	}
	
	@Override
	public void onTestStart(ITestResult result) 
	{
		extentTest = extentReport.createTest(result.getMethod().getMethodName());
		test.set(extentTest);
		ExtentLogAppender.setExtentTest(extentTest);
		
		//extentTest.log(Status.INFO, result.getName()+"Started Excuting");
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		//extentTest = extentReport.createTest(result.getName());
		test.get().log(Status.PASS, result.getName()+"succseefully executed");
		
		//extentTest.log(Status.PASS, result.getName()+" succseefully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		WebDriver driver=null;
		
		//retriving the driver object
		try 
		{
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} 
		catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) 
		{
			e.printStackTrace();
		}
		
		System.out.println("Screenshot taken");
		String destinationScreenshot;
		try 
		{
			driver=BaseTest2.getDriver();
			destinationScreenshot = Utility.getscreenshotoffullpage(driver,result.getName());
			/*extentTest.addScreenCaptureFromPath(destinationScreenshot);
			extentTest.log(Status.INFO,result.getThrowable());
			extentTest.log(Status.FAIL,result.getName()+" got failed");*/
			
			test.get().addScreenCaptureFromPath(destinationScreenshot);
			test.get().log(Status.INFO,result.getThrowable());
			test.get().log(Status.FAIL,result.getName()+" got failed");
			test.get().log(Status.FAIL,result.getName()+" got failed");
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		/*extentTest.log(Status.INFO,result.getTestName());
		extentTest.log(Status.SKIP,result.getName()+" got skipped");*/
		
		test.get().log(Status.INFO,result.getTestName());
		test.get().log(Status.SKIP,result.getName()+" got skipped");
		
	}

	@Override
	public void onFinish(ITestContext context) {
			
		extentReport.flush();
		
		String extentReportPath = System.getProperty("user.dir")+"\\test-output\\reports\\extent-report.html";
		File extentReport = new File(extentReportPath);
		
		try 
		{
			Desktop.getDesktop().browse(extentReport.toURI());
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
