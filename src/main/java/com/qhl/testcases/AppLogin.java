package com.qhl.testcases;

import static org.testng.Assert.assertEquals;
import java.util.concurrent.TimeUnit;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.qhl.base.Apputil;
import com.qhl.base.Test_Data;
import com.qhl.pages.LoginScreen;

public class AppLogin extends Apputil {
	LoginScreen obj;

	@Test(priority=1)
	public void login01()
	{
		logger = extent.createTest("After launch the apk Flash screen should appear");
		
		obj=new LoginScreen(driver);
		assertEquals(obj.get_splash_screen().isDisplayed(), true);
	}
	
	@Test(priority=2)
	public void login02()
	{
		logger = extent.createTest("At Login Screen QHL logo should be visible");
		
		boolean r1 = obj.get_QHL_logo().isDisplayed();
		assertEquals(r1, true);
		
	}
	
	@Test(priority=3)
	public void login03()
	{
		logger = extent.createTest("At login screen forgot password link text should appear");
		
		boolean r1 = obj.get_forgotpwd_ltxt().isDisplayed();
		assertEquals(r1, true);
	
	}
	
	@Test(priority=4)
	public void login04() throws InterruptedException
	{
		logger = extent.createTest("At login screen by click on forgot password i should be able to move on forgot password screen");	
		
		obj.get_forgotpwd_ltxt().click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		boolean r1 = driver.findElement(By.xpath("//android.widget.TextView[@text='Forgot Password']")).isDisplayed();
		assertEquals(r1, true);

		driver.findElement(By.xpath("//android.widget.ImageButton[@bounds='[0,91][168,259]']")).click();
		Thread.sleep(3000);
		
	}

	@Test(priority=5)
	public void login05() throws InterruptedException, ParseException
	{	
		logger = extent.createTest("At login screen each field should have proper label and validation");

		obj.get_signin_btn().click();
		
		boolean r1 = driver.findElement(By.xpath("//android.widget.TextView[@text='Please enter Employee Id']")).isDisplayed();
		assertEquals(r1, true);
		Thread.sleep(1000);
		
		obj.get_empid_fld().sendKeys("C79H27");
		obj.get_signin_btn().click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		boolean r2 = driver.findElement(By.xpath("//android.widget.TextView[@text='Please enter Password']")).isDisplayed();
		assertEquals(r2, true);
		

	}

	@Test(priority=6)
	public void login06() throws InterruptedException
	{
		logger = extent.createTest("As a Lead I can not login with invalid credential");	

		obj.get_empid_fld().clear();
		obj.get_empid_fld().sendKeys("asdasd");
		obj.get_password_fld().clear();
		obj.get_password_fld().sendKeys("dasdasdas");
		obj.get_signin_btn().click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		String msg = obj.get_sneckbar().getText().toString();
		
		assertEquals(msg, "Invalid employee ID or password");
	}
	
	
	@Test(priority=7)
	public void login07() throws ParseException, InterruptedException
	{
		logger = extent.createTest("As a Lead I should be able to login successfully");

		JSONObject cred = Test_Data.Read_Data("credential");
		obj.get_empid_fld().clear();
		obj.get_empid_fld().sendKeys(cred.get("EmployeeID").toString());
		obj.get_password_fld().clear();
		obj.get_password_fld().sendKeys(cred.get("password").toString());
		obj.get_signin_btn().click();
		Thread.sleep(9000);
		boolean a1=driver.findElement(By.id("com.quickhandslogistics:id/textViewToolbar")).isDisplayed();
		assertEquals(a1, true);
		driver.findElement(By.xpath("//android.widget.ImageButton[@index='0']")).click();
	}
}
