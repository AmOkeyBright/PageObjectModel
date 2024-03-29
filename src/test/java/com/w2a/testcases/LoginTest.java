package com.w2a.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.w2a.pages.HomePage;
import com.w2a.pages.LoginPage;
//import com.w2a.pages.ZohoHomePage;
import com.w2a.utilities.Utilities;

public class LoginTest extends BaseTest{
	
	
	@Test(dataProviderClass=Utilities.class, dataProvider="dp")
	public void loginTest(Hashtable<String, String> data) 
	{
		//Testing if changes are captured in git
		//Worked perfectly fine.....Using EGIT
		HomePage home = new HomePage();
		LoginPage login = home.goToLogin();
		login.loginIntoZoho(data.get("username"), data.get("password"));
	}

}
