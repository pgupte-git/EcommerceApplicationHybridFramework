package com.nopcommerce.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageobjects.HomePage;
import com.nopcommerce.pageobjects.SearchPage;
import com.nopcommerce.testutility.BaseTest;
import com.nopcommerce.testutility.BaseTest2;

public class SearchProductTest extends BaseTest2{
	
	HomePage home;
	SearchPage searchp;
	//static WebDriver driver;
	
	public SearchProductTest()
	{
		super();
	}
	
	@Test(priority=1,groups="search")
	public void verify_search_functionality() throws InterruptedException, IOException
	{
		//home = new HomePage(driver);
		
		searchp = new SearchPage(getDriver());
		
		searchp.clickonSearchTextInput();
		Thread.sleep(2000);
		
		searchp.entersearchValue("Digital");
		Thread.sleep(2000);
		
		searchp.select_value_from_dropdown("Digital Storm VANQUISH 3 Custom Performance PC");
		
		Thread.sleep(2000);
		
		String producttitle = searchp.getProductTitle();
		
		try
		{
			Assert.assertEquals(producttitle, dataprop.getProperty("desktop_pcname"));
		}
		
		catch(AssertionError e)
		{
			searchp.addRedBordertoMessage();
			Assert.assertEquals(producttitle, dataprop.getProperty("desktop_pcname"), "Search result Title not matched");
		}
		
		
		
		
	}
	
	

}
