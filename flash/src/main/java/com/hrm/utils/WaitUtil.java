package com.hrm.utils;

public class WaitUtil {
	public void setImplicitWait(long timeout, TimeUnit unit) {
		Log.info(timeout);
		driver.manage().timeouts().implicitlyWait(timeout, unit == null ? TimeUnit.SECONDS : unit);
	}
	
	public void setPageLoadTimeout(long timeout, TimeUnit unit) {
		Log.info(timeout);
		driver.manage().timeouts().pageLoadTimeout(timeout, unit == null ? TimeUnit.SECONDS : unit);
	}
	
	private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
		Log.debug("");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(pollingEveryInMiliSec, TimeUnit.MILLISECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}
	
	public void waitForElementVisible(WebElement locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
		Log.info(locator);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(ExpectedConditions.visibilityOf(locator));
	}
	
	public void waitForElement(WebDriver driver, WebElement element, long timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		Log.info("element found..."+element.getText());
	}
	
	public WebElement waitForElement(WebDriver driver,long time,WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
}
