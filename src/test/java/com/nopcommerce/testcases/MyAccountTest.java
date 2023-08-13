package com.nopcommerce.testcases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nopcommerce.pageobjects.HomePage;
import com.nopcommerce.pageobjects.MyAccountPage;
import com.nopcommerce.testutility.BaseTest2;

public class MyAccountTest extends BaseTest2{
	
	public MyAccountTest()
	{
		super();
	}
	
	private MyAccountPage accountpage;
	private HomePage homepage;
	
	@Test(priority=1, groups= {"regression","memberuser"}, dataProvider="getData")
	public void verify_my_account_add_newaddress(HashMap<String,String>input)
	{
		homepage = new HomePage(getDriver());
		
		accountpage=homepage.click_on_my_account();
		
		String accounttitle = accountpage.get_myaccount_title();
		Assert.assertEquals(accounttitle,"My account - Customer info","My account title is not matched");
		
		accountpage.click_on_address_link();
		
		Assert.assertTrue(accountpage.validate_no_address_text(), "The no address message is not visible");
		
		accountpage.click_add_new_address_button();
		
		accountpage.enterbillinginformation(input.get("firstname"), input.get("lastname"), input.get("email"), input.get("country"), input.get("city"), input.get("address"), 
				input.get("postalcode"), input.get("phone"));
		
		accountpage.click_on_save_button();
		
		//Assert.assertEquals(accountpage.get_success_message().contains("been added successfully."),"Success msg is not visible");
		
		Assert.assertTrue(accountpage.validate_address_data(input.get("firstname")), "Firstname not matched");
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data = getJsondatatoMap(System.getProperty("user.dir")+dataprop.getProperty("billingdetailsfilepath"));
		return new Object[][] {{data.get(0)}};
	}
	

}
