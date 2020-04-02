package com.hrm.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyFileUtil {
	private Properties properties;
	private Map<Object, Object> map = new HashMap<>(properties);

	public void loadPropertyFile(String propertyFilePath) {
		System.out.println("Loading " + propertyFilePath + " Property File");

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				System.out.println("Property File :" + propertyFilePath + "loaded successfully !");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Properties file not found at path : " + propertyFilePath);
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException ignore) {
			}
		}
	}

	public String getPropertyValue(String propertyName) {
		System.out.println("Loading Property value for " + propertyName);

		String propertyValue = properties.getProperty(propertyName);
		if (propertyValue != null) {
			System.out.println("Property value loaded succesully for " + propertyName);			
			return propertyValue;
		} else {
			System.out.println("Property value is not loaded succesully for " + propertyName);	
			throw new RuntimeException(
					"Property not specified in the specified properties file for the key" + propertyName);
		}
	}

	public Map<Object, Object> getAllPropertyValue() {
		System.out.println("Loading all Property value initiated");
		
		Enumeration keys = properties.propertyNames();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			map.put(key, properties.getProperty(key));
		}		
		System.out.println("All Property value are loaded successfully");
		return map;
	}
}
