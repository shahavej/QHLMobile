package com.qhl.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class AddContainerScreen {

	@FindBy(xpath = "//android.widget.ImageView[@bounds='[1320,103][1440,247]']")
	private WebElement threedot_btn;
	@FindBy(xpath="com.quickhandslogistics:id/content")
	private WebElement addContanier_option;
	@FindBy(id="com.quickhandslogistics:id/textViewToolbar")
	private WebElement addContainer_titlelabel;
	@FindBy(id="com.quickhandslogistics:id/textSubHeader")
	private WebElement subHeader_txt;
	@FindBy(id="com.quickhandslogistics:id/textViewOutBound")
	private WebElement outBound_label;
	@FindBy(id="com.quickhandslogistics:id/headerScheduleNote")
	private WebElement scheduleNotes_label;
	@FindBy(id="com.quickhandslogistics:id/buttonAdd")
	private WebElement add_btn;
	@FindBy(id="com.quickhandslogistics:id/textViewAddOutBound")
	private WebElement addOutbound_btn;
	@FindBy(id="com.quickhandslogistics:id/editTextScheduleNote")
	private WebElement schedule_fld;
	@FindBy(xpath="//android.widget.TextView[@index='1']")
	private WebElement removeOB1_btn;
	@FindBy(xpath="//android.widget.TextView[@index='3']")
	private WebElement outBoundQty_fld;
	@FindBy(xpath="//android.widget.TextView[@index='5']")
	private WebElement scheduleTime_fld;
	@FindBy(xpath="//android.widget.TextView[@index='7']")
	private WebElement removeOB2_btn;
	@FindBy(xpath="//android.widget.TextView[@index='13']")
	private WebElement removeOB3_btn;
	@FindBy(id="android:id/toggle_mode")
	private WebElement togglemode_clock_btn;
	@FindBy(id="android:id/input_hour")
	private WebElement hour_fld;
	@FindBy(id="android:id/input_minute")
	private WebElement min_fld;
	@FindBy(id="android:id/am_pm_spinner")
	private WebElement am_pm_spiner;
	@FindBy(xpath="//android.widget.CheckedTextView[@text='AM']")
	private WebElement am_optn;
	@FindBy(xpath="//android.widget.CheckedTextView[@text='PM']")
	private WebElement pm_optn;
	@FindBy(xpath="android:id/button1")
	private WebElement ok_btn;

	public AddContainerScreen(AndroidDriver<WebElement> driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement get_threedot_btn() {
		return threedot_btn;
	}

	public WebElement get_addContanier_option() {
		return addContanier_option;
	}
	public WebElement get_addContainer_titlelabel() {
		return addContainer_titlelabel;
	}
	public WebElement get_subHeader_txt() {
		return subHeader_txt;
	}
	public WebElement get_outBound_label() {
		return outBound_label;
	}
	public WebElement get_scheduleNotes_label() {
		return scheduleNotes_label;
	}
	public WebElement get_add_btn() {
		return add_btn;
	}
	public WebElement get_addOutbound_btn() {
		return addOutbound_btn;
	}
	public WebElement get_schedule_fld() {
		return schedule_fld;
	}
	public WebElement get_removeOB1_btn() {
		return removeOB1_btn;
	}
	public WebElement get_outBoundQty_fld() {
		return outBoundQty_fld;
	}
	public WebElement get_scheduleTime_fld() {
		return scheduleTime_fld;
	}
	public WebElement get_removeOB2_btn() {
		return removeOB2_btn;
	}
	public WebElement get_removeOB3_btn()
	{
		return removeOB3_btn;
	}
	public WebElement get_togglemode_clock_btn()
	{
		return togglemode_clock_btn;
	}
	public WebElement get_hour_fld() {
		return hour_fld;
	}
	public WebElement get_min_fld() {
		return min_fld;
	}
	public WebElement get_am_pm_spiner() {
		return am_pm_spiner;
	}
	public WebElement get_am_optn() {
		return am_optn;
	}
	public WebElement get_pm_optn() {
		return pm_optn;
	}
	public WebElement get_ok_btn() {
		return ok_btn;
	}
}
