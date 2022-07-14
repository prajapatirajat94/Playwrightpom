package com.qa.opencart.homepage;

import com.microsoft.playwright.Page;

public class HomePage {
	private Page page;
//1.String Locators	
	private String search = "input[name='search']";
	private String searchicon="div#search button";
	private String searchpageheader="div#content h1";
	private String Loginlink = "a:text('Login')";
	private String MyAccount = "//span[normalize-space()='My Account']";
	
//2.Page Constructor:
	public HomePage(Page page) {
		this.page=page;	
	}
//3.Page actions and methods 
	public String getHomePageTitle() {
		String title =page.title();
		System.out.println("Page title is: "+title);
		return title;
	}
	
    public String HomePageUrl() {
    	String url = page.url();
    	System.out.println("Page url is: "+url);
    	return url ;
    }

    public String DoSearch(String productname) {
    	page.fill(search, productname);
    	page.click(searchicon);
    	return page.textContent(searchpageheader);
    }
    
    public LoginPage navigatetologinpage() {
    	page.click(MyAccount);
    	page.click(Loginlink);
    	return new LoginPage(page);
    }
}
