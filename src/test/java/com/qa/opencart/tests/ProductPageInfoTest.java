package com.qa.opencart.tests;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductPageInfoTest extends BaseTest{
	@BeforeClass
	public void accPageSetup()
	{
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
//{Availability=In Stock, Brand=Apple, Product Code=Product 18, Reward Points=800,
//	extaxprice=$2,000.00, productname=MacBook Pro, productprice=$2,000.00}
	@Test
	public void productInfoTest()
	{
		resultsPage =accPage.doSearch("Macbook");
		productInfoPage=resultsPage.selectProduct("MacBook Pro");
		Map<String,String> productInfoMap=productInfoPage.getProductInfo();
		System.out.println(productInfoMap);
		
		softAssert.assertEquals(productInfoMap.get("Brand"),"Apple");
		softAssert.assertEquals(productInfoMap.get("Availability"),"In Stock");
		softAssert.assertEquals(productInfoMap.get("productname"),"MacBook Pro");
		softAssert.assertEquals(productInfoMap.get("productprice"),"$2,000.00");
		softAssert.assertAll();
	
//{Brand=Apple, Product Code=Product 18, Reward Points=800, Availability=In Stock,
		//productprice=$2,000.00, extaxprice=$2,000.00, productname=MacBook Pro}
	}

}
