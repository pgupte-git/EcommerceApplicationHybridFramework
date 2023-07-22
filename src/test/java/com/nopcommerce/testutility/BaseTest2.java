package com.nopcommerce.testutility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class BaseTest2 extends config{
	
	//used ThreadLocal java class for running the tests in parallel execution without any error 
	protected static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
	
	public static String browsername=System.getProperty("browser");
	public static String url = System.getProperty("url");
	
	public static Properties dataprop;
	public static Properties commonfileprop;
	public WebDriver driver;
	public static Logger logger;
	
	public BaseTest2()
	{
		dataprop = new Properties();
		File datafile = new File(System.getProperty("user.dir")+"//src//test//java//com//nopcommerce//testresources//testdata.properties");
		try 
		{
			FileInputStream fis = new FileInputStream(datafile);
			dataprop.load(fis);
		} 
		catch (Throwable e) 
		{
			e.printStackTrace();
		}
		
		
		commonfileprop = new Properties();
		File commonfilepath = new File(System.getProperty("user.dir")+"//src//test//java//com//nopcommerce//testresources//CommonFilePath.properties");
		try 
		{
			FileInputStream fis = new FileInputStream(commonfilepath);
			commonfileprop.load(fis);
		} 
		catch (Throwable e) 
		{
			e.printStackTrace();
		}
		
	}
	
	@BeforeTest
	public void setup()
	{
		//Assign the WebDriver instance from the browserManager class and assigned to base test local driver
		driver = BrowserManager.initializeBrowser(browsername);
		
		//set Driver
		threadLocalDriver.set(driver);
		
		 System.out.println("Before Test Thread ID: "+Thread.currentThread().getId());
		
		 /*url=System.getProperty("url") !=null ? System.getProperty("url")
					:System.getProperty("url","https://demo.nopcommerce.com/");*/
		 //get URL
	     getDriver().get(url);
	     
	     logger = Logger.getLogger("NopCommerce Website");
	     PropertyConfigurator.configure(System.getProperty("user.dir")+"\\src\\main\\java\\com\\nopcommerce\\resources\\log4j.properties");
	     logger = Logger.getLogger(getClass());
		
	}
	
	//get thread-safe driver
    public static WebDriver getDriver(){
        return threadLocalDriver.get();
    }
    
    @AfterTest
    public void tearDown(){
        
    	getDriver().quit();
    	System.out.println("Parent browser is closed");
    	System.out.println("After Test Thread ID: "+Thread.currentThread().getId());

        threadLocalDriver.remove();
    }
    
  //Method to read the data from json files
  	public List<HashMap<String,String>> getJsondatatoMap(String Filepath) throws IOException
  	{
  			//read file from json to string
  			String jsonContent=FileUtils.readFileToString(new File(Filepath),StandardCharsets.UTF_8);
  			
  			// String to HashMap Jackson Databind
  			ObjectMapper mapper = new ObjectMapper();
  			
  			List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
  			
  			return data;
  		
  	}
	

}
