package com.nopcommerce.testutility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.FileHandler;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.nopcommerce.pageobjects.HomePage;
import com.nopcommerce.utilities.Utility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest extends config{
	
	public static WebDriver driver;
	public static String browsername;
	public static String url = System.getProperty("url");
	
	public static Properties dataprop;
	public static Properties commonfileprop;
	
	public BaseTest()
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
	
	
	@BeforeTest(alwaysRun=true)
	public void initializebrowser()
	{
		// If browser is not selected then chrome will be launched by default
		browsername = System.getProperty("browser") !=null ? System.getProperty("browser")
				:System.getProperty("browser","chrome"); 
		
		if(browsername.contains("chrome"))
		{
			driver = WebDriverManager.chromedriver().create();
			System.out.println("Chrome browser is selected");
		}
		
		else if(browsername.contains("firefox"))
		{
			driver = WebDriverManager.firefoxdriver().create();
			System.out.println("Firefox browser is selected");
		}
		
		else if(browsername.contains("edge"))
		{
			driver = WebDriverManager.edgedriver().create();
			System.out.println("Edge browser is selected");
		}
		
		else
		{
			System.out.println("No browser is available, so chrome will is selected by default");
		}
		
		
		
		System.out.println("---------- Driver Initiated ---------- ");
		System.out.println("Running on Thread = " + Thread.currentThread().getName());
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utility.Implicit_wait_time));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utility.page_load_time));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get(url);
		
		
		
		//return driver;
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
		
	
	/*public HomePage setup()
	{
		driver = initializebrowser();
		
		
		HomePage home = new HomePage(driver);
		return home;
		
	}*/
	
	
	@AfterTest
	public void teardown()
	{
		driver.quit();
		System.out.println("Parent browser is closed");
	}

}
