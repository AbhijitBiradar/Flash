package com.hrm.profile;

public class PhantomJsProfile {
	public PhantomJSDriverService getPhantomJsService() {
		return new PhantomJSDriverService.Builder()
				.usingAnyFreePort()
				.usingPhantomJSExecutable(
						new File(ResourceHelper
								.getResourcePath("driver/phantomjs.exe")))
				.withLogFile(
						new File(ResourceHelper
								.getResourcePath("logs/phantomjslogs/")
								+ "phantomjslogs"
								+ DateTimeHelper.getCurrentDateTime() + ".log"))
				.build();

	}

	public Capabilities getPhantomJsCapability() {
		DesiredCapabilities cap = DesiredCapabilities.phantomjs();
		cap.setJavascriptEnabled(true);
		return cap;
	}

	public WebDriver getPhantomJsDriver(PhantomJSDriverService sev,
			Capabilities cap) {

		return new PhantomJSDriver(sev, cap);
	}
	
	public WebDriver getPhantomJsDriver(String hubUrl,PhantomJSDriverService sev,
			Capabilities cap) throws MalformedURLException {

		return new RemoteWebDriver(new URL(hubUrl), cap);
	}
}
