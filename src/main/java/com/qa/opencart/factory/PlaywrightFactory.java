package com.qa.opencart.factory;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {
Playwright playwright;
Browser browser;
BrowserContext browsercontext;
Page page;
Properties prop;
public Page initbrowser(Properties prop) {
	String browsername =prop.getProperty("browser").trim();
	System.out.println("My browser name is : "+browsername);
	playwright= Playwright.create();
	
	switch (browsername.toLowerCase()) {
	case "chromium":
		browser =playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		break;
	case "firefox":
		browser =playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
		break;	
	case "safari":
		browser =playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
		break;	
	case "chrome":
		browser =playwright.chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false));
		break;	
	default:
		System.out.println("Please pass the correct browser name....");
		break;
	}	
	browsercontext=browser.newContext();
	page=browsercontext.newPage();
	page.navigate(prop.getProperty("url"));
	return page;
}

/**
 * this method will initialize properties and return properties 
 * @return
 */
public Properties init_properties() {
	FileInputStream ip;
	try {
		ip = new FileInputStream("C:\\Users\\praja\\eclipse-workspace\\PlaywrightPOM\\src\\test"
				                            + "\\resources\\config\\config.properties");
		prop = new Properties();
        prop.load(ip);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}catch (Exception e) {
		e.printStackTrace();
	}

return prop;
       

}
}


