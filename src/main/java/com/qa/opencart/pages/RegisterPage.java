package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
//1.Constructor:	
	public RegisterPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil= new ElementUtil(this.driver);
	}
	
//	2.private By locators:
	By firstName = By.id("input-firstname");
	By lastName = By.id("input-lastname");
	By eMail = By.id("input-email");
	By telephone = By.id("input-telephone");
	
	
	
	
	
	
	By password = By.id("input-password");
	By confirmPassword = By.id("input-confirm");
	
	//Newsletter Radio button
	By subscribeYes= By.xpath("//label[@class='radio-inline'][1]/input");
	By subscribeNo= By.xpath("//label[@class='radio-inline'][2]/input");
	

	By privacyCheckBox = By.xpath("//*[@id=\"content\"]/form/div/div/input[1]");
	By continueButton = By.xpath("//*[@id=\"content\"]/form/div/div/input[2]");

	//Message tag after successful Registration
	By userRegSuccessMessage = By.tagName("h1");
	
	private By logoutLink = By.linkText("Logout");
	//Page Actions:
	
	private By registerLink = By.linkText("Register");
	
	public String registerUser(String firstName,String lastName,String eMail,
			String telephone,String password,String subscribe)
	{
		eleUtil.waitForElementVisible(this.firstName,AppConstants.MEDIUM_DEFAULT_WAIT);
		eleUtil.doSendKeys(this.firstName,firstName);
		eleUtil.doSendKeys(this.lastName,lastName);
		eleUtil.doSendKeys(this.eMail,eMail);
		eleUtil.doSendKeys(this.telephone,telephone);
		eleUtil.doSendKeys(this.password,password);
		eleUtil.doSendKeys(this.confirmPassword,password);
		
		doSubscribe(subscribe);
		eleUtil.doClick(privacyCheckBox);
		eleUtil.doClick(continueButton);
		
		String userRegistrationMessage=
				eleUtil.waitForElementVisible(userRegSuccessMessage, AppConstants.MEDIUM_DEFAULT_WAIT).getText();
		System.out.println("MESSAGE IS:"+userRegistrationMessage);
		
		eleUtil.doClick(logoutLink);
		eleUtil.doClick(registerLink);
		return userRegistrationMessage;
		
		
		
	}
	
	private void doSubscribe(String subscribe)
	{
		if(subscribe.equalsIgnoreCase("yes"))
		{
			eleUtil.doClick(subscribeYes);
		}
		else
		{
			eleUtil.doClick(subscribeNo);
		}
		
	}


}
