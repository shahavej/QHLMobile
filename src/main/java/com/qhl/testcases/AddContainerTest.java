package com.qhl.testcases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.qhl.base.Apputil;
import com.qhl.base.Test_Data;
import com.qhl.base.Webutil;
import com.qhl.pages.AddContainerScreen;
import com.qhl.pages.LoginScreen;

public class AddContainerTest extends Apputil{

	LoginScreen obj;
	AddContainerScreen ac_Obj;

	@BeforeClass
	public void setup() throws MalformedURLException, ParseException, InterruptedException
	{
		JSONObject jobj = Test_Data.Read_Data("config");
		driver=launch_apk(jobj.get("apk_package").toString(), jobj.get("apk_activity").toString());
		obj=new LoginScreen(driver);
		ac_Obj=new AddContainerScreen(driver);
		
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
	public void addContainer1() throws ParseException, InterruptedException
	{
		logger = extent.createTest("As a Lead after login I should be able to move landing page ");
		

		JSONObject cred = Test_Data.Read_Data("credential");
		obj.get_empid_fld().clear();
		obj.get_empid_fld().sendKeys(cred.get("EmployeeID").toString());
		obj.get_password_fld().clear();
		obj.get_password_fld().sendKeys(cred.get("password").toString());
		obj.get_signin_btn().click();
		Thread.sleep(9000);
		boolean a1=driver.findElement(By.id("com.quickhandslogistics:id/textViewToolbar")).isDisplayed();
		assertEquals(a1, true); 
		
	}
	
	@Test(priority=2)
	public void addContainer2()
	{
		logger = extent.createTest("As a Lead I should be able to navigate on Add Container screen");
		ac_Obj.get_threedot_btn().click();
		ac_Obj.get_addContanier_option().click();
		assertEquals(ac_Obj.get_addContainer_titlelabel().isDisplayed(), true);
	}
	
	@Test(priority=3)
	public void addContainer3()
	{
		logger = extent.createTest("At add Container screen each label should be proper");
		
		assertEquals(ac_Obj.get_addContainer_titlelabel().isDisplayed(),true);
		assertEquals(ac_Obj.get_outBound_label().isDisplayed(), true);
		assertEquals(ac_Obj.get_scheduleNotes_label().isDisplayed(), true);
		assertEquals(ac_Obj.get_addOutbound_btn().isDisplayed(), true);
		assertEquals(ac_Obj.get_add_btn().isEnabled(), false);
		assertEquals(ac_Obj.get_subHeader_txt().getText().toString(), "Add containers to today's schedule");
		
	}
	@Test(priority=4)
	public void addContainer4()
	{
		logger = extent.createTest("At add Container screen i should be able to add muliple add section and remove them for OB");
		
		ac_Obj.get_addOutbound_btn().click();
		assertEquals(ac_Obj.get_removeOB1_btn().isDisplayed(), true);
		assertEquals(ac_Obj.get_removeOB2_btn().isDisplayed(), true);
		assertEquals(ac_Obj.get_removeOB3_btn().isDisplayed(), true);
		assertEquals(ac_Obj.get_outBoundQty_fld().isDisplayed(), true);
		ac_Obj.get_removeOB2_btn().click();
		ac_Obj.get_removeOB3_btn().click();
		
	}
	
	@Test(priority=5)
	public void addContainer5()
	{
		logger = extent.createTest("As a Lead I should be able to add time for 1 OB");
		
		ac_Obj.get_scheduleTime_fld().click();
		ac_Obj.get_togglemode_clock_btn().click();
		ac_Obj.get_hour_fld().sendKeys("12");
		ac_Obj.get_min_fld().sendKeys("30");
		ac_Obj.get_am_pm_spiner().click();
		ac_Obj.get_pm_optn().click();
		ac_Obj.get_ok_btn().click();
		assertEquals(ac_Obj.get_scheduleTime_fld().getText().toString(), "12:30 PM");
		
	}

	@Test(priority=6)
	public void addContainer6()
	{
		logger = extent.createTest("During add Container Notes field is optional Add button should active without fill schedule details");
		
		assertEquals(ac_Obj.get_add_btn().isEnabled(), true);
	}
	
	@Test(priority=7)
	public void addContainer7()
	{
		logger = extent.createTest("As a lead I should be able to add 1 OB with schedule notes");
		
		ac_Obj.get_schedule_fld().sendKeys("Automated schedule");
		ac_Obj.get_add_btn().click();
	}
	
	
}
