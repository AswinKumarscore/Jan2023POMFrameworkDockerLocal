package com.qa.opencart.tests;

import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.ExcelUtil1;
import com.qa.opencart.utils.ExcelUtil2;

public class RegisterPageTest extends BaseTest{
	//annotation hierarchy
	//STCM (schedule tribe CM)
	//@BeforeSuite,@BeforeTest,@BeforeClass,@BeforeMethod
	@BeforeClass
	public void regSetUp()
	{
		
		registerPage=loginPage.navigateToRegisterPage();
	}
	
	//below method is used to return email id
	public String getRandomEmailId()
	{
		//return "testautomation"+System.currentTimeMillis() + "@gmail.com";
		return "testautomation"+UUID.randomUUID()+"@gmail.com";
	}
	
//	@DataProvider(name="registrationData")
//	public Object[][] getUserRegTestData()
//	{
//		return new Object[][]
//				{
//			{"abhii","anandd","9999923456","PWD@321@123","yes"},
//			{"abhii","automation","9999988456","PWD@321@123","yes"},
//			{"abhii","seleniumautomation","94499923456","PWD@321@123","yes"},
//			{"abhii","javaautomation","9999900456","PWD@321@123","yes"}
//				};
//	}
//	
	
	@DataProvider(name="regExcelData")
	public Object[][] getRegExcelTestData()
	{
		Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
		
	}

	
	@Test(dataProvider="regExcelData")
	public void userRegisterTest(String firstName,String lastName,String telephone,String password,String subscribe)
	{
		String actRegistrationSuccMessg=registerPage.registerUser(firstName,lastName,getRandomEmailId(),telephone,password,subscribe);
		System.out.println("THE RANDOM EMAIL ID:"+getRandomEmailId());
		Assert.assertEquals(actRegistrationSuccMessg,AppConstants.USER_REG_SUCCESS_MESSG);
	}

}
