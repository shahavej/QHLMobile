package com.qhl.testcases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.qhl.base.Apputil;
import com.qhl.base.Test_Data;
import com.qhl.base.Webutil;
import com.qhl.pages.LoginScreen;

public class AppLoginTest extends Apputil{
	
	LoginScreen obj;
	
	@BeforeMethod
	public void setup() throws MalformedURLException, ParseException, InterruptedException
	{
		JSONObject jobj = Test_Data.Read_Data("config");
		driver=launch_apk(jobj.get("apk_package").toString(), jobj.get("apk_activity").toString());
		obj=new LoginScreen(driver);
		
	}
	@AfterMethod
	public void killapk(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			String temp = Webutil.getScreenshot(driver);
			logger.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}

		extent.flush();
		driver.close();
	}
	
	@Test(priority=1)
	public void login1() throws InterruptedException, ParseException
	{	
		logger = extent.createTest("At login screen each field should have proper label and validation");
		
		
		obj.get_signin_btn().click();
		String msg = obj.get_sneckbar().getText();
		assertEquals(msg, "Please enter Employee Id");
		obj.get_empid_fld().sendKeys("C79H27");
		obj.get_signin_btn().click();
		String msg1 = obj.get_sneckbar().getText();
		assertEquals(msg1, "Please enter Password");
		
	}
	
	@Test(priority=2)
	public void login2() throws ParseException, InterruptedException
	{
		logger = extent.createTest("As a Lead I should be able to login successfully");

		JSONObject cred = Test_Data.Read_Data("credential");
		obj.get_empid_fld().sendKeys(cred.get("EmployeeID").toString());
		obj.get_password_fld().sendKeys(cred.get("password").toString());
		obj.get_signin_btn().click();
		Thread.sleep(2000);
		
	
	}
	@Test(priority=3)
	public void login3()
	{
		logger = extent.createTest("As a Lead I can not login with invalid credential");	
	
		obj.get_empid_fld().sendKeys("asdasd");
		obj.get_password_fld().sendKeys("dasdasdas");
		obj.get_signin_btn().click();
		String msg = obj.get_sneckbar().getText();
		assertEquals(msg, "Invalid Employee ID or password");
	}
	
}
