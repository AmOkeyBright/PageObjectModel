package com.w2a.pages.crm.accounts;

//import org.openqa.selenium.By;

import com.w2a.base.Page;

public class CreateAccountPage extends Page{
	
	public void createAccount(String nameAccount) 
	{
		//driver.findElement(By.xpath("//*[@id=\"Crm_Accounts_ACCOUNTNAME\"]")).sendKeys(nameAccount);
		type("accountName_xpath", nameAccount);
	}

}
