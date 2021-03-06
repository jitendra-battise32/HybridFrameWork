package com.HMS.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.HMS.qa.base.TestBase;
import com.HMS.qa.pages.LoginPage;

import com.HMS.qa.util.Xls_Reader;

import org.testng.annotations.BeforeMethod;

public class LoginPageTest extends TestBase {
	LoginPage loginPage;
	
	public LoginPageTest() throws IOException {
		super();   // will call the base class (parent class constructor)
	}
	
	@BeforeMethod
    public void setUp() throws IOException {
		 initialisationBrowser();   // will call the initialisationBrowser method in the TestBase Class
		 loginPage = new LoginPage();
	}

	@Test
        public void loginSuccessTest() throws IOException, InterruptedException {

		    Xls_Reader reader = new Xls_Reader(System.getProperty("user.dir")+"\\src\\main\\java\\com\\HMS\\qa\\testdata\\TestData.xlsx");
  
			String username = reader.getCellData("LoginCredentials","Username", 2);
            String password = reader.getCellData("LoginCredentials","Password", 2);
    	
            loginPage.usernameMethod().sendKeys(username);
            loginPage.passwordMethod().sendKeys(password);
	        
            loginPage.loginButtonMethod().click();
            String actualTitle = loginPage.getTitleMethod().getText();
     	    Assert.assertEquals(actualTitle,"DashBoard");
         	Assert.assertTrue(loginPage.getTitleMethod().isDisplayed());
	}
	
	
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
	

}
