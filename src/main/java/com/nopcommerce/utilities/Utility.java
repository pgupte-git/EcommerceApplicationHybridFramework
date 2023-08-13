package com.nopcommerce.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {
	
	public static final int Implicit_wait_time=10;
	public static final int page_load_time=5;
	
	public static WebDriver driver;
	
	//##### Method to generate new Email with timestamp #####//
	public static String generateEmailwithTimestamp()
	{
		Date date = new Date();
		String timestamp = date.toString().replace(" ","_").replace(":","_");
		return "pgupte"+timestamp+"@gmail.com";
	}
	
	// Generate random string email address eg: abcxyz@gmail.com
	public static String generateRandomEmail()
	{
		return RandomStringUtils.randomAlphabetic(8) + "@gmail.com";
	}
	
	//##### Method to select value from the select dropdown ####//
	
	public static void selectoptionfromdropdown(WebElement locator, String value)
	{
		Select drpelement = new Select(locator);
		
		List<WebElement>allOptions = drpelement.getOptions();
		
		for(WebElement option:allOptions)
		{
			//System.out.println(option.getText());
			if(option.getText().equals(value))
			{
				option.click();
			}
		}
	}
	
	
	//########## Method to Select data from Excel Sheet #########//
	public static Object[][] getDatafromExcelsheet(String sheetname) throws IOException
	{
		File excelfile = new File(System.getProperty("user.dir")+"//src//test//java//com//nopcommerce//testresources//TestDataForNopCommerce.xlsx");
		//XSSFWorkbook workbook = null;
		
		FileInputStream fisSheet = new FileInputStream(excelfile);
		
		XSSFWorkbook workbook = new XSSFWorkbook(fisSheet);
		
		/*try
		{
			FileInputStream fisSheet = new FileInputStream(excelfile);
			workbook = new XSSFWorkbook(fisSheet);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}*/
		
		
		XSSFSheet sheet = workbook.getSheet(sheetname);
		
		int rows = sheet.getLastRowNum();
		int columns = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][columns];
		
		for(int i=0; i<rows;i++)
		{
			XSSFRow row= sheet.getRow(i+1); // Read the data from second row while skipping header
			
			for(int j=0; j<columns; j++)
			{
				XSSFCell cell=row.getCell(j);
				CellType celltype = cell.getCellType();
				
				switch(celltype) {
				
				case STRING:
					data[i][j] = cell.getStringCellValue();
					System.out.println("Cell value:"+data[i][j]);
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				default:
					System.out.println("No cell data found");
					break;
				}
				
			}
		}
		
		return data;
	}
	
	// Method to select value from auto complete dropdown
	public static void select_value_auto_complete_dropdown(WebElement locator, String value)
	{
		String text;
				
				do
				{
					
					locator.sendKeys(Keys.ARROW_DOWN);
					
					text = locator.getAttribute("value");
					
					if(text.equals(value))
					{
						locator.sendKeys(Keys.ENTER);
						System.out.println("Selected value "+text);
						break;
					}
					
				}while(!text.isEmpty());
	}
	
	
	public static String getscreenshotoffullpage(WebDriver driver,String testcasename) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		
		String destinationfile = System.getProperty("user.dir")+"\\screenshot\\"+testcasename+".png";
		
		File target = new File(destinationfile);
		FileUtils.copyFile(source,target);
		return (destinationfile);
	}
	
	//## Add border outline to an element
	
	public static void drawBorder(WebDriver driver, WebElement locator)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].style.border='3px solid red'", locator);
	}
	
	public static void scrolldownByVisibleElement(WebDriver driver, WebElement locator) 
	{
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",locator);
    }
	
	// Perform Action method for mouse hover and click on element
	public static void perform_mousehover(WebDriver driver, WebElement primarymenulocator, WebElement menuitemlocator)
	{
		Actions act = new Actions(driver);
		act.moveToElement(primarymenulocator).moveToElement(menuitemlocator).click().perform();
	}
	
	public static void perform_mousehover_Onsingle_element(WebDriver driver, WebElement locator)
	{
		Actions act = new Actions(driver);
		act.moveToElement(locator).click().perform();
	}
	
/////////## Method for explicitly Wait ## /////////////////////////////
	
	public static WebElement WaitForElementToVisible(WebDriver driver, By locator, int time)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return driver.findElement(locator);
	}
	
	public static WebElement WaitForElementToPresnet(WebDriver driver, By locator, int time)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return driver.findElement(locator);
	}
	
	public static WebElement WaitForElementToDisappear(WebDriver driver, By locator, int time)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		return driver.findElement(locator);
	}
	
	public boolean WaitForTexttoAppear(WebElement locator, String value)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.textToBePresentInElement(locator,value));
	}
	
	public static WebElement WaitForElementToDisplay(WebDriver driver, WebElement locator)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.visibilityOf(locator));
	}
	
	public static void waitfor2sec() throws InterruptedException
	{
		Thread.sleep(2000);
	}

}
