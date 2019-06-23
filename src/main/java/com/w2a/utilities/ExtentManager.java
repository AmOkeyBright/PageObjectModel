package com.w2a.utilities;

import java.io.File;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;


public class ExtentManager {
	
	private static ExtentReports extent;
	public static String pathRoot = System.getProperty("user.dir");
	
	public static ExtentReports getInstance() 
	{
		if(extent==null) 
		{
			extent = new ExtentReports(pathRoot + "\\target\\surefire-reports\\html\\extentReport.html", true, DisplayOrder.OLDEST_FIRST);
			extent.loadConfig(new File(pathRoot+"\\src\\test\\resources\\com\\w2a\\extentconfig\\ReportsConfig.xml"));
		}
		return extent;
	}

}
