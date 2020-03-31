package com.hrm.drivers;

public class ChromeDriverFactory extends AbstractWebDriverFactory implements IWebDriverFactory {

    public ChromeDriverFactory(final DriverConfig cfg) {
        super(cfg);
    }

    protected WebDriver createNativeDriver() {
        return new ChromeDriver(new ChromeCapabilitiesFactory().createCapabilities(webDriverConfig));
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
        } catch (UnsupportedCommandException e) { }
    }


}
