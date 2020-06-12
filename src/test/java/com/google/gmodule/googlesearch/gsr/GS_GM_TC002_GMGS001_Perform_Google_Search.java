package com.google.gmodule.googlesearch.gsr;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.google.gmodule.googlesearch.TestListener;
import com.google.gmodule.googlesearch.reader.NotepadFileReader;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

/**
 * Test to verify google search result. The test data is from
 * GoogleSearchVeifyResult.xlsx
 * 
 * @author G Sunil kumar
 *
 */
@Listeners(TestListener.class)
public class GS_GM_TC002_GMGS001_Perform_Google_Search extends GoogleSearchResultBaseTest {

	private Object[][] hashMapObjArray;

	/**
	 * constructor to initialize google search verify result test
	 */
	public GS_GM_TC002_GMGS001_Perform_Google_Search() {
		super();
	}

	/**
	 * method to initialize pages required for the test
	 */
	@BeforeMethod(alwaysRun = true)
	public void pageSetup() {
		googleSearchPage = new GoogleSearchPage(getDriver());
		googleResultPage = new GoogleResultPage(getDriver());
	}

	@DataProvider(name = "perform_Google_Search_Data")
	public Object[][] perform_Google_Search_Data() {
		notepadReader =(NotepadFileReader) readerFactory.getReader(TXT__FILE);
		hashMapObjArray = notepadReader.createHashMapObj(this.getClass(), DATA_SET_1);
		return hashMapObjArray;
	}

	@Test(groups = { "beforeBatchRegression", "regression" },priority = 0, description = "Perform Google Search",dataProvider = "perform_Google_Search_Data")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Description - Perform Google Search")
	@Story("GS_GM_TC002_GMGS001")
	public void searchTextAndVerifyResultTest(HashMap<String, String> hashMapObj) {
		log("Test Case - " + getClass().getSimpleName()
				+ " with Thread Id:- " + Thread.currentThread().getId());
		log("googleSearchPage.getTitle() " + googleSearchPage.getTitle());
		Assert.assertTrue(googleSearchPage.getTitle().contains(GOOGLE_PAGE_TITLE), " Assertion status for googleSearchPage getTitle() - "+ " Actual page title - "+ googleSearchPage.getTitle() + " Expected page title -" + GOOGLE_PAGE_TITLE);
		googleSearchPage.enterGoogleSearchText(hashMapObj.get(GOOGLE_PAGE_SEARCH_TEXT));
		googleSearchPage.submitGoogleSearch(getScreenshotOnSuccess(),this.getClass().getSimpleName());

	}
}
