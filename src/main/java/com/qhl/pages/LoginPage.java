package com.qhl.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	@FindBy(xpath = "//input[@name='username']")
	private WebElement username_fld;
	@FindBy(xpath = "//input[@name='password']")
	private WebElement password_fld;
	@FindBy(id = "m_login_signin_submit")
	private WebElement signin_btn;

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement get_username_fld() {
		return username_fld;
	}

	public WebElement get_password_fld() {
		return password_fld;
	}

	public WebElement get_signin_btn() {
		return signin_btn;
	}
}
