package com.w2a.utilities;

import java.io.File;
import java.util.ArrayList;
//import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
//import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.w2a.base.Page;


public class XMLReader extends Page{
	
	public String path;
	public String tagName;
	public File fXmlFile;
	public DocumentBuilderFactory dbFactory;
	public static DocumentBuilder dBuilder ;
	public static Document doc; 
	private static NodeList nList;
	public ArrayList<ArrayList<String>> listOfLists; 
	public ArrayList<String> singleList;
	private static Element eElement;
	
	public XMLReader(String path) {
		
		this.path=path;
		try {
			fXmlFile = new File(path);
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
	}
	
	public XMLReader(String path, String tagName) {
		
		this.path=path;
		this.tagName = tagName;
		try {
			fXmlFile = new File(path);
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
	}
	
	public NodeList getTagElements(String tagName) 
	{	
		
		doc.getDocumentElement().normalize();
		nList = doc.getElementsByTagName(tagName);
		return nList;
		
	}
	
	
	public ArrayList<ArrayList<String>> readXMLData() 
	{
		doc.getDocumentElement().normalize();
		nList = doc.getElementsByTagName(tagName);
		int numberOfRows = nList.getLength();
		listOfLists = new ArrayList<ArrayList<String>>();
		
		if (tagName.equals("loginTest")) 
		{
			for (int temp = 0; temp < numberOfRows; temp++) 
			{

				Node nNode = nList.item(temp);
				singleList = new ArrayList<String>();
				// ELEMENT_NODE type refers to a non-text node which have sub-elements
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					eElement = (Element) nNode;
					singleList.add(eElement.getAttribute("username"));
					singleList.add(eElement.getAttribute("password"));
					listOfLists.add(singleList);
			
				}
			}
		}
		
		if (tagName.equals("createAccountTest")) 
		{
			for (int temp = 0; temp < numberOfRows; temp++) 
			{

				Node nNode = nList.item(temp);
				singleList = new ArrayList<String>();
				// ELEMENT_NODE type refers to a non-text node which have sub-elements
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					eElement = (Element) nNode;
					singleList.add(eElement.getAttribute("accountname"));
					listOfLists.add(singleList);
			
				}
			}
		}
		
		return listOfLists;
		
	}

}
