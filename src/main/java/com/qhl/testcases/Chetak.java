package com.qhl.testcases;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.qhl.base.Apputil;

public class Chetak extends Apputil {


	@Test
	public void test1() throws MalformedURLException, InterruptedException
	{

		driver=launch_apk("com.bajajmychetak","com.bajajmychetak.MainActivity");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//android.widget.EditText[@bounds='[416,1146][1310,1251]']")).sendKeys("7291044185");
		driver.findElement(By.xpath("//android.widget.EditText[@bounds='[130,1378][1216,1483]']")).sendKeys("Test@12345");
		driver.findElement(By.xpath("//android.view.ViewGroup[@bounds='[130,2629][1310,2789]']")).click();
		Thread.sleep(5000);
		
	}

}
