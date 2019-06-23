package com.w2a.pages;

//import org.openqa.selenium.By;


import com.w2a.base.Page;
import com.w2a.pages.crm.CRMHomePage;

public class ZohoHomePage extends Page{
	
	/*
	 * WebDriver driver;
	 * 
	 * public ZohoHomePage(WebDriver driver) { this.driver = driver; }
	 */
	
	
	public void goToCliq() 
	{
		
	}
	
	public CRMHomePage goToCRM() 
	{
		//driver.findElement(By.xpath("//*[@id=\"zl-myapps\"]/div[1]/div[2]/div/a/div")).click();
		////*[@id="zl-myapps"]/div[1]/div[2]/div/a/div
		click("crm_xpath");
		return new CRMHomePage();
	}
	
	public void goToMeeting() 
	{
		
	}
	
	public void goToALLAPPS() 
	{
		
	}

}
