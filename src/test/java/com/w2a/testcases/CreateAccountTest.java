package com.w2a.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.w2a.base.Page;
import com.w2a.pages.ZohoHomePage;
import com.w2a.pages.crm.accounts.AccountsPage;
import com.w2a.pages.crm.accounts.CreateAccountPage;
import com.w2a.utilities.Utilities;

public class CreateAccountTest {
	
	@Test(dataProviderClass=Utilities.class, dataProvider="dp")
	public void createAccountTest(Hashtable<String, String> data) 
	{
		ZohoHomePage zoho = new ZohoHomePage();
		zoho.goToCRM();
		AccountsPage accounts = Page.menu.goToAccounts();
		CreateAccountPage createaccountpage = accounts.goToCreateAccounts();
		createaccountpage.createAccount(data.get("accountname"));
	}

}
