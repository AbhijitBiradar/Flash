package com.hrm.drivers;

public Interface IWebDriverFactory {
	void cleanUp();

    WebDriver createWebDriver() throws Exception;

    WebDriver getWebDriver();

    DriverConfig getWebDriverConfig();
}
