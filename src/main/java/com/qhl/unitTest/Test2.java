package com.qhl.unitTest;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.qhl.base.Test_Data;

public class Test2 {

	JSONObject obj = new JSONObject();
	Faker fake = new Faker();

	@Test
	public void test1() {

		String name = fake.name().firstName();
		String email = fake.internet().emailAddress();

		obj.put("First Name", name);
		obj.put("Email", email);
		Test_Data.Write_Data(obj, "xyz");
	}

	@Test
	public void test2() throws ParseException {
		JSONObject a = Test_Data.Read_Data("credential");
		System.out.println(a.get("EmployeeID"));
		//System.out.println(a.get("Email"));

	}
}
