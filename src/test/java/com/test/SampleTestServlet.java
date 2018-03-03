package com.test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SampleTestServlet 
{

	WebDriver browser = null;
	Actions action = null;
	
	@BeforeMethod
	public void setUp()
	{
		Path pathOfWebDriver = Paths.get("resources/thirdparty/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", pathOfWebDriver.toAbsolutePath().toString()); /*setting chrome driver location*/
		
		browser =new ChromeDriver(); /*intializing the chrome driver object, Web driver is not a class , it is ainterface of selenium*/
		action=new Actions(browser);
		browser.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        browser.get("http://localhost:8080/sample/");	
	}

	@AfterMethod
	public void tearDown()
	{
		browser.quit();
	}
	
	@Test
	public void testSampleWarServlet() throws InterruptedException
	{
		WebElement jspLink = browser.findElement(By.xpath("//a[@href='hello']")); /*creating instance of webelement which represent id of searchbox*/
		
		action.moveToElement(jspLink).click().perform();
		Thread.sleep(5*1000); 
		
		WebElement servletPageHeaderActual = browser.findElement(By.xpath("//td//h1"));
		
		String expectedServletPageHeader = "Sample Application Servlet";
		
		Assert.assertEquals(expectedServletPageHeader, servletPageHeaderActual.getText(), "actual and expected text's doesn't match.");

	}
}
