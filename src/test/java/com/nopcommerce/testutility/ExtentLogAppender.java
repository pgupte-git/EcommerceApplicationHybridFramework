package com.nopcommerce.testutility;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ExtentLogAppender extends AppenderSkeleton{

	private static ExtentTest extentTest;
	
	public static void setExtentTest(ExtentTest test) {
        extentTest = test;
    }
	
	
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean requiresLayout() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void append(LoggingEvent event) {
		String logMessage = event.getRenderedMessage();
        if (event.getLevel().isGreaterOrEqual(org.apache.log4j.Level.ERROR)) {
            extentTest.log(Status.FAIL, logMessage);
        } else if (event.getLevel().isGreaterOrEqual(org.apache.log4j.Level.WARN)) {
            extentTest.log(Status.WARNING, logMessage);
        } else if (event.getLevel().isGreaterOrEqual(org.apache.log4j.Level.INFO)) {
            extentTest.log(Status.INFO, logMessage);
        } else {
            extentTest.log(Status.FAIL, logMessage);
        }
		
	}

}
