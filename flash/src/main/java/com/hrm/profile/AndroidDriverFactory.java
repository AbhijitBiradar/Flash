package com.hrm.profile;

public class AndroidDriverFactory extends AbstractWebDriverFactory implements IWebDriverFactory {

    public AndroidDriverFactory(final DriverConfig webDriverConfig) {
        super(webDriverConfig);
    }

    protected WebDriver createNativeDriver() throws MalformedURLException {

        return new AndroidDriver(new URL(webDriverConfig.getAppiumServerURL()), new AndroidCapabilitiesFactory()
                    .createCapabilities(webDriverConfig));
    }

    @Override
    public WebDriver createWebDriver() throws IOException {
        DriverConfig cfg = this.getWebDriverConfig();

        driver = createNativeDriver();

        setImplicitWaitTimeout(cfg.getImplicitWaitTimeout());
        if (cfg.getPageLoadTimeout() >= 0) {
            setPageLoadTimeout(cfg.getPageLoadTimeout());
        }

        this.setWebDriver(driver);
        return driver;
    }

    protected void setPageLoadTimeout(final long timeout) {
        try {
            driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
        } catch (UnsupportedCommandException e) {
        }
    }

}
