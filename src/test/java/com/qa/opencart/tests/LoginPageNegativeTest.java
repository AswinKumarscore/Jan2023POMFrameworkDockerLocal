package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class LoginPageNegativeTest extends BaseTest
{
	@DataProvider
	public Object[][] incorrectLoginTestData()
	{
		return new Object[][]
				{
			{"auto123@gmail.com","123456"},
			{"auto123gmail.com","123456"},
//			{"virat.india@gmail.com","1Virat"},
			{"auto123","123456"},
			{"auto123auto123auto123auto123@gmail.com","123456"},
			{"",""}
			
				};
		
	}
	@Test(dataProvider="incorrectLoginTestData")
	public void loginWithWrongCredentialsTest(String userName,String password)
	{
		
		Assert.assertTrue(loginPage.doLoginWithWrongCredentials(userName,password));
	}

}
