package com.qa.login;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.util.TestUtil;

public class Login {
   WebDriver driver;
   
   @BeforeMethod
   public void setUp() {
	   System.setProperty("webdriver.chrome.driver","D://selenium java//chromedriver.exe");//set property for google chrome
		  driver = new ChromeDriver();
     // TODO Auto-generated method stub
		  driver.manage().deleteAllCookies();
		  driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		  driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		  
		  driver.get("https://ui.cogmento.com/");
   }
   
   @DataProvider
   public Object[][] getLoginData() throws IOException {
     Object data[][]=TestUtil.getTestData("testNG");
     return data;
   }
	
   @Test(dataProvider = "getLoginData",priority=1)
   public void loginTest(String username, String password ) 
   {
	   driver.findElement(By.name("email")).sendKeys(username);
	   driver.findElement(By.name("password")).sendKeys(password);
	   driver.findElement(By.xpath("//*[@id=\"ui\"]/div/div/form/div/div[3]")).click();
   }
   @Test(priority=2)
   public void linkTest() {
	   List<WebElement> links=driver.findElements(By.tagName("a"));
	   Iterator<WebElement> itr=links.iterator();
	   while(itr.hasNext()) {
		   String url=itr.next().getAttribute("href");
		   System.out.println(url);
	   }
	   
   }
	
   //quit method
   @AfterMethod
   public void tearDown() {
	   driver.quit();
   }
	
}
