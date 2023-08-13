package com.nopcommerce.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nopcommerce.utilities.Utility;

public class CartPage extends Utility{
	
	private static final String data = null;
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css="div[class='page-title'] h1") WebElement pageheading;
	@FindBy (xpath="//table[@class='cart']/tbody/tr") List<WebElement> rows;
	@FindBy (xpath="//table[@class='cart']/tbody/tr/td") List<WebElement> columns;
	
	@FindBy (xpath="//table[@class='cart']/tbody/tr/td[@class='product']/a") WebElement pdname;
	@FindBy (xpath="//td[@class='quantity']/input") WebElement prod_quantity;
	@FindBy (xpath="//td[@class='unit-price']") WebElement price;
	@FindBy(xpath="//td[@class='product']/a") WebElement productname;
	@FindBy (xpath="//td[@class='subtotal']") WebElement subtotal;
	
	@FindBy (xpath="//button[@id='updatecart']") WebElement updatecartbutton;
	@FindBy (xpath="//button[normalize-space()='Continue shopping']") WebElement continue_shoppingbutton;
	@FindBy (xpath="//a[@id='open-estimate-shipping-popup']") WebElement estimateshippingbutton;
	
	@FindBy (xpath="//select[@id='checkout_attribute_1']") WebElement giftwrappingdropdown;
	@FindBy (xpath="//div[@class='selected-checkout-attributes']") WebElement giftchargevalue;
	@FindBy (xpath="//table[@class='cart-total']/tbody/tr[@class='order-total']/td[@class='cart-total-right']")
	WebElement carttotal;
	@FindBy (xpath="//input[@id='termsofservice']") WebElement termconditioncheckbox;
	@FindBy (xpath="//div[@id='terms-of-service-warning-box']") WebElement termconditionpopup;
	@FindBy (xpath="//button[normalize-space()='Close']") WebElement closetermconditionicon;
	@FindBy (xpath="//button[@id='checkout']") WebElement checkoutbutton;
	
	By giftcharge = By.xpath("//div[@class='selected-checkout-attributes']");
	
	public String  gettitleofthepage()
	{
		return driver.getTitle();
	}
	
	public String validate_cartpage_heading()
	{
		return pageheading.getText();
	}
	

	@SuppressWarnings("unused")
	public String getvaluesfromcart()
	{
		int row;
		//int col;
		
		row=rows.size();
		//col = columns.size();
		
		for(int r =1; r<=row;r++)
		{
			String data = driver.findElement(By.xpath("//table[@class='cart']/tbody/tr["+r+"]/td[3]/a")).getText();
			return data;
		}
		
		return null;		
		
	}
	
	public String getproductname()
	{
		return getvaluesfromcart();
		//return pdname.getText();
		
	}
	
	public void update_product_quantity(String value)
	{
		prod_quantity.clear();
		prod_quantity.sendKeys(value);
	}
	
	public void click_on_update_shopping_cart()
	{
		updatecartbutton.click();
	}
	
	public String get_subtotal_price()
	{
		String totalprice = subtotal.getText();
		return totalprice;
	}
	
	public void select_gift_wrapping_cost(String value)
	{
		selectoptionfromdropdown(giftwrappingdropdown,value);
		
	}
	
	public String validate_gift_wrapping_cost()
	{
		return giftchargevalue.getText();
	}
	
	public void wait_for_gift_value_to_appear() throws InterruptedException
	{
		waitfor2sec();
	}
	
	public String get_cart_total()
	{
		return carttotal.getText();	
	}
	
	public void clickontermscheckbox()
	{
		termconditioncheckbox.click();
	}
	
	public String validate_term_condition_required_error_msg()
	{
		return termconditionpopup.getText();
	}
	
	public void click_on_close_icon()
	{
		closetermconditionicon.click();
	}
	
	public boolean validate_term_checkbox_is_selected()
	{
		return termconditioncheckbox.isSelected();
	}
	
	public LoginPage clickoncheckoutbutton()
	{
		checkoutbutton.click();
		return new LoginPage(driver);
	}
	
	public CheckoutPage clickon_checkoutbutton()
	{
		checkoutbutton.click();
		return new CheckoutPage(driver);
	}
	
	
	
	
	
	
	

}
