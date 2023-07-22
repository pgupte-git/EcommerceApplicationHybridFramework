package com.nopcommerce.testutility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.nopcommerce.utilities.Utility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserManager extends config {
	
	public static String browsername=System.getProperty("browser");
	
	
	public static WebDriver initializeBrowser(String browsername)
	{
		WebDriver driver=null;
		
		//chrome options to run the scrips in headlessmode
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless=new"); //This option will run chrome in headless browser
				
		//Firefox options to run the scripts in headless mode
		FirefoxOptions option = new FirefoxOptions();
		option.addArguments("--headless");
		
		browsername = System.getProperty("browser") !=null ? System.getProperty("browser")
				:System.getProperty("browser","chrome");
		
		if(browsername.contains("chrome"))
		{
			//driver = WebDriverManager.chromedriver().create();
			driver = new ChromeDriver(options);
			System.out.println("Chrome browser is selected");
		}
		
		else if(browsername.contains("firefox"))
		{
			//driver = WebDriverManager.firefoxdriver().create();
			driver = new FirefoxDriver(option);
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
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utility.Implicit_wait_time));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utility.page_load_time));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		return driver;
	}
	

}
