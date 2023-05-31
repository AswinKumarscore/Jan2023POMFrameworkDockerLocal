package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.internal.BaseTestMethod;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;



public class RegisterPageAssignment {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	// 1.Constructor of the Page Class
	public RegisterPageAssignment(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
		// TODO Auto-generated constructor stub
	}
	
	//	2.private By locators:
	By firstName = By.id("input-firstname");
	By lastName = By.id("input-lastname");
	By eMail = By.id("input-email");
	By telephone = By.id("input-telephone");
	By password = By.id("input-password");
	By confirmPassword = By.id("input-confirm");
	//label[@class='radio-inline'][2]
	
	By subscribeYes= By.xpath("//label[@class='radio-inline'][1]/input");
	By subscribeNo= By.xpath("//label[@class='radio-inline'][2]/input");
	//By newsLetterRadio = By.xpath("//*[@id=\"content\"]/form/fieldset[3]/div/div/label[1]/input");//newsletterRadioButton
	By privacyCheckBox = By.xpath("//*[@id=\"content\"]/form/div/div/input[1]");
	By continueButton = By.xpath("//*[@id=\"content\"]/form/div/div/input[2]");

	By msgTag = By.tagName("h1");
	// 3.public Page actions.methods
	
	public String getRegistrationPageTitle() {
		return eleUtil.waitForTitleIsAndCapture("Register Account",10);
	}

	public String getRegistrationPageURL() {
		return eleUtil.waitForURLContainsAndCapture("route=account/register",10);
		
		
	}
	public void doFillTheRegistrationForm()
	{
		
		eleUtil.doSendKeys(firstName,"firstname");
		eleUtil.doSendKeys(lastName,"lastName");
		eleUtil.doSendKeys(eMail,"email@ggmail.com");
		eleUtil.doSendKeys(telephone, "telephone");
		eleUtil.doSendKeys(password, "lastName");
		eleUtil.doSendKeys(confirmPassword, "lastName");
		//eleUtil.doClick(newsLetterRadio);
		eleUtil.doClick(privacyCheckBox);
		eleUtil.doClick(continueButton);
		
	}

	

}
