package com.w2a.pages;

//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;

import com.w2a.base.Page;

public class HomePage extends Page{
	
	/*
	 * WebDriver driver;
	 * 
	 * public HomePage(WebDriver driver) { this.driver = driver; }
	 */
	
	public void goToCustomers() 
	{
		
	}
	
	public void goToSupport() 
	{
		
	}
	
	public void goToContactSales() 
	{
		
	}
	
	public LoginPage goToLogin() 
	{
		//driver.findElement(By.xpath("//a[@class='zh-login']")).click();
		click("login_xpath");
		return new LoginPage();
	}
	
	public void goToFreeSignUp() 
	{
		
	}
	
	
	public void ValidateFooterLinks() 
	{
		
	}

}
