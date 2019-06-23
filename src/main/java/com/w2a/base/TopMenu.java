package com.w2a.base;

//import org.openqa.selenium.By;

import com.w2a.pages.crm.accounts.AccountsPage;

public class TopMenu {
	
	
	public void goToHome() 
	{
		
	}
	
	public void goToLeads() 
	{
		
	}
	
	public void goToContacts() 
	{
		
	}
	
	public AccountsPage goToAccounts() 
	{
		//Page.driver.findElement(By.xpath("//*[@id=\"mainMenuTabDiv\"]/crm-menu/div[1]/crm-tab/div[2]/div[4]/a")).click();
		Page.click("accountsTab_xpath");
		return new AccountsPage();
	}
	
	public void goToDeals() 
	{
		
	}
	
	public void goToActivities() 
	{
		
	}
	
	public void goToReports() 
	{
		
	}
	
	public void goToAnalytics() 
	{
		
	}
	
	public void goToProjects() 
	{
		
	}
	
	public void signOut() 
	{
		
	}


}
