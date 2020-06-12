package com.google.gmodule.googlesearch;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.google.gmodule.googlesearch.reader.ExcelFileReader;
import com.google.gmodule.googlesearch.reader.NotepadFileReader;
import com.google.gmodule.googlesearch.reader.ReaderFactory;
import com.google.gmodule.googlesearch.utils.GoogleUtils;

import io.qameta.allure.Allure;

/**
 * BaseTest class to store method including testSetup, driver declarations etc.
 * 
 * @author Sunil kumar G
 *
 */
public class GoogleSearchBaseTest {

	final static Logger logger = Logger.getLogger(GoogleSearchBaseTest.class);
	protected String screenshotOnSuccess;
	protected String methodName;  
	protected static final String WEB_DRIVER = "WebDriver";
	protected static final String XLSX__FILE = "xlsx";
	protected static final String TXT__FILE = "txt";

	protected BrowserFactory browserFactory;
	protected ReaderFactory readerFactory;
	protected NotepadFileReader notepadReader;
	protected ExcelFileReader excelFileReader;
	private String url = "https://www.google.com";
	private WebDriver driver;
	protected GoogleSearchBasePage googleSearchBasePage;
	protected GoogleUtils googleUtils;

	/**
	 * Constructor to initialize base test page
	 */
	public GoogleSearchBaseTest() {
		super();
		browserFactory = new BrowserFactory();
		readerFactory = new ReaderFactory();
		googleUtils = new GoogleUtils();
		googleSearchBasePage = new GoogleSearchBasePage();
	}
	/**
	 * Method to setup the driver
	 */
	@BeforeMethod(alwaysRun = true)
	@Parameters({ "remote", "browser", "hubUrl" , "screenshotOnSuccess" })
	public void testSetUp(ITestContext testContext,Method method, String remote, String browser, String hubUrl, String screenshotOnSuccess) {
		System.setProperty("webdriver.chrome.driver",
				googleSearchBasePage.getBaseProjectPath() + "src\\test\\resources\\drivers\\chromedriver.exe");
		this.driver = browserFactory.getBrowserDriver(BrowserType.valueOf(browser), remote, hubUrl);
		googleSearchBasePage.setWebDriver(this.driver);
		setScreenshotOnSuccess(screenshotOnSuccess);
		doSetup(method);
		testContext.setAttribute(WEB_DRIVER, this.driver);
		launchUrl();
		getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	/**
	 * Method to get screenshotOnSuccess
	 * 
	 * @param screenshotOnSuccess
	 */
	public String getScreenshotOnSuccess() {
		return this.screenshotOnSuccess;
	}

	/**
	 * Method to set screenshotOnSuccess
	 * 
	 * @param screenshotOnSuccess
	 */
	public void setScreenshotOnSuccess(String screenshotOnSuccess) {
		this.screenshotOnSuccess = screenshotOnSuccess;
	}
	/**
	 * Method to get screen shot folder location
	 * 
	 * @return String
	 */
	public String getScreenshotFolderLocation() {
		String takeScreenshotPath = "";
		try {
			takeScreenshotPath = System.getProperty("user.dir") + "\\..\\..\\Screenshots\\";
			log("getScreenshotFolderLocation() - "+ takeScreenshotPath );
		} catch (NullPointerException | IllegalArgumentException e) {
			log("Unable to get screenshot folderpath" + "----" + e);
		}
		return takeScreenshotPath;
	}
	/**
	 * Method to setup before suite items
	 * 
	 * @param platform
	 * @param method
	 * @param url
	 * @param host
	 * @param region
	 * @param screenshotOnSuccess
	 */
	@BeforeSuite(alwaysRun = true, groups = {})
	public void beforeSuiteSetup() {		
		log("Deleting previously exisitng screenshot directories ----");
		deleteDirectory(new File(getScreenshotFolderLocation()));
	}
	/**
	 * Method to delete directory files and sub directories recursively
	 * 
	 * @param directoryToBeDeleted
	 * @return
	 */
	boolean deleteDirectory(File directoryToBeDeleted) {
		File[] allContents = directoryToBeDeleted.listFiles();
		if (allContents != null) {
			for (File file : allContents) {
				deleteDirectory(file);
			}
		}
		return directoryToBeDeleted.delete();
	}
	/**
	 * Method to set method name and verbose level
	 * 
	 * @param method
	 */
	protected void doSetup(Method method) {
		setMethodName(method.getName());
	}
	/**
	 * Getter method to get method name
	 * 
	 * @return
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * Method to set method name
	 * 
	 * @param methodName
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * Method to log to console with testng Reporter and logs to Allure with
	 * addAttachment
	 * 
	 * @param msg
	 */
	public void log(String msg) {
		String timeNow = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).toString();
		Allure.addAttachment(timeNow + " - " + msg, "");
		if (getMethodName() == null) {
			Reporter.log(timeNow + " - " + msg, true);
			logger.info(timeNow + " - " + msg);
		} else {
			logger.info(timeNow + " - <" + getMethodName() + "> " + msg);
			Reporter.log(timeNow + " - <" + getMethodName() + "> " + msg, true);
		}
	}

	/**
	 * Method to capture screenshot by passing the driver and method name as
	 * parameters
	 * 
	 * @param driver
	 * @param methodName
	 */
	public void captureScreenshot(WebDriver driver, String methodName) {
		System.out.println("Capturing screenshot of failure .....");
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		InputStream is = null;
		try {
			is = new FileInputStream(screenshot);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Allure.addAttachment("SCREENSHOT-" + methodName, is);
		System.out.println("Attached screenshot of failure to allure report .....");

	}

	

	/**
	 * Method to quit the browser session
	 */
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

	/**
	 * Method to get driver
	 */
	public WebDriver getDriver() {
		return this.driver;
	}

	/**
	 * Method to launch url
	 * 
	 * @param driver
	 * @param url
	 */
	public void launchUrl() {
		try {
			getDriver().navigate().to(url);
			getDriver().manage().window().maximize();
		} catch (Exception e) {
			googleSearchBasePage.takeScreenshot();
		}
	}

}
