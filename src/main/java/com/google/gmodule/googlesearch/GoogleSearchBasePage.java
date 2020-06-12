package com.google.gmodule.googlesearch;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.google.gmodule.googlesearch.utils.GoogleUtils;

public class GoogleSearchBasePage {
	protected Date d = new Date();
	protected static final String ON = "on";
	protected SimpleDateFormat sdf;
	public static int counter = 0;
	protected WebDriver driver;
	protected GoogleUtils googleUtils;
	protected GoogleSearchBasePage googleSearchPage;
	private boolean status;
	private int implicitTimeOutInSeconds;
	private int timeOutInSeconds;
	private WebDriverWait wait;
	private String baseProjectPath;

	/**
	 * Constructor to initialize base page
	 */
	public GoogleSearchBasePage() {
		super();
		googleUtils = new GoogleUtils();
		implicitTimeOutInSeconds = 1;
		timeOutInSeconds = 15;
	}
	/**
	 * Method to get screen shot folder path
	 * 
	 * @return String
	 */
	public String getScreenshotFolderPath(String methodName) {
		String takeScreenshotPath = "";
		try {
			takeScreenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + methodName + "\\";
		} catch (NullPointerException | IllegalArgumentException e) {
			Reporter.log("Unable to get screenshot folderpath" + "----" + e);
		}
		return takeScreenshotPath;
	}
	/**
	 * Method to capture screenshot of the screens of test case
	 * 
	 */
	public void captureScreenshotOfEveryScreen(String screenshotOnSuccess, String methodName) {
		sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");
		try {
			if (screenshotOnSuccess.equalsIgnoreCase(ON)) {
				File scrFile = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(
						getScreenshotFolderPath(methodName) + "SCREENSHOT_" + counter + "_" + sdf.format(d) + ".png"));				
				counter++;
			}
		} catch (IOException e) { 
			Reporter.log("Unable to copy screenshot to the location - " + getScreenshotFolderPath(methodName));
		}
	}

	/**
	 * method to set driver
	 * 
	 */
	public void setWebDriver(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * method to get driver
	 * 
	 * @return WebDriver
	 */
	public WebDriver getWebDriver() {
		return this.driver;
	}

	/**
	 * method to highlight the element
	 * 
	 * @param driver
	 * @param element
	 */
	public void highlight(WebElement element) {
		Actions actions = new Actions(getWebDriver());
		actions.moveToElement(element);
		actions.perform();
		JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid blue;');", element);
	}

	/**
	 * method to get base project path
	 * 
	 * @return String
	 */
	public String getBaseProjectPath() {
		baseProjectPath = System.getProperty("user.dir") + "\\";
		return baseProjectPath;
	}

	/**
	 * method to take screenshot
	 * 
	 * @param driver
	 */
	public void takeScreenshot() {
		File scrFile = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(googleSearchPage.getBaseProjectPath()
					+ "src\\test\\resources\\screenshots\\screeenshot_" + googleUtils.getCurrentTimestamp() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * method verified the element present conditions, waits for element to load
	 * and returns the boolean
	 * 
	 * @param elem
	 * @return boolean
	 */
	public boolean isElementPresent(WebElement elem) {
		status = false;
		try {
			highlight(elem);
			wait = new WebDriverWait(getWebDriver(), timeOutInSeconds);
			wait.until(ExpectedConditions.visibilityOf(elem));
			wait.until(ExpectedConditions.elementToBeClickable(elem));
			getWebDriver().manage().timeouts().implicitlyWait(implicitTimeOutInSeconds, TimeUnit.SECONDS);
			status = true;
		} catch (Exception e) {
			Assert.fail("Element NOT found");
		}
		return status;
	}

}
