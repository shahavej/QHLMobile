package com.qhl.base;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.io.Files;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Apputil {
	public static AndroidDriver<WebElement> driver;
	public DesiredCapabilities dc;
	String folder_name;
	DateFormat df;

	/* for reporting */
	public ExtentReports extent;
	public ExtentTest logger;

	@BeforeSuite
	public void report_setup() {

		/* Report Name setup :) */
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
		LocalDateTime now = LocalDateTime.now();
		String a = dtf.format(now);
		ExtentHtmlReporter reporter = new ExtentHtmlReporter("./Report/"+a+".html");
		/*-------------------------------------------------------------------------*/
		extent = new ExtentReports();
		extent.attachReporter(reporter);

	}

	public void install_and_launch_apk(String Apk_path) throws MalformedURLException {
		DesiredCapabilities dc = new DesiredCapabilities();
	
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		dc.setCapability(MobileCapabilityType.APP, Apk_path); // pass the path of the App
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		dc.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0"); // you can
		// set the version for virtual device
		URL url = new URL("http://127.0.0.1:4723/wd/hub");// this is the Appium server path
		driver = new AndroidDriver<WebElement>(url, dc);
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

	}

	public AndroidDriver<WebElement> launch_apk(String apk_package, String apk_activity) throws MalformedURLException, InterruptedException {
		dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
		dc.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
		dc.setCapability("appPackage", apk_package);
		dc.setCapability("appActivity", apk_activity);
		URL url = new URL("http://127.0.0.1:4723/wd/hub");// this is the Appium server path
		driver = new AndroidDriver<WebElement>(url, dc);
		Thread.sleep(5000);
		return driver;

	}

	public void click(WebElement ele) {
		ele.click();
	}

	public void sendkeys(WebElement ele, String txt) {
		ele.sendKeys(txt);
	}

	public void handle_dropdown(WebElement dropdown, String value) throws InterruptedException {

		// driver.findElementById("android:id/text1").click();// click on dropdown
		dropdown.click();
		List<WebElement> options = driver.findElementsById("android:id/text1");
		// System.out.println("Total number of options available in dropdown:" +
		// options.size());
		for (WebElement e : options) {
			String val = e.getText();
			if (val.equalsIgnoreCase(value)) {
				e.click();
				break;
			}
		}
		Thread.sleep(8000);
	}

	public void captureScreenShots(String file_name) throws IOException {
		folder_name = "screenshot";
		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// create dir with given folder name
		new File(folder_name).mkdir();
		// Setting file name
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
		LocalDateTime now = LocalDateTime.now();
		String a = dtf.format(now) + ".png";
		// copy screenshot file into screenshot folder.
		Files.copy(f, new File(folder_name + "/" + a));
	}

	public void scrollTillandClick(String txt) {
		MobileElement listele1 = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(" + "new UiSelector().text(\"" + txt + "\"));"));
		listele1.click();
	}

	public void scrollTill(String txt) {
		MobileElement listele1 = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(" + "new UiSelector().text(\"" + txt + "\"));"));

	}

	public String MobileNumber() {
		int num1, num2, num3; // 3 numbers in area code
		int set2, set3; // sequence 2 and 3 of the phone number
		Random generator = new Random();
		num1 = generator.nextInt(7) + 1; // add 1 so there is no 0 to begin
		num2 = generator.nextInt(8); // randomize to 8 becuase 0 counts as a number in the generator
		num3 = generator.nextInt(8);
		set2 = generator.nextInt(643) + 100;
		set3 = generator.nextInt(8999) + 1000;
		String phone = (+num1 + "" + num2 + "" + num3 + +set2 + +set3);
		return phone;
	}

	private static final String ALPHA_NUMERIC_STRING = "0123456789";

	public static String randomNumber(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	private static final String ALPHA_NUMERIC_STRING1 = "!@#$%^&*QWERTYUIOPqwertyuiopaASDFGHJK0123456789";

	public static String randomPassword(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING1.length());
			builder.append(ALPHA_NUMERIC_STRING1.charAt(character));
		}
		return builder.toString();
	}
	
	public void bc() throws ParseException, MalformedURLException, InterruptedException
	{
		JSONObject jobj = Test_Data.Read_Data("config");
		driver=launch_apk(jobj.get("apk_package").toString(), jobj.get("apk_activity").toString());
	}
	
	public void am(ITestResult result) throws IOException
	{
		if (result.getStatus() == ITestResult.FAILURE) {
			String temp = Webutil.getScreenshot(driver);
			logger.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
		extent.flush();
	}
	
}