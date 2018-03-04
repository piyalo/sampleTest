package com.resource;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertyReader {
	static Path pathOfPropFile = Paths.get("resources/testprop/framework.properties");
	private static String propertyFile= pathOfPropFile.toAbsolutePath().toString(); 
	private static Properties prop;
	private static FileInputStream fis;
	
	public PropertyReader() throws IOException
	{
		prop=new Properties();
		fis=new FileInputStream(propertyFile);
		prop.load(fis);
	}

	public String getURL() throws IOException
	{

		return prop.getProperty("URL").trim();			
	}
	public String getBrowserName() throws IOException
	{

		return prop.getProperty("BrowserName").trim();			
	}
	
	public String getSeapMode() throws IOException
	{

		return prop.getProperty("SeapMode").trim();			
	}
	
	public int getDefaultBPTIntervalSec() throws IOException
	{

		return Integer.parseInt(prop.getProperty("BPTInterval").trim());			
	}

}
