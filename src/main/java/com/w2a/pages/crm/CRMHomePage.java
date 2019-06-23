package com.w2a.pages.crm;



import org.openqa.selenium.By;

import com.w2a.base.Page;

public class CRMHomePage extends Page{
	
	int atIndex;
	String welcomeMessage;
	
	public void verifyWelcomeMessage() 
	{
		welcomeMessage = driver.findElement(By.xpath("//*[@id=\"show-uName\"]")).getText();
		atIndex = username.indexOf("@");
		if (welcomeMessage.equals("Welcome " + username.substring(0, atIndex))) 
		{
			System.out.println("Passed");
		}
		else 
		{
			System.out.println("Failed");
		}
	}
	
	
	
	public void verifyCRMHomePageTitle() 
	{
		
	}

}
