package com.google.gmodule.googlesearch;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * BrowserFactory to create required brwoser session to be used in the test
 * cases
 * 
 * @author Sunil kumar
 *
 */
public class BrowserFactory {
	private DesiredCapabilities capFirefox;
	private ThreadLocal<RemoteWebDriver> remoteDriver = new ThreadLocal<>();
	private ThreadLocal<WebDriver> localDriver = new ThreadLocal<>();
	protected GoogleSearchBasePage googleSearchBasePage;
	protected static final String CHROME = "chrome";
	protected static final String FIREFOX = "firefox";
	protected static final String WEB_DRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
	protected static final String FALSE = "false";
	protected static final String TRUE = "true";
	private DesiredCapabilities capChrome;

	/**
	 * Constructor to initialize browser factory elements
	 * 
	 * @param driver
	 */
	public BrowserFactory() {
		googleSearchBasePage = new GoogleSearchBasePage();
		capChrome = null;
	}

	/**
	 * Method to get local browser driver
	 * 
	 * @param browserType
	 * @param remote
	 * @param hubUrl
	 * @return
	 */
	public WebDriver getBrowserDriver(BrowserType browserType, String remote, String hubUrl) {
		if (remote.equalsIgnoreCase(TRUE)) {
			return getRemoteBrowserDriver(browserType, hubUrl);
		} else {
			return getLocalBrowserDriver(browserType, hubUrl);
		}
	}

	/**
	 * Method to get remote driver
	 * 
	 * @return
	 */
	public RemoteWebDriver getRemoteDriver() {
		return remoteDriver.get();
	}

	/**
	 * Method to set remote driver
	 * 
	 * @param remoteDriver
	 */
	public void setRemoteDriver(RemoteWebDriver remoteDriver) {
		this.remoteDriver.set(remoteDriver);
	}

	/**
	 * Method to get local driver
	 * 
	 * @return
	 */
	public WebDriver getLocalDriver() {
		return localDriver.get();
	}

	/**
	 * Method to set local driver
	 * 
	 * @param localDriver
	 */
	public void setLocalDriver(WebDriver localDriver) {
		this.localDriver.set(localDriver);
	}

	/**
	 * Method to get local browser driver
	 * 
	 * @param browserType
	 * @param remote
	 * @param hubUrl
	 * @return
	 */
	public WebDriver getRemoteBrowserDriver(BrowserType browserType, String hubUrl) {
		switch (browserType) {
		case FIREFOX:
			capFirefox = DesiredCapabilities.firefox();
			try {
				setRemoteDriver(new RemoteWebDriver(new URL(hubUrl), capFirefox));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			break;
		case CHROME:
			System.setProperty(WEB_DRIVER_CHROME_DRIVER,
					googleSearchBasePage.getBaseProjectPath() + "src\\test\\resources\\drivers\\chromedriver.exe");
			capChrome = DesiredCapabilities.chrome();
			try {
				setRemoteDriver(new RemoteWebDriver(new URL(hubUrl), capChrome));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			break;
		default:
			System.setProperty(WEB_DRIVER_CHROME_DRIVER,
					googleSearchBasePage.getBaseProjectPath() + "src\\test\\resources\\drivers\\chromedriver.exe");
			capChrome = DesiredCapabilities.chrome();
			try {
				setRemoteDriver(new RemoteWebDriver(new URL(hubUrl), capChrome));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			break;
		}
		return getRemoteDriver();
	}

	/**
	 * Method to get local browser driver
	 * 
	 * @param browserType
	 * @param remote
	 * @param hubUrl
	 * @return
	 */
	public WebDriver getLocalBrowserDriver(BrowserType browserType, String hubUrl) {
		switch (browserType) {
		case FIREFOX:
			setLocalDriver(new FirefoxDriver());
			break;
		case CHROME:
			setLocalDriver(new ChromeDriver());
			break;
		default:
			setLocalDriver(new ChromeDriver());
			break;
		}
		return getLocalDriver();
	}
}