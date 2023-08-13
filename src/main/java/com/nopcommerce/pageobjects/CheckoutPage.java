package com.nopcommerce.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.nopcommerce.utilities.Utility;

public class CheckoutPage extends Utility{
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath="//div[@class='page-title']/h1") WebElement pageheading;
	
	// Billing tab elements
	@FindBy (xpath="//h2[normalize-space()='Billing address']") WebElement billingaddresstab;
	@FindBy (xpath="//input[@id='BillingNewAddress_FirstName']") WebElement fname;
	@FindBy (xpath="//input[@id='BillingNewAddress_LastName']") WebElement lname;
	@FindBy (xpath="//input[@id='BillingNewAddress_Email']") WebElement email;
	@FindBy (xpath="//select[@id='BillingNewAddress_CountryId']") WebElement country;
	@FindBy (xpath="//input[@id='BillingNewAddress_City']") WebElement city;
	@FindBy (xpath="//input[@id='BillingNewAddress_Address1']") WebElement address1;
	@FindBy (xpath="//input[@id='BillingNewAddress_ZipPostalCode']") WebElement postalcode;
	@FindBy (xpath="//input[@id='BillingNewAddress_PhoneNumber']") WebElement phone_number;
	@FindBy (xpath="//button[@onclick='Billing.save()']") WebElement billingcontinuebutton;
	
	@FindBy (xpath="//div[@class='section select-billing-address']/label") WebElement selectaddresstext;
	@FindBy (xpath="//input[@id='ShipToSameAddress']") WebElement shippingcheckbox;
	@FindBy (xpath="//select[@id='billing-address-select']") WebElement select_address;
	@FindBy (xpath="//button[@onclick='Billing.save()']") WebElement billing_continue_button;
	@FindBy (xpath="//button[@id='edit-billing-address-button']") WebElement billing_edit_button;
	
	// Shipping method
	@FindBy (xpath="//li[@id='opc-shipping_method']//div[@class='step-title']") WebElement shippingtab;
	@FindBy (xpath="//input[@id='shippingoption_0']") WebElement groundradiobutton;
	@FindBy (xpath="//label[normalize-space()='Ground ($0.00)']") WebElement groundtext;
	@FindBy (xpath="//button[@class='button-1 shipping-method-next-step-button']") WebElement shippingcontinuebutton;
	
	@FindBy (css="li[id='opc-payment_method'] h2[class='title']") WebElement paymentmethodtab;
	@FindBy (xpath="//input[@id='paymentmethod_1']") WebElement creditcardradiobutton;
	@FindBy (xpath="//label[normalize-space()='Credit Card']") WebElement creditcardtext;
	@FindBy (xpath="//button[@class='button-1 payment-method-next-step-button']") WebElement paymentcontinuebutton;
	
	// Payment information
	@FindBy (xpath="//h2[normalize-space()='Payment information']") WebElement paymentinfotab;
	@FindBy (xpath="//select[@id='CreditCardType']") WebElement selectcardtype;
	@FindBy (xpath="//input[@id='CardholderName']") WebElement cardholdername;
	
	@FindBy (xpath="//input[@id='CardNumber']") WebElement cardnumber;
	
	@FindBy (xpath="//select[@id='ExpireMonth']") WebElement expiry_month;
	
	@FindBy (xpath="//select[@id='ExpireYear']") WebElement expiry_year;
	@FindBy (xpath="//input[@id='CardCode']") WebElement cardcode;
	@FindBy (xpath="//button[@class='button-1 payment-info-next-step-button']") WebElement payment_continuebutton;
	
	// Get all entered details
	@FindBy (xpath="//div[@class='billing-info']//ul[@class='info-list']/li") List<WebElement> billingdetails;
	@FindBy (xpath="//div[@class='shipping-info']//ul[@class='info-list']/li") List<WebElement> shippingdetails;
	@FindBy (xpath="//table[@class='cart']/tbody/tr/td[@class='product']/a") WebElement pdname;
	@FindBy (xpath="//tr[@class='order-total']//td[@class='cart-total-right']") WebElement carttotalvalue;
	
	@FindBy (xpath="//button[normalize-space()='Confirm']") WebElement confirmbutton;
	
	@FindBy (xpath="//div[@class='page-title']/h1") WebElement thankyoumsg;
	@FindBy (xpath="//div[@class='section order-completed']//div[@class='title']") WebElement ordersuccessmsg;
	
	
	public String getcheckoutpageheading()
	{
		return pageheading.getText();
	}
	
	public boolean verify_billing_tab()
	{
		return billingaddresstab.isDisplayed();
	}
	
	//If billing address already added
	public boolean validate_ship_to_same_address_checkbox()
	{
		return shippingcheckbox.isSelected();
	}
	
	public String get_select_billing_address_text()
	{
		return selectaddresstext.getText();
	}
	
	public void select_address_from_dropdown(String Value)
	{
		selectoptionfromdropdown(select_address, Value);
	}
	
	public void click_on_billing_continue_button()
	{
		billing_continue_button.click();
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
	
	public void click_on_continue_button()
	{
		billingcontinuebutton.click();
		
		WaitForElementToDisplay(driver,shippingtab);
	}
	
	public boolean verify_ground_transport_selected()
	{
		return groundradiobutton.isSelected();
	}
	
	public String getshippingmethodtext()
	{
		return groundtext.getText();
	}
	
	public void click_shipping_continue_button()
	{
		shippingcontinuebutton.click();
	}
	
	public boolean verify_payment_method_tab_visible()
	{
		return paymentmethodtab.isDisplayed();
	}
	
	public void click_on_credit_card_option()
	{
		creditcardradiobutton.click();
	}
	
	public boolean validate_credit_card_option_selected()
	{
		return creditcardradiobutton.isSelected();
	}
	
	public void click_on_paymentmethod_continue_button()
	{
		paymentcontinuebutton.click();
	}
	
	public void entercreditcarddetails(String cardtype, String cardname, String card_number, String month, String year, String code)
	{
		selectoptionfromdropdown(selectcardtype,cardtype);
		
		cardholdername.sendKeys(cardname);
		cardnumber.sendKeys(card_number);
		
		selectoptionfromdropdown(expiry_month,month);
		selectoptionfromdropdown(expiry_year,year);
		
		cardcode.sendKeys(code);
	}
	
	public void click_payment_continue_button()
	{
		payment_continuebutton.click();
	}
	
	public void click_on_confirm_button()
	{
		confirmbutton.click();
	}
	
	public String getThankyoumsg()
	{
		return ordersuccessmsg.getText();
	}
	
	public boolean validate_success_message_visible()
	{
		return ordersuccessmsg.isDisplayed();
	}
	
	
	public boolean validate_billing_data(String value1)
	{
		//Get all billing data into a list and read the values from the billingdetails list
		
		for(WebElement options:billingdetails)
		{
			String actualdata = options.getText();
			String data= actualdata.substring(actualdata.lastIndexOf(":")+1);
			//System.out.println(data);
			
			if(value1.contains(data.trim()))
			{
	    		 System.out.println("Data name matches "+data+" with "+value1);
	    		 break;
	    	 }
		}
		
		return true;
	}
	
	public String validate_product_name()
	{
		return pdname.getText();
	}
	
	public String validate_cart_total()
	{
		return carttotalvalue.getText();
	}
	
	
	//Unused methods
	
	/*public void validate_billingdetails_using_json_array() throws IOException
	{
		try 
		
		{
		// Read JSON data
	    String jsonData = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"//src//test//java//com//nopcommerce//testresources//BillingDetails.json")));
	    JSONArray jsonArray = new JSONArray(jsonData);
	    
	    //Loop through Json data and compare with web page
	    for(int i=0; i< jsonArray.length(); i++)
	    {
	    	 JSONObject jsonObject = jsonArray.getJSONObject(i);
	    	 
	    	 String expectedEmail = jsonObject.getString("email");
	    	 //String expectedName = jsonObject.getString("firstname");
	    	 
	    	 WebElement billing =  billingdetails.get(i+1);
	    	 String actual_billing_data = billing.getText();
	    	 
	    	 String data= actual_billing_data.substring(actual_billing_data.lastIndexOf(":")+1);
	    	 
	    	 if(expectedEmail.equals(data.trim()))
	    	 {
	    		 System.out.println("Data name matches"+data+" with "+expectedEmail);
	    	 }
	    	 else
	    	 {
	    		 System.out.println("Data not matches "+data+"with "+expectedEmail);
	    	 }
	    }
	}
	
	catch (Exception e) 
	{
		e.printStackTrace();
	}
	
	}*/

}
