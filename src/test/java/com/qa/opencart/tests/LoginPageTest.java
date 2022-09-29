package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.basetest.BaseTest;

import ConstantUtilities.AppConstants;

public class LoginPageTest extends BaseTest {

@Test(priority = 1)
public void LoginPagenavigationTest() {
	loginpage =homepage.navigatetologinpage();
	String actloginpagetitle=loginpage.LoginPageTitle();
	Assert.assertEquals(actloginpagetitle, AppConstants.LOGIN_PAGE_TITLE);
}

@Test(priority = 2)
public void forgotpasswordlinkexist() {
	boolean fp=loginpage.ForgetPasswordLinkExist();
	Assert.assertEquals(fp, true);
}
@Test(priority = 3)
public void appLoginTest() {
	
Assert.assertTrue(loginpage.doLogin(prop.getProperty("username"), 
		prop.getProperty("password")));
	
}
}
