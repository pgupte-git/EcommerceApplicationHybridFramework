package com.nopcommerce.pageobjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nopcommerce.utilities.Utility;

public class ProductListingPage extends Utility{
	
	WebDriver driver;
	
	public ProductListingPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	
	@FindBy (css="div[class='page-title'] h1") WebElement ShoesTitle;
	
	@FindBy (css=".product-item") List<WebElement> Productlists;
	
	@FindBy (xpath="//h2[@class='product-title']//a[contains(text(),'adidas Consortium Campus 80s Running Shoes')]")
	WebElement prodname;
	
	public String getpageheading()
	{
		return ShoesTitle.getText();
	}
	
	public void wait_for_product_heading()
	{
		WaitForElementToDisplay(driver, ShoesTitle);
	}
	
	public void scrolltoproductelement()
	{
		scrolldownByVisibleElement(driver, prodname);
	}
	
	
	public Map<String, WebElement> getProductElements()
	{
		Map<String,WebElement> productMap = new HashMap<>();
		
		for(WebElement productElement : Productlists)
		{
			WebElement productNameElement = productElement.findElement(By.cssSelector(".product-title"));
			String productName = productNameElement.getText();
			productMap.put(productName, productElement);
		}
		
		return productMap;
	}
	
	public ProductDetailPage clickonproductname(String productname)
	{
		Map<String,WebElement> productMap = getProductElements();
		WebElement desiredProductElement = productMap.get(productname);
		//System.out.println(desiredProductElement);
		
		if(desiredProductElement!=null)
		{
			desiredProductElement.click();
		}
		else
		{
			System.out.println("Product not found: " + productname);
		}
		
		return new ProductDetailPage(driver);
	}
	
}
