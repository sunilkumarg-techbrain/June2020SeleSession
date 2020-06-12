package com.google.gmodule.googlesearch;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/** 
 * Listener class to set the retry count for the failed scenarios
 * @author Sunil kumar
 *
 */
public class RetryAnalyzer implements IRetryAnalyzer {

	private int counter = 0;
	private int retryLimit = 2;

	/**
	 * Constructor for RetryAnalyzer
	 */
	public RetryAnalyzer() {
		super();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.testng.IRetryAnalyzer#retry(org.testng.ITestResult)
	 * 
	 *      This method decides how many times a test needs to be rerun. TestNg
	 *      will call this method every time a test fails. So we can put some
	 *      code in here to decide when to rerun the test.
	 * 
	 *      Note: This method will return true if a tests needs to be retried
	 *      and false it not.
	 */
	@Override
	public boolean retry(ITestResult result) {

		if (counter < retryLimit) {
			System.out.println("Retrying " + result.getName() + " again and the count is " + counter);
			counter++;
			return true;
		}
		return false;
	}

}
