package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.dataproviders.ProductDataProvider;

public class SearchTest extends BaseTest {
	// The precondition for search Test is Login
	
	//Hard codes values can be fixed with Data provider
	@BeforeClass
	public void searchSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	//DataProvider annotation supplies data to a test method

	//Data is provided inthe form of 2D array :3rows and 1 column
	//Data Driven Testing Approach
	
	
	@Test(dataProvider="productDataWithSearchKey",dataProviderClass=ProductDataProvider.class)
	public void searchProductResultsCountTest(String searchKey) {
		resultsPage = accPage.doSearch(searchKey );
		Assert.assertTrue(resultsPage.getProductResultsCount() > 0);
	}

	@Test(dataProvider="productDataWithSearchKey",dataProviderClass=ProductDataProvider.class)
	public void searchPageTitleTest(String searchKey) {
		resultsPage = accPage.doSearch(searchKey);
		String actSearchTitle = resultsPage.getResultsPageTitle(searchKey);
		System.out.println("Search Page Title is : " + actSearchTitle);
		Assert.assertEquals(actSearchTitle, "Search - "+searchKey);
	}
	


	@Test(dataProvider="productDataWithName",dataProviderClass=ProductDataProvider.class)
	public void selectProductTest(String searchKey,String productName) {
		resultsPage = accPage.doSearch(searchKey);
		productInfoPage = resultsPage.selectProduct(productName);
		String actProductHeaderName = productInfoPage.getProductHeaderName();
		System.out.println("Actual Product Header Name is :" + actProductHeaderName);
		Assert.assertEquals(actProductHeaderName,productName);
	}


	@Test(dataProvider="productDataWithImage",dataProviderClass=ProductDataProvider.class)
	public void productImagesTest(String searchKey,String productName,int expImagesCount)
	{
		resultsPage = accPage.doSearch(searchKey);
		productInfoPage = resultsPage.selectProduct(productName);
		int actProductImagesCount= productInfoPage.getProductImagesCount();
		Assert.assertEquals(actProductImagesCount,expImagesCount);
	}
	

}
