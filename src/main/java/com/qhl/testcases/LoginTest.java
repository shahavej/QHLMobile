package com.qhl.testcases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.qhl.base.Apputil;
import com.qhl.base.Webutil;
import com.qhl.pages.LoginPage;

public class LoginTest extends Apputil {
	LoginPage obj;
	WebDriver driver;
	
	@BeforeMethod
	public void setup()
	{
		driver=Webutil.launch_browser("chrome");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://dev.quickhands.build-release.com/admin/login");
		
	}
	@AfterMethod
	public void killapk(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			String temp = Webutil.getScreenshot(driver);
			logger.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}

		extent.flush();
		driver.quit();
	}
	
	@Test(description="As a User I should be able to login",priority=1)
	public void login1() throws InterruptedException
	{	logger = extent.createTest("As a User I should be able to login");

		obj=new LoginPage(driver);
		obj.get_username_fld().sendKeys("admin@crownstack.com");
		obj.get_password_fld().sendKeys("password");
		obj.get_signin_btn().click();
		Thread.sleep(10000);
		assertEquals(driver.getCurrentUrl(),"https://dev.quickhands.build-release.com/admin/dashboard");
		
	}
	@Test(priority=2)
	public void login2()
	{
		logger = extent.createTest("At login screen each field should have proper valdation");
		
		obj.get_signin_btn().click();
		assertEquals(driver.findElement(By.xpath("//*[@id=\"m_login\"]/div[2]/div/div/form/small[1]")).getText()," Email is required and must be of valid format. ");
		assertEquals(driver.findElement(By.xpath("//*[@id=\"m_login\"]/div[2]/div/div/form/small[2]")).getText()," Password is required ");
		
		
	
	}
	
	
	
}
