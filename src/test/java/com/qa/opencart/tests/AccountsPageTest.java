package com.qa.opencart.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.utils.AppConstants;

public class AccountsPageTest extends BaseTest {
	// For The AccountsPageTest
	// What is the Precondition?
	// The Browser should launch
	// Navigate to the URL
	// Login to Website

	@BeforeClass
	public void accPageSetup() {

		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		
	}

	@Test
	public void accPageTitleTest() {

		String actTitle = accPage.getAccPageTitle();
		Assert.assertEquals(actTitle,AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);

	}

	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());

	}

	@Test
	public void isMyAccLinkExistTest() {

		Assert.assertTrue(accPage.isMyAccountLinkExist());

	}

	@Test
	public void accPageHeadersCountTest() {
		List<String> accountHeadersList = accPage.getAccountPageHeadersList();
		Assert.assertEquals(accountHeadersList.size(), 4);

	}

	@Test
	public void accPageHeadersTest() {
		List<String> accountHeadersList = accPage.getAccountPageHeadersList();
		
		Collections.sort(accountHeadersList);
		Collections.sort(AppConstants.EXPECTED_ACCOUNTS_HEADERS_LIST);
		Assert.assertEquals(accountHeadersList,AppConstants.EXPECTED_ACCOUNTS_HEADERS_LIST);
	}
	@Test
	public void successfulSearchTest()
	{
		accPage.doSearch("iPhone");
		System.out.println("------Search for item is Successfull-------");
	}
}
