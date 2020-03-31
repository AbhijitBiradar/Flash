package com.hrm.profile;

public class FirefoxProfile {
	//https://github.com/tarun3kumar/seleniumtestsframework/blob/master/src/main/java/com/seleniumtests/browserfactory/FirefoxCapabilitiesFactory.java
	
	public Capabilities getFirefoxCapabilities() {
		DesiredCapabilities firefox = DesiredCapabilities.firefox();
		FirefoxProfile profile = new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(true);
		firefox.setCapability(FirefoxDriver.PROFILE, profile);
		firefox.setCapability("marionette", true);
		return firefox;
	}
	
	public WebDriver getFirefoxDriver(Capabilities cap) {
		System.setProperty("webdriver.gecko.driver", ResourceHelper.getResourcePath("driver/geckodriver.exe"));
		return new FirefoxDriver(cap);
	}
	
	public WebDriver getFirefoxDriver(String hubUrl,Capabilities cap) throws MalformedURLException {
		return new RemoteWebDriver(new URL(hubUrl),cap);
	}
}
