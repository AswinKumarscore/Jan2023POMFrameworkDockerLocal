package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

//PAGE SHOULD NOT HAVE ANY ASSERTIONS
//EVERY PAGE CLASSES WILL HAVE CONCEPT OF ENCAPSULATION
public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1.Constructor of the Page Class
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver); 
	}

	// 2.private By locators:
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdlink = By.linkText("Forgotten Password11");
	private By footerlinks = By.xpath("//footer//a");
	
	private By logoImage = By.xpath("//img[contains(@src,'opencart/image/catalog/opencart-logo.png')]");
	private By searchTabXpath = By.xpath("//input[@name='search']");
	private By returningCustomerLabel = By.xpath("//div/h2[text()='Returning Customer']");
	private By newCustomerLabel = By.xpath("//div/h2[text()='New Customer']");
	
	private By loginErrorMessage =By.xpath("//div[@class='alert alert-danger alert-dismissible']");
	
	private By registerLink = By.linkText("Register");

	// 3.public Page actions.methods
	
	@Step("getting login page tite")
	public String getLoginPageTitle() {
		return eleUtil.waitForTitleIsAndCapture(AppConstants.LOGIN_PAGE_TITLE_VALUE,AppConstants.SHORT_DEFAULT_WAIT);
	}

	@Step("getting login page URL")
	public String getLoginPageURL() {
		return eleUtil.waitForURLContainsAndCapture(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE,AppConstants.SHORT_DEFAULT_WAIT);
		
	}
	
	@Step("checking forgot password link exist on the login page")
	public boolean isForgotPwdLinkExist() {
	    return eleUtil.checkElementIsDisplayed(forgotPwdlink);
	}
	
	@Step("getting footer links")
	public  List<String> getFooterLinksList()
	{
		List<WebElement> footerLinksList=eleUtil.waitForElementsVisible(footerlinks,AppConstants.MEDIUM_DEFAULT_WAIT);
		List<String> footerTextList = new ArrayList<String>();
		for(WebElement e:footerLinksList)
		{
			String text = e.getText();
			footerTextList.add(text);
		}
//		System.out.println("Printing Footer textList ");
//		for(String e:footerTextList)
//		{
//			System.out.println(e);
//		}
		return footerTextList;
		
	}

	
	//PageChaining MODEL:
	@Step("login with username {0}, and password {1}")
	public AccountsPage doLogin(String userName,String pwd)
	{
		eleUtil.waitForElementVisible(emailId,AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
		//return the next landing page --AccountsPage -- page chaining model
	}
	
	@Step("Login with incorrect username and incorrect password...")
	public boolean doLoginWithWrongCredentials(String userName,String pwd)
	{
		System.out.println("Wrong Credentials are :"+userName+" "+pwd);
		eleUtil.waitForElementVisible(emailId,AppConstants.MEDIUM_DEFAULT_WAIT);
		eleUtil.doSendKeys(emailId, userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		String actualErrorMessage = eleUtil.doGetElementText(loginErrorMessage);
		System.out.println(actualErrorMessage);
		if(actualErrorMessage.contains(AppConstants.LOGIN_ERROR_MESSAGE))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//ASSIGNMENT:
	public boolean isLogoImagePresent() {
		return eleUtil.checkElementIsDisplayed(logoImage);
	    //return driver.findElement(logoImage).isDisplayed();
	}
	
	//ASSIGNMENT:
	public boolean isSearchTabPresent() {
		return eleUtil.checkElementIsDisplayed(searchTabXpath);
	    //return driver.findElement(searchTabXpath).isDisplayed();
	}
	
	//ASSIGNMENT:
	//Is returning customer label present or not
	public boolean isReturningCustomerLabelPresent() {
		return eleUtil.checkElementIsDisplayed(returningCustomerLabel);
	    //return driver.findElement(returningCustomerLabel).isDisplayed();
	}
	
	//ASSIGNMENT:
	//New Customer label is present or not
	public boolean isNewCustomerLabelPresent() {
		return eleUtil.checkElementIsDisplayed(newCustomerLabel);
		    //return driver.findElement(newCustomerLabel).isDisplayed();
	}
	
	public RegisterPage navigateToRegisterPage()
	{
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	

		
		
	
	

}
