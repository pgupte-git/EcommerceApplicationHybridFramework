package com.nopcommerce.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nopcommerce.utilities.Utility;

public class RegisterPage extends Utility{
	
	WebDriver driver;
	
	// HomePage Constructor to initialize driver and pagefactory 
	public RegisterPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy (xpath="//input[@id='gender-male']") WebElement maleradiobutton;
	@FindBy (xpath="//input[@id='gender-female']") WebElement femaleradiobutton;
	@FindBy (xpath="//input[@id='FirstName']") WebElement fname;
	@FindBy (xpath="//input[@id='LastName']") WebElement lname;
	@FindBy (xpath="//select[@name='DateOfBirthDay']") WebElement day;
	@FindBy (xpath="//select[@name='DateOfBirthMonth']") WebElement month;
	@FindBy (xpath="//select[@name='DateOfBirthYear']") WebElement year;
	
	@FindBy (xpath="//input[@id='Email']") WebElement email;
	@FindBy (xpath="//input[@id='Password']") WebElement password;
	@FindBy (xpath="//input[@id='ConfirmPassword']") WebElement confirmpassword;
	
	@FindBy (xpath="//button[@id='register-button']") WebElement registerbutton;
	
	@FindBy (xpath="//div[@class='result']") WebElement registration_successmsg;
	@FindBy (xpath="//a[normalize-space()='Continue']") WebElement continuebutton;
	
	// Actions
	
	public boolean selectmalegenderradiobutton()
	{
		maleradiobutton.click();
		return maleradiobutton.isSelected();
	}
	
	public boolean selectFemalegenderradiobutton()
	{
		femaleradiobutton.click();
		return femaleradiobutton.isSelected();
	}
	
	public void addfirstnameandlastname(String value1, String value2)
	{
		fname.sendKeys(value1);
		lname.sendKeys(value2);
	}
	
	public void selectdateofbirth(String value1, String value2, String value3)
	{
		selectoptionfromdropdown(day, value1);
		selectoptionfromdropdown(month, value2);
		selectoptionfromdropdown(year, value3);
	}
	
	public void addEmailaddress(String value)
	{
		email.sendKeys(value);
	}
	
	public void addpassword(String value1)
	{
		password.sendKeys(value1);
		confirmpassword.sendKeys(value1);
	}
	
	public void clickonregisterbutton()
	{
		registerbutton.click();
	}
	
	public String getRegisterSuccessMessage()
	{
		return registration_successmsg.getText();
	}
	
	public boolean validateRegisterSuccessMessage()
	{
		return registration_successmsg.isDisplayed();
	}
	
	public HomePage clickoncontinuebutton()
	{
		continuebutton.click();
		return new HomePage(driver);
	}
	
	
	
	
	
	
	

}
