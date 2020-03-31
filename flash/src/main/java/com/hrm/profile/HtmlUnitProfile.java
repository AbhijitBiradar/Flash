package com.hrm.profile;

public class HtmlUnitProfile {
	public Capabilities getHtmlUnitDriverCapabilities() {
		DesiredCapabilities unit = DesiredCapabilities.htmlUnitWithJs();
		return unit;
	}
	
	public WebDriver getHtmlUnitDriver(Capabilities cap) {
		return new HtmlUnitDriver(cap);
	}
}
