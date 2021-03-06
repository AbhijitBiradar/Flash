package com.hrm.profile;

public class SafariDriverFactory extends AbstractWebDriverFactory implements IWebDriverFactory {

    public SafariDriverFactory(final DriverConfig cfg) {
        super(cfg);
    }

    @Override
    public WebDriver createWebDriver() {
        DesiredCapabilities cap = new SafariCapabilitiesFactory().createCapabilities(webDriverConfig);
        System.out.println("Begin Safari");
        synchronized (this.getClass()) {
            driver = new SafariDriver(cap);
        }

        System.out.println("safari started");

        this.setWebDriver(driver);

        // Implicit Waits handles dynamic element.
        setImplicitWaitTimeout(webDriverConfig.getImplicitWaitTimeout());
        if (webDriverConfig.getPageLoadTimeout() >= 0) {
            TestLogging.log("Safari doesn't support pageLoadTimeout");
        }

        return driver;
    }


}
