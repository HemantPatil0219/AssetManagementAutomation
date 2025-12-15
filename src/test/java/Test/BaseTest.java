package Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.Status;
import Reports.ExtentReportManager;
import Utils.SeleniumGridManager;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final ThreadLocal<String> browserName = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static String getBrowserName() {
        return browserName.get();
    }

    @BeforeSuite(alwaysRun = true)
    public void startSeleniumGrid() throws Exception {
        SeleniumGridManager.startGrid();
    }

    @AfterSuite(alwaysRun = true)
    public void stopSeleniumGrid() throws Exception {
        SeleniumGridManager.stopGrid();
        ExtentReportManager.flushReport();
    }

    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setupClass(@Optional("chrome") String browser) throws MalformedURLException {
        browserName.set(browser);

        WebDriver remoteDriver;
        URL gridUrl = new URL("http://localhost:4444/");

        switch (browser.toLowerCase()) {
            case "firefox":
                remoteDriver = new RemoteWebDriver(gridUrl, new FirefoxOptions());
                break;
            case "edge":
                remoteDriver = new RemoteWebDriver(gridUrl, new EdgeOptions());
                break;
            case "chrome":
            default:
                remoteDriver = new RemoteWebDriver(gridUrl, new ChromeOptions());
                break;
        }

        driver.set(remoteDriver);
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void startReport(Method method) {
        ExtentReportManager.createTest(method.getName());
        ExtentReportManager.getTest().info("Starting test: " + method.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void endReport(ITestResult result) {
        String methodName = result.getMethod().getMethodName();

        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                ExtentReportManager.getTest().log(Status.PASS, "Test Passed: " + methodName);
                break;
            case ITestResult.FAILURE:
                ExtentReportManager.getTest().log(Status.FAIL, "Test Failed: " + methodName);
                ExtentReportManager.getTest().log(Status.FAIL, result.getThrowable());
                break;
            case ITestResult.SKIP:
                ExtentReportManager.getTest().log(Status.SKIP, "Test Skipped: " + methodName);
                break;
        }

        ExtentReportManager.unload();
    }
}
