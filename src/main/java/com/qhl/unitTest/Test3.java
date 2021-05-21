package com.qhl.unitTest;

import java.io.File;
import java.text.SimpleDateFormat;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Test3{

	static String NodePath ="C:/Program Files/nodejs/node.exe";
	static String AppiumMainJs_path="C:/Program Files/Appium/resources/app/node_modules/appium/build/lib/main.js";

	static AppiumDriverLocalService	service;
	static SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
/*
	@BeforeTest
	public static void startServer()
	{
		service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingDriverExecutable(new File(NodePath)).withAppiumJS(new File(AppiumMainJs_path))
				.withIPAddress("127.0.0.1").usingPort(4723).withLogFile(new File("D:\\AppiumProject\\a.txt"))
				);
		
		service.start();
		System.out.println("start server");
	}

	@Test
	public void te1()
	{
		System.out.println("this is run");
	}
	
	@AfterClass
	public void t()
	{
		service.stop();
		System.out.println("stop server");
	}


*/



	@Test()
	public void m()
	{
		String Appium_Node_Path=NodePath;
    	String Appium_JS_Path= AppiumMainJs_path;
    	AppiumDriverLocalService service;

    	service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().
    		                usingAnyFreePort().usingDriverExecutable(new File(Appium_Node_Path)).
    		                withAppiumJS(new File(Appium_JS_Path)));
    	service.start();
    	System.out.println("appium started");
	}

	
    	
	 

}
