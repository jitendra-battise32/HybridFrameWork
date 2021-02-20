package com.HMS.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.HMS.qa.util.TestUtil;
import com.HMS.qa.util.WebEventListener;

public class TestBase {

	public static WebDriver driver;
    public static Properties prop;
    public static EventFiringWebDriver e_driver;
    public static WebEventListener eventListener;
    public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
    
    public TestBase() throws IOException{
    	prop = new Properties();
        
    	FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\HMS\\qa\\config\\config.properties");
    	prop.load(fis);
    	
    	 // String browserName = System.getProperty("browser");


    	String browserName = prop.getProperty("browser");

    }
    
    public static WebDriver initialisationBrowser() throws IOException {
    	
    	String browserName = prop.getProperty("browser");

    	if (browserName.contains("chrome"))
    	{ 
    	     System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\lib\\chromedriver.exe");
    	     ChromeOptions options = new ChromeOptions();
    	     if(browserName.contains("headless"))
    	     {
    	     options.addArguments("headless");
    		 }
    	     driver = new ChromeDriver(options);
    	}


    	if (browserName.equals("firefox")) 
    	{
    		System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\lib\\geckodriver.exe");
    		 driver = new FirefoxDriver();
    	}

    	if (browserName.equals("IE")) 
    	{
    		System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\lib\\IEDriverServer.exe");  
    	     driver=new InternetExplorerDriver();  	
    	}
    	
    	EventFiringWebDriver e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
    	
    	driver.manage().window().maximize();
    	driver.manage().deleteAllCookies();
    	driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
    	driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
    	
    	driver.get(prop.getProperty("url"));
    	tdriver.set(driver);
    	return getDriver();
    }
    
    public static synchronized WebDriver getDriver() {
    	
    	return tdriver.get();
    }
			
		    
		
	
	
	
	

}
