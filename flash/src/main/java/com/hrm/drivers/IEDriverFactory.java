package com.hrm.drivers;

public class IEDriverFactory extends AbstractWebDriverFactory implements IWebDriverFactory{
	public IEDriverFactory(final DriverConfig webDriverConfig1) {
        super(webDriverConfig1);
    }

    @Override
    public void cleanUp() {
        try {
            if (driver != null) {
                try {
                    driver.quit();
                } catch (WebDriverException ex) {
                    ex.printStackTrace();
                }

                driver = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public WebDriver createWebDriver() throws IOException {

        // killProcess();
        if (!OSUtility.isWindows()) {
            throw new RuntimeException("With gods grace IE browser is only supported on windows, Imagine a "
                    + "situation when you have to fix IE bugs on Unix and Mac as well");
        }

        DriverConfig cfg = this.getWebDriverConfig();

        driver = new InternetExplorerDriver(new IECapabilitiesFactory().createCapabilities(cfg));

        // Implicit Waits to handle dynamic element. The default value is 5 seconds.
        setImplicitWaitTimeout(cfg.getImplicitWaitTimeout());
        if (cfg.getPageLoadTimeout() >= 0) {
            driver.manage().timeouts().pageLoadTimeout(cfg.getPageLoadTimeout(), TimeUnit.SECONDS);
        }

        this.setWebDriver(driver);
        return driver;
    }

    private void killProcess() {
        if (OSUtility.isWindows()) {
            try {
                Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
                Runtime.getRuntime().exec("taskkill /F /IM Iexplore.exe");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
