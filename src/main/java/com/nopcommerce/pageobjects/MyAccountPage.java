package com.nopcommerce.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nopcommerce.utilities.Utility;

public class MyAccountPage extends Utility{
	
	WebDriver driver;
	
	public MyAccountPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy (xpath="//div[@class='page-title']") WebElement pageheading;
	@FindBy (xpath="//li[@class='customer-addresses inactive']/a") WebElement addresslink;
	@FindBy (xpath="//div[@class='no-data']") WebElement Addressmsg;
	@FindBy (xpath="//button[@class='button-1 add-address-button']") WebElement addnewbutton;
	
	@FindBy (xpath="//input[@id='Address_FirstName']") WebElement fname;
	@FindBy (xpath="//input[@id='Address_LastName']") WebElement lname;
	@FindBy (xpath="//input[@id='Address_Email']") WebElement email;
	@FindBy (xpath="//select[@id='Address_CountryId']") WebElement country;
	@FindBy (xpath="//input[@id='Address_City']") WebElement city;
	@FindBy (xpath="//input[@id='Address_Address1']") WebElement address1;
	@FindBy (xpath="//input[@id='Address_ZipPostalCode']") WebElement postalcode;
	@FindBy (xpath="//input[@id='Address_PhoneNumber']") WebElement phone_number;
	@FindBy (css="button[class*='save-address-button']") WebElement savebutton;
	@FindBy (xpath="//div[@class='bar-notification success']/p") WebElement successmsg;
	
	@FindBy (xpath="//ul[@class='info']/li") List<WebElement> addressdata;
	
	
	public String get_myaccount_title()
	{
		return pageheading.getText();
	}
	
	public void click_on_address_link()
	{
		addresslink.click();
	}
	
	public String get_no_data_msg()
	{
		return Addressmsg.getText();
	}
	
	public boolean validate_no_address_text()
	{
		return Addressmsg.isDisplayed();
	}
	
	public void click_add_new_address_button()
	{
		addnewbutton.click();
	}
	
	public void enterbillinginformation(String firstname, String lastname, String Email, String contryvalue, 
			String cityval, String address, String postcode, String phonenumber)
	{
		fname.sendKeys(firstname);
		lname.sendKeys(lastname);
		email.sendKeys(Email);
		
		selectoptionfromdropdown(country, contryvalue);
		
		city.sendKeys(cityval);
		address1.sendKeys(address);
		postalcode.sendKeys(postcode);
		phone_number.sendKeys(phonenumber);
	}
	
	public void click_on_save_button()
	{
		savebutton.click();
	}
	
	public String get_success_message()
	{
		return successmsg.getText();
	}
	
	public boolean validate_address_data(String value)
	{
		for(WebElement option:addressdata)
		{
			String actualdata = option.getText();
			String data= actualdata.substring(actualdata.lastIndexOf(":")+1);
			
			if(value.contains(data.trim()))
			{
				System.out.println("Data name matches "+data+" with "+value);
				break;
			}
		}
		
		return true;
		
		
	}
	

}
