package com.hrm.actions;

public class WebAction {
	private WebDriver driver;
	
	public void resizeWindow(final int width, final int height) {
        try {
            TestLogging.logWebStep("Resize browser window to width " + width + " height " + height, false);

            Dimension size = new Dimension(width, height);
            driver.manage().window().setPosition(new Point(0, 0));
            driver.manage().window().setSize(size);
        } catch (Exception ex) { }
    }

    public void maximizeWindow() {
        try {
            BrowserType browser = BrowserType.getBrowserType(WebUIDriver.getWebUIDriver().getBrowser());
            if (browser == BrowserType.Android || browser == BrowserType.IPhone) {
                return;
            }

            driver.manage().window().maximize();
        } catch (Exception ex) {

            try {
                ((JavascriptExecutor) driver).executeScript(
                    "if (window.screen){window.moveTo(0, 0);window.resizeTo(window.screen.availWidth,window.screen.availHeight);}");
            } catch (Exception ignore) {
                TestLogging.log("Unable to maximize browser window. Exception occured: " + ignore.getMessage());
            }
        }
    }

	public void sendKeys(By locator,String value) {
		oLog.info("Locator : " + locator + " Value : " + value);
		getElement(locator).sendKeys(value);
	}
	
	public void clear(By locator) {
		oLog.info("Locator : " + locator);
		getElement(locator).clear();
	}
	
	public String getText(By locator) {
		oLog.info("Locator : " + locator);
		return getElement(locator).getText();
	}
	
	public void clearAndSendKeys(By locator,String value) {
		WebElement element =  getElement(locator);
		element.clear();
		element.sendKeys(value);
		oLog.info("Locator : " + locator + " Value : " + value);
	}
	
	public void navigateTo(String url) {
		oLog.info(url);
		driver.get(url);
	}

	public void naviagteTo(URL url) {
		oLog.info(url.getPath());
		driver.get(url.getPath());
	}

	public String getTitle() {
		String title = driver.getTitle();
		oLog.info(title);
		return driver.getTitle();
	}

	public String getCurrentUrl() {
		String url = driver.getCurrentUrl();
		oLog.info(url);
		return driver.getCurrentUrl();
	}
	
