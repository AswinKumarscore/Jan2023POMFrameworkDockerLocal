package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.RegisterPageAssignment;

public class RegisterPageAssignmentTest extends BaseTest{
	
	
	@Test
	public void RegistrationPageTitleTest()
	{
		String actTitle = registerPageAssignment.getRegistrationPageTitle();
		Assert.assertEquals(actTitle,"Register Account");
	}
	
	@Test
	public void RegistrationPageUrlTest()
	{
		String actURL = registerPageAssignment.getRegistrationPageURL();
		Assert.assertTrue(actURL.contains("route=account/register"));
		
	}
	
	@Test
	public void isLoginRegistrationSuccessfulTest()
	{
		registerPageAssignment.doFillTheRegistrationForm();
	}

}
