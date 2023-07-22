package com.nopcommerce.pageobjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nopcommerce.utilities.Utility;

public class SearchPage extends Utility{
	
	public static WebDriver driver;
	
	@SuppressWarnings("static-access")
	public SearchPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	
	}
	
	
	@FindBy (xpath="//input[@id='small-searchterms']") WebElement searchinput;
	@FindBy (xpath="//button[normalize-space()='Search']") WebElement searchbutton;
	
	@FindBy (xpath="//div[@class='product-name']/h1") WebElement product_title;
	
	public void clickonSearchTextInput()
	{
		searchinput.click();
	}
	
	public void entersearchValue(String value)
	{
		searchinput.clear();
		searchinput.sendKeys(value);
	}
	
	public void select_value_from_dropdown(String value)
	{
		select_value_auto_complete_dropdown(searchinput,value);
	}
	
	public String getProductTitle()
	{
		return product_title.getText();
	}
	
	public void getfullpagescreenhot(String testcasename) throws IOException
	{
		getscreenshotoffullpage(driver,testcasename);
	}
	
	public void addRedBordertoMessage()
	{
		drawBorder(driver, product_title);
	}
	

}
