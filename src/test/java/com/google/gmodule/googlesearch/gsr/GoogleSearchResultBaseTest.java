package com.google.gmodule.googlesearch.gsr;

import com.google.gmodule.googlesearch.GoogleSearchBaseTest;

/**
 * BaseTest class to store method including testSetup, driver declarations etc.
 * 
 * @author G Sunil kumar
 *
 */
public class GoogleSearchResultBaseTest extends GoogleSearchBaseTest {

	protected static final String DATA_SET_1 = "dataset1";
	protected static final String HTTPS = "https://";
	protected static final String HTTP = "http://";
	protected static final String HREF = "href";
	protected static final String GOOGLE_PAGE_SEARCH_TEXT = "searchText";
	protected static final String GOOGLE_PAGE_TITLE = "Google";
	GoogleSearchPage googleSearchPage;
	GoogleResultPage googleResultPage;

	/**
	 * constructor to initialize base test page
	 */
	public GoogleSearchResultBaseTest() {
		super();
	}

}
