package Test;

import Utils.VideoRecorderUtil;
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

        // AVI → MP4 conversion must already be hooked here
        // VideoConverterUtil.convertAviToMp4();

        ExtentReportManager.flushReport();
    }

    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setupClass(@Optional("chrome") String browser) throws MalformedURLException {

        browserName.set(browser);
        URL gridUrl = new URL("http://localhost:4444/");

        WebDriver remoteDriver;
        switch (browser.toLowerCase()) {
            case "firefox":
                remoteDriver = new RemoteWebDriver(gridUrl, new FirefoxOptions());
                break;
            case "edge":
                remoteDriver = new RemoteWebDriver(gridUrl, new EdgeOptions());
                break;
            default:
                remoteDriver = new RemoteWebDriver(gridUrl, new ChromeOptions());
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

    // ✅ SINGLE BeforeMethod
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method) throws Exception {

        String testName = method.getName() + "_" + getBrowserName();

        ExtentReportManager.createTest(testName);
        ExtentReportManager.getTest()
                .info("Starting test: " + testName);

        VideoRecorderUtil.startRecording(testName);
    }

    // ✅ SINGLE AfterMethod
    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) throws Exception {

        String methodName = result.getMethod().getMethodName();

        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                ExtentReportManager.getTest()
                        .log(Status.PASS, "Test Passed: " + methodName);
                break;

            case ITestResult.FAILURE:
                ExtentReportManager.getTest()
                        .log(Status.FAIL, "Test Failed: " + methodName);
                ExtentReportManager.getTest()
                        .log(Status.FAIL, result.getThrowable());
                break;

            case ITestResult.SKIP:
                ExtentReportManager.getTest()
                        .log(Status.SKIP, "Test Skipped: " + methodName);
                break;
        }

        // stop recording
        VideoRecorderUtil.stopRecording();

        // embed MP4 directly in report (browser-playable)
        String mp4Path = VideoRecorderUtil.getMp4Path();

        ExtentReportManager.getTest().info(
                "<b>🎥 Test Execution Video</b><br>" +
                        "<video width='720' height='420' controls>" +
                        "<source src='" + mp4Path + "' type='video/mp4'>" +
                        "Your browser does not support the video tag." +
                        "</video>"
        );

        ExtentReportManager.unload();
        VideoRecorderUtil.clear();
    }
}
