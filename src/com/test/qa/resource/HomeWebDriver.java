package com.test.qa.resource;

import org.openqa.selenium.WebDriver;

public class HomeWebDriver {
	
	private WebDriver webDriver;
	private String browser;

	public WebDriver getWebDriver() {
		return webDriver;
	}

	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}
	
}
