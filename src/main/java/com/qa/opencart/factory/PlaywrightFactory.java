package com.qa.opencart.factory;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
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
private static ThreadLocal<Browser>tlBrowser= new ThreadLocal<>();
private static ThreadLocal<BrowserContext>tlBrowserContext= new ThreadLocal<>();
private static ThreadLocal<Page>tlPage= new ThreadLocal<>();
private static ThreadLocal<Playwright>tlPlaywright= new ThreadLocal<>();


public static Playwright getPlaywright() {
	return tlPlaywright.get();
}

public static Browser getBrowser() {
	return tlBrowser.get();
}

public static BrowserContext getBrowserContext() {
	return tlBrowserContext.get();
}

public static Page getPage() {
	return tlPage.get();
}





public Page initbrowser(Properties prop) {
	String browsername =prop.getProperty("browser").trim();
	System.out.println("My browser name is : "+browsername);
	//playwright= Playwright.create();
	tlPlaywright.set(Playwright.create());
	
	
	switch (browsername.toLowerCase()) {
	case "chromium":
	// browser =playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
		break;
	case "firefox":
	//	browser =playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
		tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
		break;	
	case "safari":
	//	browser =playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
		tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
		break;	
	case "chrome":
	//	browser =playwright.chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false));
		tlBrowser.set(getPlaywright().chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false)));
		break;	
	default:
		System.out.println("Please pass the correct browser name....");
		break;
	}	
	//browsercontext=browser.newContext();
	tlBrowserContext.set(getBrowser().newContext());
	//page=browsercontext.newPage();
	tlPage.set(getBrowserContext().newPage());
	
	getPage().navigate(prop.getProperty("url"));
	return getPage();
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
/**
 * take screenshot
 * @return
 */
public static String takeScreenshot() {
	String path =System.getProperty("user.dir")+ "/screenshot/"+System.currentTimeMillis()+".png";
	getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
	return path;
}
}


