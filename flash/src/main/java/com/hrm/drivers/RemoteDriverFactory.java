package com.hrm.drivers;

public class RemoteDriverFactory extends AbstractWebDriverFactory implements IWebDriverFactory{
	public RemoteDriverFactory(final DriverConfig cfg) {
        super(cfg);
    }

    @Override
    public WebDriver createWebDriver() throws MalformedURLException, IllegalArgumentException, SecurityException,
        InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException,
        ClassNotFoundException {
        DriverConfig webDriverConfig = this.getWebDriverConfig();
        DesiredCapabilities capability = null;
        URL url;

        url = new URL(webDriverConfig.getHubUrl());

        switch (webDriverConfig.getBrowser()) {

            case FireFox :
                capability = new FirefoxCapabilitiesFactory().createCapabilities(webDriverConfig);
                break;

            case InternetExplore :
                capability = new IECapabilitiesFactory().createCapabilities(webDriverConfig);
                break;

            case Chrome :
                capability = new ChromeCapabilitiesFactory().createCapabilities(webDriverConfig);
                break;

            case Safari :
                capability = new SafariCapabilitiesFactory().createCapabilities(webDriverConfig);
                break;

            case Android :
                capability = new AndroidCapabilitiesFactory().createCapabilities(webDriverConfig);
                break;

            case IPhone :
                capability =
                    ((ICapabilitiesFactory) Class.forName("com.seleniumtests.browserfactory.IPhoneCapabilitiesFactory")
                            .getConstructor().newInstance()).createCapabilities(webDriverConfig);
                break;

            default :
                break;
        }

        switch (webDriverConfig.getBrowser()) {

            case IPhone :
            case FireFox :
                try {
                    driver = new ScreenShotRemoteWebDriver(url, capability);
                } catch (RuntimeException e) {
                    if (e.getMessage().contains(
                                "Unable to connect to host 127.0.0.1 on port 7062 after 45000 ms. Firefox console output")) {
                        TestLogging.log("Firefox Driver creation got port customexception, retry after 5 seconds");
                        WaitHelper.waitForSeconds(5);
                        driver = new ScreenShotRemoteWebDriver(url, capability);
                    } else {
                        throw e;
                    }
                }

                break;

            default :
                driver = new ScreenShotRemoteWebDriver(url, capability);
        }

        setImplicitWaitTimeout(webDriverConfig.getImplicitWaitTimeout());
        if (webDriverConfig.getPageLoadTimeout() >= 0) {
            setPageLoadTimeout(webDriverConfig.getPageLoadTimeout(), webDriverConfig.getBrowser());
        }

        this.setWebDriver(driver);

        String hub = url.getHost();
        int port = url.getPort();

        // logging node ip address:
        try {
            HttpHost host = new HttpHost(hub, port);
            DefaultHttpClient client = new DefaultHttpClient();
            String sessionUrl = "http://" + hub + ":" + port + "/grid/api/testsession?session=";
            URL session = new URL(sessionUrl + ((RemoteWebDriver) driver).getSessionId());
            BasicHttpEntityEnclosingRequest req;
            req = new BasicHttpEntityEnclosingRequest("POST", session.toExternalForm());

            org.apache.http.HttpResponse response = client.execute(host, req);
            String responseContent = EntityUtils.toString(response.getEntity());
            try {
                JSONObject object = new JSONObject(responseContent);
                String proxyId = (String) object.get("proxyId");
                String node = (proxyId.split("//")[1].split(":")[0]);
                String browserName = ((RemoteWebDriver) driver).getCapabilities().getBrowserName();
                String version = ((RemoteWebDriver) driver).getCapabilities().getVersion();
                System.out.println("WebDriver is running on node " + node + ", " + browserName + version + ", session "
                        + ((RemoteWebDriver) driver).getSessionId());
                TestLogging.log("WebDriver is running on node " + node + ", " + browserName + version + ", session "
                        + ((RemoteWebDriver) driver).getSessionId());
            } catch (org.json.JSONException e) { }
        } catch (Exception ex) { }

        return driver;
    }

    protected void setPageLoadTimeout(final long timeout, final BrowserType type) {
        switch (type) {

            case Chrome :
                try {
                    driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
                } catch (UnsupportedCommandException e) {
                    e.printStackTrace();
                }

                break;

            case FireFox :
            case InternetExplore :
                driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
                break;

            default :
        }
    }
}
