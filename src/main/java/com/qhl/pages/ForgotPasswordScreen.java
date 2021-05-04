package com.qhl.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class ForgotPasswordScreen {
	
	@FindBy(id = "com.quickhandslogistics:id/editTextEmpId")
	private WebElement empid_fld;
	@FindBy(id = "com.quickhandslogistics:id/buttonPasswordReset")
	private WebElement reset_btn;
	@FindBy(id = "com.quickhandslogistics:id/confirm_button")
	private WebElement OK_btn;
	
	public ForgotPasswordScreen(AndroidDriver<WebElement> driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement get_empid_fld() {
		return empid_fld;
	}

	public WebElement get_reset_btn() {
		return reset_btn;
	}

	public WebElement get_OK_btn() {
		return OK_btn;
	}
}