	public Object executeScript(String script) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		oLog.info(script);
		return exe.executeScript(script);
	}

	public Object executeScript(String script, Object... args) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		oLog.info(script);
		return exe.executeScript(script, args);
	}

	public void scrollToElemet(WebElement element) {
		executeScript("window.scrollTo(arguments[0],arguments[1])",
				element.getLocation().x, element.getLocation().y);
		oLog.info(element);
	}

	public void scrollToElemet(By locator) {
		scrollToElemet(driver.findElement(locator));
		oLog.info(locator);
	}

	public void scrollToElemetAndClick(By locator) {
		WebElement element = driver.findElement(locator);
		scrollToElemet(element);
		element.click();
		oLog.info(locator);
	}

	public void scrollToElemetAndClick(WebElement element) {
		scrollToElemet(element);
		element.click();
		oLog.info(element);
	}

	public void scrollIntoView(WebElement element) {
		executeScript("arguments[0].scrollIntoView()", element);
		oLog.info(element);
	}

	public void scrollIntoView(By locator) {
		scrollIntoView(driver.findElement(locator));
		oLog.info(locator);
	}

	public void scrollIntoViewAndClick(By locator) {
		WebElement element = driver.findElement(locator);
		scrollIntoView(element);
		element.click();
		oLog.info(locator);
	}

	public void scrollIntoViewAndClick(WebElement element) {
		scrollIntoView(element);
		element.click();
		oLog.info(element);
	}
	
	public void clickLink(String linkText) {
		oLog.info(linkText);
		getElement(By.linkText(linkText)).click();
	}
	
	public void clickPartialLink(String partialLinkText) {
		oLog.info(partialLinkText);
		getElement(By.partialLinkText(partialLinkText)).click();
	}
	
	public String getHyperLink(By locator){
		oLog.info(locator);
		return getHyperLink(getElement(locator));
	}
	
	public String getHyperLink(WebElement element) {
		String link = element.getAttribute("hreg");
		oLog.info("Element : " + element + " Value : " + link);
		return link;
	}
	
	protected String getHeaderXpath(String tableIdoRxPath){
		oLog.debug(tableIdoRxPath);
		 return IsElementPresentQuick(By.id(tableIdoRxPath)) ? "//table[@id='" + tableIdoRxPath + "']//thead" : tableIdoRxPath + "//thead";
	}
	
	protected String getTableBodyXpath(String tableIdoRxPath){
		oLog.debug(tableIdoRxPath);
		 return IsElementPresentQuick(By.id(tableIdoRxPath)) ? "//table[@id='" + tableIdoRxPath + "']//tbody" : tableIdoRxPath + "//tbody";
	}
	
	protected WebElement getGridElement(String tableIdoRxPath,int row,int column){
		
		WebElement element;
		
		if ((element = getElementWithNull(By.xpath(getTableBodyXpath(tableIdoRxPath) + "//tr[" + row + "]//td[" + column + "]//input"))) != null){
			oLog.debug(element);
			return element;
		}
		else if ((element = getElementWithNull(By.xpath(getTableBodyXpath(tableIdoRxPath) + "//tr[" + row + "]//td[" + column + "]/a"))) != null){
			oLog.debug(element);
			return element;
		}
		else if ((element = getElementWithNull(By.xpath(getTableBodyXpath(tableIdoRxPath) + "//tr[" + row + "]//td[" + column + "]/button"))) != null){
			oLog.debug(element);
			return element;
		}
		else if((element = getElementWithNull(By.xpath(getTableBodyXpath(tableIdoRxPath) + "//tr[" + row + "]//td[" + column + "]"))) != null){
			oLog.debug(element);
			return element;
		}
		return null;
	}
	
	protected int searchInGrid(String description,String tableIdoRxPath,int row,final int column){
		
		WebElement element;
		
		while((element = getElementWithNull(By.xpath(getTableBodyXpath(tableIdoRxPath) + "//tr[" + row + "]//td[" + column + "]"))) != null){
			
			if(!element.getText().isEmpty()){
				oLog.info(element.getText());
				if(element.getText().trim().contains(description))
					return row;
			}
			row++;
		}
		
		throw new IllegalArgumentException("No matching description found : " + description);
	}
	
	public List<String> getGridHeading(String tableIdoRxPath){
		List<String> header = new LinkedList<String>();
		
		int j = 1;
		WebElement element;
		
		while((element = (getElementWithNull(By.xpath(getHeaderXpath(tableIdoRxPath) + "//tr[1]//th[" + j + "]")))) != null){
			if(!element.getText().isEmpty()){
				header.add(element.getText().trim());
				oLog.info(element.getText().trim());
			}
			j++;
		}
		return header;
	}
	
	public String getGridColumnText(String tableIdoRxPath,int row,int column) {
		oLog.info(tableIdoRxPath + "," + row + "," + column);
		WebElement element =  getGridElement(tableIdoRxPath,row,column);
		return element == null ? "" : element.getText().trim();
	}
	
	public void typeInGrid(String tableIdoRxPath,int row,int column,String value) {
		oLog.info(tableIdoRxPath + "," + row + "," + column + "," + value);
		WebElement element =  getGridElement(tableIdoRxPath,row,column);
		element.clear();
		element.sendKeys(value);
	}
	
	public void typeInGrid(String description,String tableIdoRxPath,int row,int column,String value) {
		oLog.info(tableIdoRxPath + "," + row + "," + column + "," + value + "," + description);
		int index =  searchInGrid(description,tableIdoRxPath,row,column);
		typeInGrid(tableIdoRxPath,index,3,value);
	}
	
	public WebElement getElement(By locator) {
		oLog.info(locator);
		if (IsElementPresentQuick(locator))
			return driver.findElement(locator);
		
		try {
			throw new NoSuchElementException("Element Not Found : " + locator);
		} catch (RuntimeException re) {
			oLog.error(re);
			throw re;
		}
	}
	
	/**
	 * Check for element is present based on locator
	 * If the element is present return the web element otherwise null
	 * @param locator
	 * @return WebElement or null
	 */
	
	public WebElement getElementWithNull(By locator) {
		oLog.info(locator);
		try {
			return driver.findElement(locator);
		} catch (NoSuchElementException e) {
			// Ignore
		}
		return null;
	}

	public boolean IsElementPresentQuick(By locator) {
		boolean flag = driver.findElements(locator).size() >= 1;
		oLog.info(flag);
		return flag;
	}

	public String takeScreenShot(String name) throws IOException {

		if (driver instanceof HtmlUnitDriver) {
			oLog.fatal("HtmlUnitDriver Cannot take the ScreenShot");
			return "";
		}

		File destDir = new File(ResourceHelper.getResourcePath("screenshots/")
				+ DateTimeHelper.getCurrentDate());
		if (!destDir.exists())
			destDir.mkdir();

		File destPath = new File(destDir.getAbsolutePath()
				+ System.getProperty("file.separator") + name + ".jpg");
		try {
			FileUtils
					.copyFile(((TakesScreenshot) driver)
							.getScreenshotAs(OutputType.FILE), destPath);
		} catch (IOException e) {
			oLog.error(e);
			throw e;
		}
		oLog.info(destPath.getAbsolutePath());
		return destPath.getAbsolutePath();
	}

	public String takeScreenShot() {
		oLog.info("");
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}
	
	public void SelectUsingVisibleValue(By locator,String visibleValue) {
		SelectUsingVisibleValue(getElement(locator),visibleValue);
	}
	
	public void SelectUsingVisibleValue(WebElement element,String visibleValue) {
		Select select = new Select(element);
		select.selectByVisibleText(visibleValue);
		oLog.info("Locator : " + element + " Value : " + visibleValue);
	}
	
	public void SelectUsingValue(By locator,String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
		oLog.info("Locator : " + locator + " Value : " + value);
	}
	
	public void SelectUsingIndex(By locator,int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
		oLog.info("Locator : " + locator + " Index : " + index);
	}
	
	public String getSelectedValue(By locator) {
		oLog.info(locator);
		return getSelectedValue(getElement(locator));
	}
	
	public String getSelectedValue(WebElement element) {
		String value = new Select(element).getFirstSelectedOption().getText();
		oLog.info("WebELement : " + element + " Value : "+ value);
		return value;
	}
	
	
	public List<String> getAllDropDownValues(By locator) {
		Select select = new Select(getElement(locator));
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<String>();
		
		for (WebElement element : elementList) {
			oLog.info(element.getText());
			valueList.add(element.getText());
		}
		return valueList;
	}
	public void selectCheckBox(By locator) {
		oLog.info(locator);
		selectCheckBox(driver.findElement(locator));
	}
	
	public void unSelectCheckBox(By locator) {
		oLog.info(locator);
		unSelectCheckBox(driver.findElement(locator));
	}
	
	public boolean isIselected(By locator) {
		oLog.info(locator);
		return isIselected(driver.findElement(locator));
	}
	
	public boolean isIselected(WebElement element) {
		boolean flag = element.isSelected();
		oLog.info(flag);
		return flag;
	}
	
	public void selectCheckBox(WebElement element) {
		if(!isIselected(element))
			element.click();
		oLog.info(element);
	}
	
	public void unSelectCheckBox(WebElement element) {
		if(isIselected(element))
			element.click();
		oLog.info(element);
	}
	
	public ButtonHelper(WebDriver driver) {
		this.driver = driver;
		oLog.debug("Button Helper : " + this.driver.hashCode());
	}
	
	public void click(By locator) {
		click(driver.findElement(locator));
		oLog.info(locator);
	}
	
	public void click(WebElement element){
		element.click();
		oLog.info(element);
	}
	
	public void goBack() {
		driver.navigate().back();
		oLog.info("");
	}

	public void goForward() {
		driver.navigate().forward();
		oLog.info("");
	}

	public void refresh() {
		driver.navigate().refresh();
		oLog.info("");
	}

	public Set<String> getWindowHandlens() {
		oLog.info("");
		return driver.getWindowHandles();
	}

	public void SwitchToWindow(int index) {

		LinkedList<String> windowsId = new LinkedList<String>(
				getWindowHandlens());

		if (index < 0 || index > windowsId.size())
			throw new IllegalArgumentException("Invalid Index : " + index);

		driver.switchTo().window(windowsId.get(index));
		oLog.info(index);
	}

	public void switchToParentWindow() {
		LinkedList<String> windowsId = new LinkedList<String>(
				getWindowHandlens());
		driver.switchTo().window(windowsId.get(0));
		oLog.info("");
	}

	public void switchToParentWithChildClose() {
		switchToParentWindow();

		LinkedList<String> windowsId = new LinkedList<String>(
				getWindowHandlens());

		for (int i = 1; i < windowsId.size(); i++) {
			oLog.info(windowsId.get(i));
			driver.switchTo().window(windowsId.get(i));
			driver.close();
		}

		switchToParentWindow();
	}
	
	public void switchToFrame(By locator) {
		driver.switchTo().frame(getElement(locator));
		oLog.info(locator);
	}
	
	public void switchToFrame(String nameOrId) {
		driver.switchTo().frame(nameOrId);
		oLog.info(nameOrId);
	}
	
	public Alert getAlert() {
		oLog.debug("");
		return driver.switchTo().alert();
	}
	
	public void AcceptAlert() {
		oLog.info("");
		getAlert().accept();
	}
	
	public void DismissAlert() {
		oLog.info("");
		getAlert().dismiss();
	}

	public String getAlertText() {
		String text = getAlert().getText();
		oLog.info(text);
		return text;
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			oLog.info("true");
			return true;
		} catch (NoAlertPresentException e) {
			// Ignore
			oLog.info("false");
			return false;
		}
	}

	public void AcceptAlertIfPresent() {
		if (!isAlertPresent())
			return;
		AcceptAlert();
		oLog.info("");
	}

	public void DismissAlertIfPresent() {

		if (!isAlertPresent())
			return;
		DismissAlert();
		oLog.info("");
	}
	
	public void AcceptPrompt(String text) {
		
		if (!isAlertPresent())
			return;
		
		Alert alert = getAlert();
		alert.sendKeys(text);
		alert.accept();
		oLog.info(text);
	}
	
	public Alert getAlert() {
		oLog.debug("");
		return driver.switchTo().alert();
	}
	
	public void AcceptAlert() {
		oLog.info("");
		getAlert().accept();
	}
	
	public void DismissAlert() {
		oLog.info("");
		getAlert().dismiss();
	}

	public String getAlertText() {
		String text = getAlert().getText();
		oLog.info(text);
		return text;
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			oLog.info("true");
			return true;
		} catch (NoAlertPresentException e) {
			// Ignore
			oLog.info("false");
			return false;
		}
	}

	public void AcceptAlertIfPresent() {
		if (!isAlertPresent())
			return;
		AcceptAlert();
		oLog.info("");
	}

	public void DismissAlertIfPresent() {

		if (!isAlertPresent())
			return;
		DismissAlert();
		oLog.info("");
	}
	
	public void AcceptPrompt(String text) {
		
		if (!isAlertPresent())
			return;
		
		Alert alert = getAlert();
		alert.sendKeys(text);
		alert.accept();
		oLog.info(text);
	}
	
	public void goBack() {
		driver.navigate().back();
		Log.info("");
	}

	public void goForward() {
		driver.navigate().forward();
		Log.info("");
	}

	public void refresh() {
		driver.navigate().refresh();
		Log.info("");
	}

	public Set<String> getWindowHandlens() {
		Log.info("");
		return driver.getWindowHandles();
	}

	public void SwitchToWindow(int index) {

		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandlens());

		if (index < 0 || index > windowsId.size()){
			throw new IllegalArgumentException("Invalid Index : " + index);
		}
		driver.switchTo().window(windowsId.get(index));
		Log.info(index);
	}

	public void switchToParentWindow() {
		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandlens());
		driver.switchTo().window(windowsId.get(0));
		Log.info("");
	}

	public void switchToParentWithChildClose() {
		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandlens());

		for (int i = 1; i < windowsId.size(); i++) {
			Log.info(windowsId.get(i));
			driver.switchTo().window(windowsId.get(i));
			driver.close();
		}

		switchToParentWindow();
	}
	

	
	public void switchToFrame(String nameOrId) {
		driver.switchTo().frame(nameOrId);
		Log.info(nameOrId);
	}
	
	public void SelectUsingVisibleValue(WebElement element,String visibleValue) {
		Select select = new Select(element);
		select.selectByVisibleText(visibleValue);
		Log.info("Locator : " + element + " Value : " + visibleValue);
	}

	public String getSelectedValue(WebElement element) {
		String value = new Select(element).getFirstSelectedOption().getText();
		Log.info("WebELement : " + element + " Value : "+ value);
		return value;
	}
	
	public void SelectUsingIndex(WebElement element,int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
		Log.info("Locator : " + element + " Value : " + index);
	}
	
	public void SelectUsingVisibleText(WebElement element,String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
		Log.info("Locator : " + element + " Value : " + text);
	}
	
	
	public List<String> getAllDropDownValues(WebElement locator) {
		Select select = new Select(locator);
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<String>();
		
		for (WebElement element : elementList) {
			Log.info(element.getText());
			valueList.add(element.getText());
		}
		return valueList;
	}
	
	public Object executeScript(String script) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		Log.info(script);
		return exe.executeScript(script);
	}

	public Object executeScript(String script, Object... args) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		Log.info(script);
		return exe.executeScript(script, args);
	}

	public void scrollToElemet(WebElement element) {
		executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x, element.getLocation().y);
		Log.info(element);
	}

	public void scrollToElemetAndClick(WebElement element) {
		scrollToElemet(element);
		element.click();
		Log.info(element);
	}

	public void scrollIntoView(WebElement element) {
		executeScript("arguments[0].scrollIntoView()", element);
		Log.info(element);
	}

	public void scrollIntoViewAndClick(WebElement element) {
		scrollIntoView(element);
		element.click();
		Log.info(element);
	}

	public void scrollDownVertically() {
		executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollUpVertically() {
		executeScript("window.scrollTo(0, -document.body.scrollHeight)");
	}

	public void scrollDownByPixel() {
		executeScript("window.scrollBy(0,1500)");
	}

	public void scrollUpByPixel() {
		executeScript("window.scrollBy(0,-1500)");
	}

	public void ZoomInBypercentage() {
		executeScript("document.body.style.zoom='40%'");
	}

	public void ZoomBy100percentage() {
		executeScript("document.body.style.zoom='100%'");
	}

}
