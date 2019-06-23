package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.w2a.utilities.*;


import io.github.bonigarcia.wdm.WebDriverManager;

public class Page {
	
	
	public static WebDriver driver;
	public static String username = "brighthamza9@yahoo.com";
	public static TopMenu menu;
	
	public static Properties locator = new Properties();
	public static Properties config = new Properties();
	public static FileInputStream fileInput;
	public static String rootPath = System.getProperty("user.dir");  //-------- C:\Users\Bright\eclipse-workspace\PageObjectModelBasics
	private static String locatorPath = rootPath + "\\src\\test\\resources\\com\\w2a\\properties\\LOCATOR.properties";
	private static String configPath = rootPath + "\\src\\test\\resources\\com\\w2a\\properties\\CONFIG.properties";
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static XMLReader xml = new XMLReader(rootPath + "\\src\\test\\resources\\com\\w2a\\xml\\testdata.xml");
	public WebDriverWait wait;
	
	public static String xmlFilePath = rootPath + "\\src\\test\\resources\\com\\w2a\\xml\\testData.xml";
	public ExtentReports report = ExtentManager.getInstance();
	
	/*Used to define all the logs inside the testcases */
	public static ExtentTest test;
	public static WebElement dropdown;
	
	//for jenkins
	public static String browser; 
	
	/*
	 * All the Initializations would be done here.
	 * 
	 * WebDriver
	 * Properties
	 * Logs
	 * ExtentReports
	 * Excel
	 * Mail
	 * 
	 * 
	 * */
	
	public Page() 
	{
		if (driver == null) 
		{
			try 
			{
				fileInput = new FileInputStream(locatorPath);
				locator.load(fileInput);
				log.debug("LOCATOR.properties file successfully loaded");
			} 
			catch (FileNotFoundException e1) 
			{
				log.debug("LOCATOR.properties file not found");
			}
			catch (IOException e) 
			{
				log.debug("LOCATOR.properties file cannot be read");
			}
			
			try 
			{
				fileInput = new FileInputStream(configPath);
				config.load(fileInput);
				log.debug("CONFIG.properties file successfully loaded");
			} 
			catch (FileNotFoundException e1) 
			{
				log.debug("CONFIG.properties file not found");
			}
			catch (IOException e) 
			{
				log.debug("CONFIG.properties file cannot be read");
			}
			
			//Jenkins Browser Filter Configuration
			if(System.getenv("browser") != null && !System.getenv("browser").isEmpty()) 
			{
				browser = System.getenv("browser"); 
			}
			else 
			{
				browser = config.getProperty("browser"); 
			}
			
			config.setProperty("browser", browser);
			
			if(config.getProperty("browser").equals("firefox")) 
			{
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.debug("firefox driver successfully launched");
			}
			else if(config.getProperty("browser").equals("chrome")) 
			{
				WebDriverManager.chromedriver().setup();
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				//options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");
				driver = new ChromeDriver(options);
				log.debug("chrome driver successfully launched");
			}
			else if(config.getProperty("browser").equals("edge")) 
			{
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				log.debug("edge driver successfully launched");
			}
			else if(config.getProperty("browser").equals("opera")) 
			{
				WebDriverManager.operadriver().setup();
				driver = new OperaDriver();
				log.debug("opera driver successfully launched");
			}
			else if(config.getProperty("browser").equals("ie")) 
			{
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				log.debug("internetexplorer driver successfully launched");
			}
			else 
			{
				
			}
			
			driver.get(config.getProperty("testsiteurl"));
			log.debug("Successfully navigated to "+config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);
			menu = new TopMenu();
			
			/*
			 * driver.get("http://zoho.com"); driver.manage().window().maximize();
			 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); menu = new
			 * TopMenu();
			 */
		}
		
	}
	
	public static void click(String locatorValue) 
	{
		boolean logInfo = true;
		if(locatorValue.endsWith("_xpath")) 
		{
			driver.findElement(By.xpath(locator.getProperty(locatorValue))).click();
		}
		else if(locatorValue.endsWith("_css")) 
		{
			driver.findElement(By.cssSelector(locator.getProperty(locatorValue))).click();
		}
		else if(locatorValue.endsWith("_id")) 
		{
			driver.findElement(By.id(locator.getProperty(locatorValue))).click();
		}
		else if(locatorValue.endsWith("_tag")) 
		{
			driver.findElement(By.tagName(locator.getProperty(locatorValue))).click();
		}
		else if(locatorValue.endsWith("_name")) 
		{
			driver.findElement(By.name(locator.getProperty(locatorValue))).click();
		}
		else if(locatorValue.endsWith("_linktext")) 
		{
			driver.findElement(By.linkText(locator.getProperty(locatorValue))).click();
		}
		else if(locatorValue.endsWith("_className")) 
		{
			driver.findElement(By.className(locator.getProperty(locatorValue))).click();
		}
		else 
		{
			test.log(LogStatus.ERROR, "Invalid Locator: "+locatorValue);
			logInfo = false;
		}
		if(logInfo) 
		{
			test.log(LogStatus.INFO, "Clicking on: "+locatorValue);
			log.debug("Clicking on: "+locatorValue);
		}
	}

