package com.nopcommerce.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nopcommerce.utilities.Utility;

public class ProductDetailPage extends Utility{
	
	WebDriver driver;
	
	public ProductDetailPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy (css="div[class='product-name'] h1") WebElement ProductTitle;
	
	@FindBy (xpath="//select[@id='product_attribute_6']") WebElement selectSize;
	
	@FindBy (xpath="//select[@id='product_attribute_7']") WebElement selectcolor;
	
	@FindBy (css="label[for='product_attribute_8_20'] span[class='attribute-square']") WebElement selectprint;
	
	@FindBy (xpath="//div[@class='tooltip-container']/div/img[@alt='Fresh']") WebElement imagetooltip;
	
	@FindBy (xpath="//div[@class='overview']") WebElement pagecontainer;
	
	@FindBy (css="button[id='add-to-cart-button-24']") WebElement addtocartbutton;
	
	@FindBy (xpath="//div[@class='bar-notification error']/p") List<WebElement> errormessages;
	
	@FindBy (xpath="//div[@class='bar-notification success']") WebElement productaddedsuccessmsg;
	
	@FindBy (xpath="//a[normalize-space()='shopping cart']") WebElement shoppingcartlink;
	
	
	public String getproducttitle()
	{
		return ProductTitle.getText();
	}
	
	public void selectshoesize(String value)
	{
		selectoptionfromdropdown(selectSize,value);
	}
	
	public void selectshoecolor(String value)
	{
		selectoptionfromdropdown(selectcolor,value);
	}
	
	public void selectshoeprint()
	{
		perform_mousehover_Onsingle_element(driver, selectprint);
	}
	
	public boolean ishoverimagevisible()
	{
		return imagetooltip.isDisplayed();
	}
	
	public void clickanywhereonpage()
	{
		pagecontainer.click();
	}
	
	public void clickonaddtocartbutton()
	{
		addtocartbutton.click();
	}
	
	public String getsuccessmsg()
	{
		return productaddedsuccessmsg.getText();
	}
	
	public CartPage gotoshoppingcart()
	{
		shoppingcartlink.click();
		return new CartPage(driver);
	}
	
	

}
