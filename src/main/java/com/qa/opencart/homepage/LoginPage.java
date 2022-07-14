package com.qa.opencart.homepage;

import com.microsoft.playwright.Page;

public class LoginPage {

private Page page;
//1.String Locators	
private String emailid= "input#input-email";
private String Password ="//input[@id='input-password']";
private String Loginbtn="//input[@value='Login']";
private String ForgotPassword = "//div[@class='form-group']//a[normalize-space()='Forgotten Password']";
private String LogOutLink ="//a[@class='list-group-item'][normalize-space()='Logout']";

//2.Page constructor
public LoginPage(Page page) {
		this.page=page;
	}

//3.Page actions/Methods
public String LoginPageTitle() {
	return page.title();
}

public boolean ForgetPasswordLinkExist() {
	System.out.println(page.textContent(ForgotPassword));
	return page.isVisible(ForgotPassword);
}

public boolean doLogin(String appusername,String appPassword)  {
	System.out.println("App credential is :"+appusername+"and Password is : "+appPassword);
	page.fill(emailid,appusername );
	page.fill(Password, appPassword);
	page.click(Loginbtn);
 page.textContent(LogOutLink);
	if(page.isVisible(LogOutLink)) {
		System.out.println("User logged in successfully ....");
		return true;
	}

	return false;
	
}
}
