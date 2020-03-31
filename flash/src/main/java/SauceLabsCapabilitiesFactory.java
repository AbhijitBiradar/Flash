
public class SauceLabsCapabilitiesFactory implements ICapabilitiesFactory {
    @Override
    public DesiredCapabilities createCapabilities(final DriverConfig cfg) {

        final DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("browserName", cfg.getBrowserName());
        capabilities.setCapability("platform", cfg.getPlatform());
        capabilities.setCapability("version", cfg.getVersion());
        capabilities.setCapability("name",
            SeleniumTestsContextManager.getThreadContext().getAttribute(SeleniumTestsContext.TEST_METHOD_SIGNATURE));

        return capabilities;
    }

}
