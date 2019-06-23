package com.w2a.rough;

import com.w2a.base.Page;

//import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;

//import com.w2a.base.Page;
import com.w2a.pages.HomePage;
import com.w2a.pages.LoginPage;
import com.w2a.pages.ZohoHomePage;
import com.w2a.pages.crm.CRMHomePage;
import com.w2a.pages.crm.accounts.AccountsPage;
import com.w2a.pages.crm.accounts.CreateAccountPage;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {

	public static void main(String[] args) throws InterruptedException {
		
		/*
		 * WebDriverManager.chromedriver().setup(); WebDriver driver = new
		 * ChromeDriver(); driver.get("http://zoho.com");
		 * driver.manage().window().maximize();
		 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 */
		
		HomePage home = new HomePage();
		LoginPage login = home.goToLogin();
		//Thread.sleep(3000);
		//LoginPage login = new LoginPage();
		//login.loginIntoZoho("brighthamza9@yahoo.com", "okey##0917");
		//ZohoHomePage zoho = new ZohoHomePage();
		//CRMHomePage crm = new CRMHomePage();
		//AccountsPage accounts = new AccountsPage();
		//CreateAccountPage createaccountpage = new CreateAccountPage();
		
		
		ZohoHomePage zoho = login.loginIntoZoho("brighthamza9@yahoo.com", "okey##0917");
		Thread.sleep(2000);
		CRMHomePage crm = zoho.goToCRM();
		crm.verifyWelcomeMessage();
		AccountsPage accounts = Page.menu.goToAccounts();
		CreateAccountPage createaccountpage = accounts.goToCreateAccounts();
		createaccountpage.createAccount("Tochukwu Orisakwe");
		
		
		//driver.quit();

	}

}
