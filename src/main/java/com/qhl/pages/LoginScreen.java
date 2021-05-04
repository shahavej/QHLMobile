package com.qhl.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;

public class LoginScreen {

	@FindBy(id = "com.quickhandslogistics:id/editTextEmployeeId")
	private WebElement empid_fld;
	@FindBy(id = "com.quickhandslogistics:id/editTextPassword")
	private WebElement password_fld;
	@FindBy(id = "com.quickhandslogistics:id/buttonLogin")
	private WebElement signin_btn;
	@FindBy(id = "com.quickhandslogistics:id/textViewForgotPassword")
	private WebElement forgotpwd_ltxt;
	@FindBy(xpath = "//android.widget.TextView[@bounds='[36,2915][1404,3056]']")
	private WebElement sneckbar;
	@FindBy(id = "com.quickhandslogistics:id/mainConstraintLayout")
	private WebElement splash_screen;
	@FindBy(id="com.quickhandslogistics:id/imageView4")
	private WebElement QHL_logo;
	
	
	public LoginScreen(AndroidDriver<WebElement> driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement get_empid_fld() {
		return empid_fld;
	}

	public WebElement get_password_fld() {
		return password_fld;
	}

	public WebElement get_signin_btn() {
		return signin_btn;
	}

	public WebElement get_forgotpwd_ltxt() {
		return forgotpwd_ltxt;
	}
	public WebElement get_sneckbar() {
		return sneckbar;
	}
	
	public WebElement get_splash_screen() {
		return splash_screen;
	}
	public WebElement get_QHL_logo()
	{
		return QHL_logo;
	}
}
