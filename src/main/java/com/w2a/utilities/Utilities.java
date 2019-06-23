package com.w2a.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.w2a.base.Page;


//import com.w2a.base.TestBase;

//Implements things that are common in all testcases
public class Utilities extends Page{
	 
	private static Element eElement;
	public static String screenshotPath;
	public static String screenshotName;
	public static void captureScreenshot() throws IOException 
	{
		//screenshotName="error.jpg";
		
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		Date d = new Date();
		screenshotName=d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		FileUtils.copyFile(screenshot, new File(rootPath + "\\target\\surefire-reports\\html\\"+screenshotName));
	}
	
	
	
	 public static boolean isTestRunnable(String testName, XMLReader xml) {
		  
	  NodeList elementList = xml.getTagElements("test_suite"); 
	  for (int temp = 0; temp < elementList.getLength(); temp++) 
		{

			Node nNode = elementList.item(temp);
			// ELEMENT_NODE type refers to a non-text node which have sub-elements
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				eElement = (Element) nNode;
				if(eElement.getAttribute("TCID").equalsIgnoreCase(testName)) 
				{
					String runmode = eElement.getAttribute("RunMode");
					if(runmode.equalsIgnoreCase("y")) 
					{
						return true;
					}
					else 
					{
						return false;
					}
				}
		
			}
		}
	  
	   return false;
	  }
	 
		

	
	
	@DataProvider(name="dp")
	public Object[][] getData(Method m)
	{
		//m.getName() returns the name of the method that calls it
		String tagName = m.getName();
		xml = new XMLReader(xmlFilePath, tagName);		
		
		NodeList elementList = xml.getTagElements(tagName); 
		int rowsLength = elementList.getLength();
		Object[][] data = new Object[rowsLength][1];
		Hashtable <String, String> table = null;
		
		for (int rowNum=0; rowNum<rowsLength; rowNum++) 
		{		
			table = new Hashtable<String, String>();
			Node nNode = elementList.item(rowNum); 
			NamedNodeMap attributes = nNode.getAttributes();
			
			for (int a = 0; a < attributes.getLength(); a++) 
			{
		        Node theAttribute = attributes.item(a);
		        table.put(theAttribute.getNodeName(), theAttribute.getNodeValue());
		        data[rowNum][0] = table;
		}
		
	}
		return data;
	}
}
