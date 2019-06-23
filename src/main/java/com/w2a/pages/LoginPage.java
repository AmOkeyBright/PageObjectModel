package com.w2a.pages;

//import org.openqa.selenium.By;


import com.w2a.base.Page;

public class LoginPage extends Page{
	
	/*
	 * WebDriver driver;
	 * 
	 * public LoginPage(WebDriver driver) { this.driver = driver; }
	 */
	
	
	public ZohoHomePage loginIntoZoho(String username, String password)
	{
		
		//driver.findElement(By.id("lid")).sendKeys(username);
		type("username_xpath", username);
		
		//driver.findElement(By.id("pwd")).sendKeys(password);
		type("password_xpath", password);
		
		//driver.findElement(By.id("signin_submit")).click();
		click("submit_xpath");
		return new ZohoHomePage();
	}

}
