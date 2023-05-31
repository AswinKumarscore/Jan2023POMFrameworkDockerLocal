package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.dataproviders.ProductDataProvider;
import com.qa.opencart.pojo.Product;

public class SearchDataTest extends BaseTest {
	// The precondition for search Test is Login
	
		//Hard codes values can be fixed with Data provider
		@BeforeClass
		public void searchSetup() {
			accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		}

		/*
		@DataProvider(name="productData")
		public Object[][] getProductTestData()
		{
			return new Object[][]
					{
				{new Product("Macbook","MacBook Pro",4)},
				{new Product("iMac","iMac",3)},
				{new Product("Samsung","Samsung SyncMaster 941BW",1)},
				{new Product("Samsung","Samsung Galaxy Tab 10.1",7)}
					};
			
		}
		*/		
		@Test(dataProvider="productData",dataProviderClass=ProductDataProvider.class)
		public void searchProductResultsCountTest(Product product) {
			resultsPage = accPage.doSearch(product.getSearchKey());
			Assert.assertTrue(resultsPage.getProductResultsCount() > 0);
		}

		@Test(dataProvider = "productData",dataProviderClass=ProductDataProvider.class)
		public void searchPageTitleTest(Product product) {
			resultsPage = accPage.doSearch(product.getSearchKey());
			String actSearchTitle = resultsPage.getResultsPageTitle(product.getSearchKey());
			System.out.println("Search Page Title is : " + actSearchTitle);
			Assert.assertEquals(actSearchTitle, "Search - "+product.getSearchKey());
		}
		
	

		@Test(dataProvider = "productData",dataProviderClass=ProductDataProvider.class)
		public void selectProductTest(Product product) {
			resultsPage = accPage.doSearch(product.getSearchKey());
			productInfoPage = resultsPage.selectProduct(product.getProductName());
			String actProductHeaderName = productInfoPage.getProductHeaderName();
			System.out.println("Actual Product Header Name is :" + actProductHeaderName);
			Assert.assertEquals(actProductHeaderName,product.getProductName());
		}
		

		@Test(dataProvider = "productData",dataProviderClass=ProductDataProvider.class)
		public void productImagesTest(Product product)
		{
			resultsPage = accPage.doSearch(product.getSearchKey());
			productInfoPage = resultsPage.selectProduct(product.getProductName());
			int actProductImagesCount= productInfoPage.getProductImagesCount();
			Assert.assertEquals(actProductImagesCount,product.getProductImages());
		}
		


}
