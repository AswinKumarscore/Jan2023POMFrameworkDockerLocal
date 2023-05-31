package com.qa.opencart.tests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Stories;
//For LoginPageTest I dont need to Login 
//But for other Tests(AccountPageTest,ResultsPage Test I need to Login)
import io.qameta.allure.Story;

//shortcut Cntrl+O
@Epic("Epic 100:Login Page Design")
@Story("US 101:design Login Page for open cartapp with title,url,forgot "
		+ "pwd links,user is able to login")
//@Stories
public class LoginPageTest extends BaseTest {

	
	@Severity(SeverityLevel.MINOR)
	@Description("checking login page title test......")
	@Feature("title test")
	@Test(priority = 1)
	public void loginPageTitleTest()
	{
		//Cntl+clickon the method shortcut to navigate to particular method
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle,"Account Login");
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("Forgot password Link test......")
	@Feature("Forgot password test")
	@Test(priority = 2)
	public void forgotPwdLinkExistTest()
	{
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description("checking login page URL  test......")
	@Feature("URL test")
	@Test(priority = 3)
	public void loginPageUrlTest()
	{
		String actURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains("route=account/login"));
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Description("checking user is able to login with correct username/password test....")
	@Feature("Login success test")
	@Test(priority = 9)
	public void isloginSuccessfullTest()
	{
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogoutLinkExist());
		
	}
	
	
	
	//ASSIGNMENT:
	@Test(priority = 4)
	public void isLogoExistTest() {
		 Assert.assertTrue(loginPage.isLogoImagePresent());
	}
	
	//ASSIGNMENT:
	@Test(priority = 5)
	public void isSearchTabExistTest() {
		 Assert.assertTrue(loginPage.isSearchTabPresent());
		  
	}
	
	//ASSIGNMENT:
	@Test(priority = 6)
	public void isReturningCustomerLabelExistTest() {
		 Assert.assertTrue(loginPage.isReturningCustomerLabelPresent());
	}
	
	//ASSIGNMENT:
	@Test(priority = 7)
	public void isNewCustomerLabelPresent() {
		 Assert.assertTrue(loginPage.isNewCustomerLabelPresent());
	}
	
	

	

}
