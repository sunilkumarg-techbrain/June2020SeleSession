package com.google.gmodule.googlesearch.gsr;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.google.gmodule.googlesearch.GoogleSearchBasePage;

public class GoogleSearchPage extends GoogleSearchBasePage {

	private final String GOOGLE_SEARCH_PAGE_PAGE_5_BUTTON = "//*[contains(@id,'nav')]/tbody/tr/td[6]/a";
	private final String GOOGLE_SEARCH_PAGE_PAGE_2_BUTTON = "//*[contains(@id,'nav')]/tbody/tr/td[3]/a";
	private final String GOOGLE_SEARCH_PAGE_GOOGLE_TEXT_BOX = "//*[@name='q']";
	private final String GOOGLE_SEARCH_PAGE_GOOGLE_SEARCH_BUTTON = "//*[@id='tsf']/div[2]/div[3]/center/input[1]";
	private final String GOOGLE_SEARCH_PAGE_AREA_OUTSIDE_SEARCH_BOX = "//*[@id='body']/center";
	private final String GOOGLE_SEARCH_PAGE_RESULT_STATS = "//*[@id='rso']//h3/a";

	@FindBy(xpath = GOOGLE_SEARCH_PAGE_PAGE_5_BUTTON)
	WebElement googleSearchPagePage5Button;
	@FindBy(xpath = GOOGLE_SEARCH_PAGE_PAGE_2_BUTTON)
	WebElement googleSearchPagePage2Button;
	@FindBy(xpath = GOOGLE_SEARCH_PAGE_GOOGLE_TEXT_BOX)
	WebElement googleTextBox;
	@FindBy(xpath = GOOGLE_SEARCH_PAGE_GOOGLE_SEARCH_BUTTON)
	WebElement googleSearchButton;
	@FindBy(xpath = GOOGLE_SEARCH_PAGE_AREA_OUTSIDE_SEARCH_BOX)
	WebElement areaOutsideSearchBox;
	@FindBy(xpath = GOOGLE_SEARCH_PAGE_RESULT_STATS)
	WebElement resultStats;
	@FindBy(xpath = GOOGLE_SEARCH_PAGE_GOOGLE_TEXT_BOX)
	List<WebElement> googleTextBoxList;

	public GoogleSearchPage(WebDriver driver) {
		super();
		setWebDriver(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * method to click page 5 button
	 * 
	 * @param driver
	 * @return
	 */
	public GoogleSearchPage clickPage5Button() {
		if (isElementPresent(googleSearchPagePage5Button)) {
			googleTextBox.click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		return this;
	}

	/**
	 * method to click page 2 button
	 * 
	 * @param driver
	 * @return
	 */
	public GoogleSearchPage clickPage2Button() {
		if (isElementPresent(googleSearchPagePage2Button)) {
			googleTextBox.click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		return this;
	}

	/**
	 * Method to enter google search text
	 * 
	 * @param driver
	 * @param searchText
	 * @return
	 */
	public GoogleSearchPage enterGoogleSearchText(String searchText) {
		System.out.println("googleTextBoxList.size() " + googleTextBoxList.size());
		if (isElementPresent(googleTextBox)) {
			googleTextBox.clear();
			googleTextBox.sendKeys(searchText + "\n");
		}
		return this;
	}

	/**
	 * method to submit google search
	 * 
	 * @param driver
	 * @return
	 */
	public GoogleSearchPage submitGoogleSearch(String screenshotOnSuccess, String methodName) {
		if (isElementPresent(googleTextBox)) {
			captureScreenshotOfEveryScreen(screenshotOnSuccess,methodName);
			googleTextBox.submit();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			captureScreenshotOfEveryScreen(screenshotOnSuccess,methodName);
		}
		return this;
	}

	public String getTitle() {
		return getWebDriver().getTitle();
	}

}
