package com.nopcommerce.testcases;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
//import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.nopcommerce.pageobjects.HomePage;
import com.nopcommerce.pageobjects.LoginPage;
import com.nopcommerce.pageobjects.RegisterPage;
import com.nopcommerce.testutility.BaseTest;
//import com.nopcommerce.testutility.BaseTest;
import com.nopcommerce.testutility.BaseTest2;
import com.nopcommerce.utilities.Utility;


//@Test(dependsOnGroups = "register")
public class LoginTest extends BaseTest2{
	
	public LoginTest()
	{
		super();
	}
	
	LoginPage login;
	HomePage home;
	RegisterPage register;
	
	ExtentTest extentTest;
	
	private String randomEmail;
	
	/*@BeforeClass
	public void getregisteremail(ITestContext context)
	{
		// Retrieve the registered email from the TestNG context
		randomEmail = (String) context.getAttribute("registeredEmail");
	}*/
	
	@Test(priority=1, groups= {"regression","smoke","sanity","memberuser"})
	public void verifyloginwithValidDataUsingRandomEmails(ITestContext context)
	{
		home = new HomePage(getDriver());
		
		login = home.clickonloginlink();
		logger.info("Click on the Login");
		
		// Retrieve the registered email from the TestNG context
		randomEmail = (String) context.getAttribute("registeredEmail");
		
		
		login.enterusername(randomEmail);
		logger.info("add username");
		login.enterpassword(dataprop.getProperty("loginpassword"));
		logger.info("Added Password");
		login.clickonloginbutton();
		logger.info("Clicked on the Login button");
		
		Assert.assertTrue(home.verifymyaccountlink(),"My account link is not visible");
		logger.info("My Account Link is verified");
		
		System.out.println(home.getmyaccountlink());
		
	}
	
	@Test(priority=2, groups={"smoke"}, dependsOnMethods={"verifyloginwithValidDataUsingRandomEmails"})
	public void verify_logout()
	{
		home.clickonlogout();
		logger.info("Clicked on the Logout Link");
		
		Assert.assertTrue(home.verifyloginlink(), "Login link is not visible");
		logger.info("User is logout");
		
		//System.out.println("User is logout");
	}
	
	
	@Test(priority=3, groups= {"smoke"}, dataProvider = "getData")
	public void verifyloginwithValidDataUsingJSONData(HashMap<String,String>input)
	{
		home = new HomePage(getDriver());
		
		login = home.clickonloginlink();
		
		login.enterusername(input.get("email"));
		login.enterpassword(input.get("password"));
		login.clickonloginbutton();
		
		Assert.assertTrue(home.verifymyaccountlink(),"My account link is not visible");
		
		System.out.println(home.getmyaccountlink());
		
		home.clickonlogout();
		
		Assert.assertTrue(home.verifyloginlink(), "Login link is not visible");
		
		System.out.println("User is logout");
	}
	
	@Test(priority=4, groups= {"errorvalidation"}, dataProvider="nonmemberdata")
	public void verifyloginWithNonmember(String email, String password)
	{
		home = new HomePage(getDriver());
		
		login = home.clickonloginlink();
		
		login.enterusername(email);
		login.enterpassword(password);
		login.clickonloginbutton();
		String message ="No customer account found";
		Assert.assertEquals(login.getloginerrormsg(), message, "Error message is not matched");
	}
	
	@Test(priority=5, groups= {"errorvalidation"},dataProvider="supplyinvaliddata")
	public void verifyLoginWithInvalidEmailandPassword(String email, String password)
	{
		home = new HomePage(getDriver());
		
		login = home.clickonloginlink();
		
		login.enterusername(email);
		login.enterpassword(password);
		login.clickonloginbutton();
		String message ="No customer account found";
		Assert.assertEquals(login.getloginerrormsg(), message, "Error message is not matched");
	}
	
	@Test(priority=6, groups= {"errorvalidation"},dataProvider="supplyinvaliddata")
	public void verifyLoginWithvalidEmailandInvalidPassword(String email, String password)
	{
		login.enterusername(email);
		login.enterpassword(password);
		login.clickonloginbutton();
		String message ="No customer account found";
		Assert.assertEquals(login.getloginerrormsg(), message, "Error message is not matched");
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data = getJsondatatoMap(System.getProperty("user.dir")+dataprop.getProperty("registerdatafilepath"));
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	// Data to read from excel
	@DataProvider(name = "nonmemberdata")
	public Object[][] supplydatafromexcel() throws IOException
	{
		Object[][] data = Utility.getDatafromExcelsheet("Nonmember");
		return data;
	}
	
	@DataProvider
	public Object[][] supplyinvaliddata() throws IOException
	{
		Object[][] data = Utility.getDatafromExcelsheet("InvalidData");
		
		return data;
	}
	
	

}
