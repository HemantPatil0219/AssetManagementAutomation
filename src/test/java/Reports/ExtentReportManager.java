package Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public synchronized static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    private static void createInstance() {
        ExtentSparkReporter sparkReporter =
                new ExtentSparkReporter("test-output/ExtentReport.html");

        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("Test Execution Results");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Tester", "Hemant Patil");
        extent.setSystemInfo("Environment", "QA");
    }

    public static void createTest(String testName) {
        ExtentTest test = getInstance().createTest(testName);
        extentTest.set(test);
    }

    public static ExtentTest getTest() {
        if (extentTest.get() == null) {
            createTest("Unknown Test - " + Thread.currentThread().getId());
        }
        return extentTest.get();
    }

    public static void unload() {
        extentTest.remove();
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
