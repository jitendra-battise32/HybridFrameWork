package com.HMS.qa.AllureReportListener;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import io.qameta.allure.Attachment;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.HMS.qa.base.*;

public class AllureReporter implements ITestListener{

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}
	
	// Text attachments for Allure
		@Attachment(value = "Page screenshot", type = "image/png")
		public byte[] saveScreenshotPNG(WebDriver driver) {
			return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		}
		
		// Text attachments for Allure
		@Attachment(value = "{0}", type = "text/plain")
		public static String saveTextLog(String message) {
			return message;
		}
		
		// HTML attachments for Allure
		@Attachment(value = "{0}", type = "text/html")
		public static String attachHtml(String html) {
			return html;
		}
		
		
		
	@Override
	public void onTestStart(ITestResult iTestResult) {
		// TODO Auto-generated method stub
		System.out.println("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		// TODO Auto-generated method stub
		System.out.println("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		// TODO Auto-generated method stub
		System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
		Object testClass = iTestResult.getInstance();
		WebDriver driver = TestBase.getDriver();
		// Allure ScreenShotRobot and SaveTestLog
		if (driver instanceof WebDriver) {
			System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
			saveScreenshotPNG(driver);
		}
		// Save a log on allure.
		saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");		
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		// TODO Auto-generated method stub
		System.out.println("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		// TODO Auto-generated method stub
		System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}

	@Override
	public void onStart(ITestContext iTestContext) {
		// TODO Auto-generated method stub
		System.out.println("I am in onStart method " + iTestContext.getName());
		iTestContext.setAttribute("WebDriver", TestBase.getDriver());
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		// TODO Auto-generated method stub
		System.out.println("I am in onFinish method " + iTestContext.getName());
	}

	
	
	
	
}
