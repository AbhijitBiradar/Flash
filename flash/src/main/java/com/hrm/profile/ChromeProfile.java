package com.hrm.profile;

public class ChromeProfile {
	public Capabilities getChromeCapabilities() {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("start-maximized");
		DesiredCapabilities chrome = DesiredCapabilities.chrome();
		chrome.setJavascriptEnabled(true);
		chrome.setCapability(ChromeOptions.CAPABILITY, option);
		return chrome;
	}

	public WebDriver getChromeDriver(Capabilities cap) {
		System.setProperty("webdriver.chrome.driver",
				ResourceHelper.getResourcePath("driver/chromedriver.exe"));
		System.setProperty("webdriver.chrome.logfile",
				ResourceHelper.getResourcePath("logs/chromelogs/")
						+ "chromelog" + DateTimeHelper.getCurrentDateTime()
						+ ".log");
		return new ChromeDriver(cap);
	}
	
	public WebDriver getChromeDriver(String hubUrl,Capabilities cap) throws MalformedURLException {
		return new RemoteWebDriver(new URL(hubUrl), cap);
	}
}
