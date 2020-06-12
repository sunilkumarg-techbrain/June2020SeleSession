package com.google.gmodule.googlesearch.gsr;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.google.gmodule.googlesearch.GoogleSearchBasePage;

public class GoogleResultPage extends GoogleSearchBasePage {

	private final String GOOGLE_RESULT_PAGE_RESULT_STATS = "//*[@id='rso']//a[contains(@href,'https')]";

	@FindBy(xpath = GOOGLE_RESULT_PAGE_RESULT_STATS)
	WebElement resultStats;
	@FindBy(xpath = GOOGLE_RESULT_PAGE_RESULT_STATS)
	List<WebElement> resultStatsList;

	/**
	 * constructor to initialize google results page
	 * 
	 * @param driver
	 */
	public GoogleResultPage(WebDriver driver) {
		setWebDriver(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * method to get google search results list
	 * 
	 * @return List<WebElement>
	 */
	public List<WebElement> getGoogleSearchResultsList() {
		if (isElementPresent(resultStats)) {
			return resultStatsList;
		} else {
			Assert.fail("Element not present");
			return null;
		}

	}
}
