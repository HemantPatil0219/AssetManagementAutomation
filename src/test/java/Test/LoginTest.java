package Test;

import Events.LoginPageEvents;
import Reports.ExtentReportManager;
import Utils.ConfigReader;
import Utils.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class LoginTest extends BaseTest {

    LoginPageEvents login;

    @BeforeMethod
    public void setup() {
        login = new LoginPageEvents(getDriver());
        login.loadHomePage(ConfigReader.get("BASEURLDEV"));
    }

    // ✅ Correct DataProvider
    @DataProvider(name = "loginData")
    public Object[][] loginDataProvider() {
        String excelPath =
                "C:\\Users\\Lenovo\\IdeaProjects\\AssetManagement\\src\\test\\resources\\TestData\\Login.xlsx";

        return ExcelUtils.getTestData(excelPath, "Login");
    }

    @Test(dataProvider = "loginData")
    public void loginTests(Map<String, String> data) {

        String testCase = data.get("TestCase");
        ExtentReportManager.createTest(testCase);
        ExtentReportManager.getTest().info("Executing test case: " + testCase);

        // Fill login form
        login.enterEmail(data.get("Email"));
        login.enterPassword(data.get("Password"));
        login.clickSignIn();

        String expectedResult = data.get("ExpectedResult");

        switch (expectedResult) {

            case "EMAIL_REQUIRED":
                Assert.assertTrue(
                        login.getEmailErrorMessage().toLowerCase().contains("email"),
                        "Email required validation not shown"
                );
                ExtentReportManager.getTest().pass("Email required validation displayed");
                break;

            case "INVALID_EMAIL":
                Assert.assertTrue(
                        login.getEmailErrorMessage().toLowerCase().contains("invalid"),
                        "Invalid email validation not shown"
                );
                ExtentReportManager.getTest().pass("Invalid email validation displayed");
                break;

            case "PASSWORD_REQUIRED":
                Assert.assertTrue(
                        login.getPasswordErrorMessage().toLowerCase().contains("password"),
                        "Password required validation not shown"
                );
                ExtentReportManager.getTest().pass("Password required validation displayed");
                break;

            case "SUCCESS":
                login.enterOtpAndSubmit();
                Assert.assertTrue(
                        login.isDashboardVisible(),
                        "Dashboard not visible after login"
                );
                ExtentReportManager.getTest().pass("User logged in successfully");
                break;

            default:
                Assert.fail("Unknown ExpectedResult: " + expectedResult);
        }
    }
}
