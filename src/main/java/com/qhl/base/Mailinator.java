package com.qhl.base;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import com.github.javafaker.Faker;

public class Mailinator {

	@FindBy(id = "addOverlay")
	public WebElement search_fld;

	@FindBy(xpath = "//button[contains(text(),'GO')]")
	public WebElement go_btn;

	@FindBy(xpath = "//td[4]//a[contains(text(),'Reset Password')][1]")
	public WebElement reset_pwd_lnk;

	@FindBy(id = "msg_body")
	public static WebElement msg_frame;


	public static String get_OTP(String email) throws InterruptedException

	{
		WebDriver w_driver = set_prop(email);
		Thread.sleep(3000);
		//w_driver.findElement(By.xpath("//tr[1]//td[4]//a[contains(text(),'Forgot Password OTP')]")).click();
		w_driver.findElement(By.xpath("//tr//td[4]//a[contains(text(),'OTP')]")).click();
		// switch into message frame
		w_driver.switchTo().frame(0);
		String message = w_driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]")).getText();
		String otp = message.replaceAll("\\D", "");
		w_driver.close();
		return otp;

	}
	public static String set_password(String email) throws InterruptedException

	{
		WebDriver w_driver = set_prop(email);
		Thread.sleep(3000);
		//w_driver.findElement(By.xpath("//tr//td[4]//a[contains(text(),'Reset password')]")).click();
		w_driver.findElement(By.xpath("//tr//td[contains(text(),'Reset password')]")).click();

		// switch into message frame
		w_driver.switchTo().frame(0);

		w_driver.findElement(By.xpath("//a[contains(text(),'Click here to reset your password')]")).click();

		try{
			Set<String> handleValues=w_driver.getWindowHandles();
			for(String handleValue:handleValues){
				w_driver.switchTo().window(handleValue);	
				if(w_driver.getTitle().trim().equalsIgnoreCase("QuickHandsDashboard")){
					break;
				}
			}
		}catch(Exception e){
			//logger.debug( Constants.ELEMENT_SEARCH_ERROR_MESSAGE, e);
		}

		Faker fake = new Faker();
		String password = fake.name().firstName()+"@321";
		System.out.println("in mailinator :"+password);
		w_driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys(password);
		w_driver.findElement(By.xpath("//input[@formcontrolname='confirmPassword']")).sendKeys(password);
		w_driver.findElement(By.id("m_login_forget_password_submit")).click();
		Thread.sleep(3000);
		w_driver.close();
		return password;
	}



	/*
		String pid = w_driver.getWindowHandle();
		Set<String> sid = w_driver.getWindowHandles();

		for (String str : sid) {
			if(!pid.equalsIgnoreCase(sid.toString()))
			{
				w_driver.switchTo().window(str);
				w_driver.manage().window().maximize();
				System.out.println(w_driver.getTitle());
				w_driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("password");

			}

		}

		String message = w_driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]")).getText();
		String otp = message.replaceAll("\\D", "");
		w_driver.close();
		return otp;
	 */



	public static WebDriver set_prop(String email) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver",".//Exe//91.exe");
		WebDriver w_driver = new ChromeDriver();
		w_driver.manage().window().maximize();
		w_driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		w_driver.get("https://www.mailinator.com/");
		w_driver.findElement(By.id("addOverlay")).sendKeys(email);
		w_driver.findElement(By.xpath("//button[contains(text(),'GO')]")).click();
		Thread.sleep(3000);
		return w_driver;

	}




}