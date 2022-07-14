package com.qa.opencart.basetest;

import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.microsoft.playwright.Page;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.opencart.homepage.HomePage;
import com.qa.opencart.homepage.LoginPage;

public class BaseTest {
	PlaywrightFactory pf;
	 Page page;
	protected HomePage homepage;
	protected Properties prop;
	protected LoginPage loginpage;

	@BeforeTest
	public void setup() {
		pf = new PlaywrightFactory();
		prop =pf.init_properties();
		page =pf.initbrowser(prop);
		homepage = new HomePage(page);
	}
	
	@AfterTest
	public void TearDown() {
		page.context().browser().close();
	}
}
