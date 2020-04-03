package com.hrm.readers;

import com.hrm.constants.TestConstants;
import com.hrm.enums.DriverType;
import com.hrm.enums.EnvironmentType;
import com.hrm.utils.PropertyFileUtil;

public class ConfigFileReader {
	private final String propertyFilePath = "configs//Configuration.properties";
	PropertyFileUtil propertyFileUtil = new PropertyFileUtil();

	public ConfigFileReader() {
		propertyFileUtil.loadPropertyFile(propertyFilePath);
	}

	public String getDriverPath() {
		System.out.println("Loading " + TestConstants.DRIVER_PATH + " from property File");
		String driverPath = propertyFileUtil.getPropertyValue(TestConstants.DRIVER_PATH);
		if (driverPath != null) {
			System.out.println(TestConstants.DRIVER_PATH + " loaded successully from property File");
			return driverPath;
		} else {
			System.out.println("Loading " + TestConstants.DRIVER_PATH + " from property File is failed");
			throw new RuntimeException(
					"Driver Path not specified in the Configuration.properties file for the Key:driverPath");
		}
	}

	public long getImplicitlyWait() {
		System.out.println("Loading "+ TestConstants.IMPLICIT_WAIT +" from property File");
		String implicitlyWait = propertyFileUtil.getPropertyValue(TestConstants.IMPLICIT_WAIT);
		if (implicitlyWait != null) {
			try {
				System.out.println(TestConstants.IMPLICIT_WAIT +" loaded successully from property File");
				return Long.parseLong(implicitlyWait);
			} catch (NumberFormatException e) {
				System.out.println("Loading "+ TestConstants.IMPLICIT_WAIT +" from property File is failed");
				throw new RuntimeException("Not able to parse value : " + implicitlyWait + " in to Long");
			}
		}
		return 30;
	}

	public String getApplicationUrl() {
		System.out.println("Loading "+ TestConstants.URL +" from property File");
		String url = propertyFileUtil.getPropertyValue(TestConstants.URL);
		if (url != null) {
			System.out.println(TestConstants.URL +" loaded successully from property File");
			return url;
		} else {
			System.out.println("Loading "+ TestConstants.URL +" from property File is failed");
			throw new RuntimeException(
					"Application Url not specified in the Configuration.properties file for the Key:url");
		}
	}

	public DriverType getBrowser() {
		System.out.println("Loading "+ TestConstants.BROWSER +" from property File");
		String browserName = propertyFileUtil.getPropertyValue(TestConstants.BROWSER);
		if (browserName == null || browserName.equals(TestConstants.CHROME)) {
			System.out.println(TestConstants.CHROME +" loaded successully from property File");
			return DriverType.CHROME;
		} else if (browserName.equalsIgnoreCase(TestConstants.FIREFOX)) {
			System.out.println(TestConstants.FIREFOX +" loaded successully from property File");
			return DriverType.FIREFOX;
		} else if (browserName.equals(TestConstants.IE_EXPLORER)) {
			System.out.println(TestConstants.IE_EXPLORER +" loaded successully from property File");
			return DriverType.INTERNETEXPLORER;
		} else {
			System.out.println("Loading "+ TestConstants.BROWSER +" from property File is failed");
			throw new RuntimeException(
					"Browser Name Key value in Configuration.properties is not matched : " + browserName);
		}
	}

	public EnvironmentType getEnvironment() {
		System.out.println("Loading "+ TestConstants.ENVIRONMENT +" from property File");
		String environmentName = propertyFileUtil.getPropertyValue(TestConstants.ENVIRONMENT);
		if (environmentName == null || environmentName.equalsIgnoreCase(TestConstants.LOCAL)) {
			System.out.println(TestConstants.LOCAL +" loaded successully from property File");
			return EnvironmentType.LOCAL;
		} else if (environmentName.equals(TestConstants.REMOTE)) {
			System.out.println(TestConstants.REMOTE +" loaded successully from property File");
			return EnvironmentType.REMOTE;
		} else {
			System.out.println("Loading "+ TestConstants.ENVIRONMENT +" from property File is failed");
			throw new RuntimeException(
					"Environment Type Key value in Configuration.properties is not matched : " + environmentName);
		}
	}

	public Boolean getBrowserWindowSize() {
		System.out.println("Loading "+ TestConstants.WINDOW_MAXIMIZE +" from property File");
		String windowSize = propertyFileUtil.getPropertyValue(TestConstants.WINDOW_MAXIMIZE);
		if (windowSize != null) {
			System.out.println(TestConstants.WINDOW_MAXIMIZE +" loaded successully from property File");
			return Boolean.valueOf(windowSize);
		} else {
			System.out.println(TestConstants.WINDOW_MAXIMIZE +" loaded successully from property File");
			return true;
		}
	}

	public String getTestDataResourcePath() {
		System.out.println("Loading "+ TestConstants.TEST_DATA_FILE_PATH +" from property File");
		String testDataResourcePath = propertyFileUtil.getPropertyValue(TestConstants.TEST_DATA_FILE_PATH);
		if (testDataResourcePath != null) {
			System.out.println(TestConstants.TEST_DATA_FILE_PATH +" loaded successully from property File");
			return testDataResourcePath;
		} else {
			System.out.println("Loading "+ TestConstants.TEST_DATA_FILE_PATH +" from property File is failed");
			throw new RuntimeException(
					"Test Data Resource Path not specified in the Configuration.properties file for the Key:testDataResourcePath");
		}
	}

	public String getUserName() {
		System.out.println("Loading "+ TestConstants.USER_NAME +" from property File");
		String userName = propertyFileUtil.getPropertyValue(TestConstants.USER_NAME);
		if (userName != null) {
			System.out.println(TestConstants.USER_NAME +" loaded successully from property File");
			return userName;
		} else {
			System.out.println("Loading "+ TestConstants.USER_NAME +" from property File is failed");
			throw new RuntimeException(
					"User Name not specified in the Configuration.properties file for the Key:userName");
		}
	}

	public String getPassword() {
		System.out.println("Loading "+ TestConstants.PASSWORD +" from property File");
		String password = propertyFileUtil.getPropertyValue(TestConstants.PASSWORD);
		if (password != null) {
			System.out.println(TestConstants.PASSWORD +" loaded successully from property File");
			return password;
		} else {
			System.out.println("Loading "+ TestConstants.PASSWORD +" from property File is failed");
			throw new RuntimeException(
					"Password not specified in the Configuration.properties file for the Key:Password");
		}
	}
}
