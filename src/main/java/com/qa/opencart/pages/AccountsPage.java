package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	WebDriver driver;
	private ElementUtil eleUtil;

	//1.constructor of the Page class
	public AccountsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
//Questions:How do you utilise OR Use all the methods of Element Util?
	//Answer:You create Object of the Element Util class and use the method
	//Account credentials:
//	virat.india@gmail.com
//1Virat

	//2. by locators
	private By logout = By.linkText("Logout");
	private By myAccount = By.linkText("My Account");
	private By accHeaders = By.cssSelector("div#content h2");//Account Page headers
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	
	//3. page actions:
	public String getAccPageTitle()
	{
		return eleUtil.waitForTitleIsAndCapture(AppConstants.ACCOUNTS_PAGE_TITLE_VALUE,AppConstants.SHORT_DEFAULT_WAIT);
	}
	
	public boolean isLogoutLinkExist()
	{
		return eleUtil.checkElementIsDisplayed(logout);
	}
	
	public boolean isMyAccountLinkExist()
	{
		return eleUtil.checkElementIsDisplayed(myAccount);
		
	}
	
	public List<String>  getAccountPageHeadersList()
	{
		List<WebElement> headersList = eleUtil.waitForElementsVisible(accHeaders,AppConstants.MEDIUM_DEFAULT_WAIT);
		List<String> headersValList = new ArrayList<String>();
		for(WebElement e:headersList)
		{
			String text = e.getText();
			headersValList.add(text);
		}
		return headersValList;
	}
	
	public ResultsPage doSearch(String searchTerm)
	{
		eleUtil.waitForElementVisible(search, AppConstants.MEDIUM_DEFAULT_WAIT);
		eleUtil.doSendKeys(search, searchTerm);
		eleUtil.doClick(searchIcon);
		return new ResultsPage(driver);
	} 


}
