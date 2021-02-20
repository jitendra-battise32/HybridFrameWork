package com.HMS.qa.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.HMS.qa.base.TestBase;

import io.qameta.allure.Step;

public class LoginPage extends TestBase{

	public LoginPage() throws IOException {
		//super();
		 PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	@FindBy (xpath="//input[@id='user_name']")
	WebElement userName;
	
	@FindBy (xpath="//input[@id='password']")
	WebElement password;
	
	@FindBy (xpath ="//input[@id='login_submit']")
	WebElement loginButton;
	
	@FindBy (xpath ="//*[@class='title-admin']")
	WebElement title;

	@FindBy (xpath ="//*[@class='box-body']")
	WebElement dashBoardReports;

	@Step("Getting Username")
	public WebElement usernameMethod() {
		return userName;	
	}
		
	@Step("Getting Password")
	public WebElement passwordMethod() {
		return password;
	}
		
	@Step("Clicking on Login Button")
	public WebElement loginButtonMethod() {
		return loginButton;
	}
		
	@Step("Getting Title")
	public WebElement getTitleMethod() {
		return title;
	}
		
	@Step("Verifying Dashboard Title")
	public WebElement dashBoardReportsMethod() {
		return dashBoardReports;
	}

}