	public static void type(String locatorValue, String value) 
	{
		boolean logInfo = true;
		if(locatorValue.endsWith("_xpath")) 
		{
			driver.findElement(By.xpath(locator.getProperty(locatorValue))).sendKeys(value);
		}
		else if(locatorValue.endsWith("_css")) 
		{
			driver.findElement(By.cssSelector(locator.getProperty(locatorValue))).sendKeys(value);
		}
		else if(locatorValue.endsWith("_id")) 
		{
			driver.findElement(By.id(locator.getProperty(locatorValue))).sendKeys(value);
		}
		else if(locatorValue.endsWith("_tag")) 
		{
			driver.findElement(By.tagName(locator.getProperty(locatorValue))).sendKeys(value);
		}
		else if(locatorValue.endsWith("_name")) 
		{
			driver.findElement(By.name(locator.getProperty(locatorValue))).sendKeys(value);
		}
		else if(locatorValue.endsWith("_linktext")) 
		{
			driver.findElement(By.linkText(locator.getProperty(locatorValue))).sendKeys(value);
		}
		else if(locatorValue.endsWith("_className")) 
		{
			driver.findElement(By.className(locator.getProperty(locatorValue))).sendKeys(value);
		}
		else 
		{
			test.log(LogStatus.ERROR, "Invalid Locator: "+locatorValue);
			logInfo = false;
		}
		//driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		if(logInfo) 
		{
			test.log(LogStatus.INFO, "Typing in: " + locatorValue + ".Data entered as " + value);
			log.debug("Typing in: " + locatorValue + ".Data entered as " + value);
		}
	}

	public boolean isElementPresent(By by) 
	{
		try 
		{
			driver.findElement(by);
			return true;
		}
		catch(NoSuchElementException exception)
		{
			return false;
		}
		
	}

	public void select(String locatorValue, String value) 
	{
		boolean logInfoSelect = true;
		if(locatorValue.endsWith("_xpath")) 
		{
			dropdown = driver.findElement(By.xpath(locator.getProperty(locatorValue)));
		}
		else if(locatorValue.endsWith("_css")) 
		{
			dropdown = driver.findElement(By.cssSelector(locator.getProperty(locatorValue)));
		}
		else if(locatorValue.endsWith("_id")) 
		{
			dropdown = driver.findElement(By.id(locator.getProperty(locatorValue)));
		}
		else if(locatorValue.endsWith("_tag")) 
		{
			dropdown = driver.findElement(By.tagName(locator.getProperty(locatorValue)));
		}
		else if(locatorValue.endsWith("_name")) 
		{
			dropdown = driver.findElement(By.name(locator.getProperty(locatorValue)));
		}
		else if(locatorValue.endsWith("_linktext")) 
		{
			dropdown = driver.findElement(By.linkText(locator.getProperty(locatorValue)));
		}
		else if(locatorValue.endsWith("_className")) 
		{
			dropdown = driver.findElement(By.className(locator.getProperty(locatorValue)));
		}
		else 
		{
			test.log(LogStatus.ERROR, "Invalid Locator: "+locatorValue);
			logInfoSelect = false;
		}
		//driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		if(logInfoSelect) 
		{
			Select selectItems = new Select(dropdown);
			selectItems.selectByVisibleText(value);
			test.log(LogStatus.INFO, "Selecting from dropdown: " + locatorValue + ".Data selected: " + value);
			log.debug("Selecting from dropdown: " + locatorValue + ".Data selected: " + value);
		}
	}

	public static void verifyEquals(String expected, String actual) throws IOException 
	{
		try 
		{
			Assert.assertEquals(actual, expected);
		}
		catch(Throwable t) 
		{
			Utilities.captureScreenshot();
		
		
			//For ReportNG
			Reporter.log("<br>");
			Reporter.log("Verifaction failure "+t.getMessage());
			Reporter.log("<br>");
			Reporter.log("<a target=\"_blank\" href="+Utilities.screenshotName+"><img height=200 width=200 src="+Utilities.screenshotName+"></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			
			
			//For ExtentNG
			test.log(LogStatus.FAIL, "Verifaction failure with exception: " + t.getMessage());
			log.debug("Verifaction failure with exception: " + t.getMessage());
			//used to add screenshot
			test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenshotName));
		}
	}

	@AfterSuite
	public static void quitSession() throws InterruptedException 
	{
		if (driver != null) 
		{
			Thread.sleep(2000);
			driver.quit();
			log.debug("Execution Completed");
		}
	}


}
