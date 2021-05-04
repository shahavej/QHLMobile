package com.qhl.testcases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.qhl.base.Apputil;
import com.qhl.base.Mailinator;
import com.qhl.base.Test_Data;
import com.qhl.base.Webutil;
import com.qhl.pages.ForgotPasswordScreen;
import com.qhl.pages.LoginScreen;

public class ForgotPasswordTest extends Apputil {
	ForgotPasswordScreen fps;
	LoginScreen ls;

	@BeforeClass
	public void setup() throws MalformedURLException, ParseException, InterruptedException
	{
		JSONObject jobj = Test_Data.Read_Data("config");
		driver=launch_apk(jobj.get("apk_package").toString(), jobj.get("apk_activity").toString());
		fps = new ForgotPasswordScreen(driver);
		ls = new LoginScreen(driver);

	}

	@AfterMethod
	public void killapk(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			String temp = Webutil.getScreenshot(driver);
			logger.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}

		extent.flush();
		//driver.close();
	}

	@Test(priority=1)
	public void forgot_password01()
	{
		logger = extent.createTest("At Forgot password screen should have proper title");
		
		ls.get_forgotpwd_ltxt().click();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		boolean r1 = driver.findElement(By.xpath("//android.widget.TextView[@text='Forgot Password']")).isDisplayed();
		assertEquals(r1, true);

	}
	
	@Test(priority=2)
	public void forgot_password02() throws ParseException
	{
		logger = extent.createTest("Confirmation message should apepar after send the link on mail Id");
		
		
		JSONObject cjson = Test_Data.Read_Data("credential");
		fps.get_empid_fld().sendKeys(cjson.get("EmployeeID").toString());
		fps.get_reset_btn().click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		boolean r1 = fps.get_OK_btn().isDisplayed();
		assertEquals(r1, true);
		fps.get_OK_btn().click();
		
	}
	
	@Test(priority=3)
	public void forgot_password03() throws InterruptedException, ParseException
	{
		logger = extent.createTest("As a Lead I should get an email on registered email id");
		
		
		JSONObject cjson = Test_Data.Read_Data("credential");
		String password = Mailinator.set_password(cjson.get("emailid").toString());
		JSONObject obj = new JSONObject();
		obj.put("password", password);
		obj.put("emailid", cjson.get("emailid").toString());
		obj.put("EmployeeID", cjson.get("EmployeeID").toString());
		Test_Data.Write_Data(obj, "credential");
		
	}
	
	@Test(priority=4)
	public void forgot_password04() throws ParseException, InterruptedException, MalformedURLException
	{
		logger = extent.createTest("As a Lead I should be able to login with the valid new password");
		
		JSONObject jobj = Test_Data.Read_Data("config");
		driver=launch_apk(jobj.get("apk_package").toString(), jobj.get("apk_activity").toString());
		LoginScreen ls1 = new LoginScreen(driver);
		
		JSONObject cobj = Test_Data.Read_Data("credential");
		ls1.get_empid_fld().sendKeys(cobj.get("EmployeeID").toString());
		ls1.get_password_fld().sendKeys(cobj.get("password").toString());
		ls1.get_signin_btn().click();
		
		Thread.sleep(8000);
		boolean a1=driver.findElement(By.id("com.quickhandslogistics:id/textViewToolbar")).isDisplayed();
		assertEquals(a1, true);
		
	}
}
