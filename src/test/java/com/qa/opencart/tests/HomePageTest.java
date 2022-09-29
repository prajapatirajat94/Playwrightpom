package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.basetest.BaseTest;

import ConstantUtilities.AppConstants;

public class HomePageTest extends BaseTest {

	
@Test(priority=1)
public void TitleTest(){
	String actualtitle =homepage.getHomePageTitle();
	
	Assert.assertEquals(actualtitle, AppConstants.HOME_PAGE_TITLE);
	
	}
@Test(priority=2)
public void UrlTest() {
	String actualurl=homepage.HomePageUrl();
	Assert.assertEquals(actualurl, prop.getProperty("url"));
}
@DataProvider()
public Object [][] getproductdata() {
	
	return new Object [][] {
		{"Samsung"},
		{"iMac"},
		{"Macbook"}
		  };
}
@Test(priority=3,dataProvider="getproductdata")
public void Search(String productname) {
	String actualheader=homepage.DoSearch(productname);
	Assert.assertEquals(actualheader, "Search - "+productname);
}




	
}
