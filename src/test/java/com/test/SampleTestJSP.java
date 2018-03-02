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

public class SampleTestJSP 
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
        browser.get("http://10.11.17.3:8080/sample/");	
	}

	@AfterMethod
	public void tearDown()
	{
		browser.quit();
	}
	
	@Test
	public void testSampleWarJSP() throws InterruptedException
	{
		WebElement jspLink = browser.findElement(By.xpath("//a[@href='hello.jsp']")); /*creating instance of webelement which represent id of searchbox*/
		
		action.moveToElement(jspLink).click().perform();
		Thread.sleep(5*1000); 
		
		WebElement jspPageHeaderActual = browser.findElement(By.xpath("//td//h1"));
		
		String expectedJspPageHeader = "Sample Application JSP Page";
		
		Assert.assertEquals(expectedJspPageHeader, jspPageHeaderActual.getText(), "actual and expected text's doesn't match.");

	}
	
}