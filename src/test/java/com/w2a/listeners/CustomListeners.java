package com.w2a.listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;
import com.w2a.base.Page;
import com.w2a.utilities.MonitoringMail;
import com.w2a.utilities.TestConfig;
import com.w2a.utilities.Utilities;




public class CustomListeners extends Page implements ITestListener, ISuiteListener{
	
	public 	String messageBody;

	public void onTestStart(ITestResult result) {
		test = report.startTest(result.getName().toUpperCase());
		
		//runmodes = Y
		if(!Utilities.isTestRunnable(result.getName(), xml)) 
		{
			throw new SkipException("Skipping the test" + result.getName().toUpperCase() +  " as the Run mode is NO");
		}
		report.endTest(test);
		report.flush();
		
	}

	public void onTestSuccess(ITestResult result) {
		test.log(LogStatus.PASS, result.getName().toUpperCase() + " SUCCESSFULLY EXECUTED/PASSED");
		
		
		/*This is used to end reporting for the testcase*/
		report.endTest(test);
		report.flush();
		
	}

	public void onTestFailure(ITestResult result) {
		
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			Utilities.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test.log(LogStatus.FAIL, result.getName().toUpperCase() + " NOT SUCCESSFULLY EXECUTED/PASSED " + result.getThrowable());
		
		
		
		//used to add screenshot
		test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenshotName));
		/*This is used to end reporting for the testcase*/
		
		
		
		
		Reporter.log("Click to Screenshot");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+Utilities.screenshotName+">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+Utilities.screenshotName+"><img height=200 width=200 src="+Utilities.screenshotName+"></a>");
		report.endTest(test);
		report.flush();
		
	}

	public void onTestSkipped(ITestResult result) {
		test.log(LogStatus.SKIP, result.getName().toUpperCase() +  " as the Run mode is NO");
		log.debug(result.getName().toUpperCase() +  " as the Run mode is NO");
		report.endTest(test);
		report.flush();
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		
		
		
	}

	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ISuite suite) 
	{
		MonitoringMail mail = new MonitoringMail();
		 
		try {
			messageBody = "http://" + InetAddress.getLocalHost().getHostAddress()
					+ ":8080/job/LiveProject%20-%20PageObjectModel/Page_20Object_20Model_20Report/";
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
		
	}


