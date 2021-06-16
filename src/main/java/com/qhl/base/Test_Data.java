package com.qhl.base;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Test_Data {

	public static void Write_Data(JSONObject obj, String filename) {
		try (FileWriter file = new FileWriter(
				"./TestData/"+filename+".json")) {
			// File Writer creates a file in write mode at the given location
			file.write(obj.toJSONString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static JSONObject Read_Data(String filename) throws org.json.simple.parser.ParseException {
		JSONParser parser = new JSONParser();
		try {
			//Object obj = parser.parse(new FileReader("./TestData/"+filename+".json"));
			Object obj = parser.parse(new FileReader(".\\TestData\\"+filename+".json"));
			
			
			JSONObject jsonObject = (JSONObject) obj;
			return jsonObject;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
