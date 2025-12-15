package Utils;

import Events.LoginPageEvents;
import Reports.ExtentReportManager;
import org.openqa.selenium.WebDriver;

public class LoginUtils {

    public static void loginAsAdmin(WebDriver driver) {

        ExtentReportManager.getTest().info("Logging in as Admin");

        driver.get(ConfigReader.get("BASEURLDEV"));

        LoginPageEvents login = new LoginPageEvents(driver);
        login.enterEmail(ConfigReader.get("ADMIN_EMAIL"));
        login.enterPassword(ConfigReader.get("ADMIN_PASSWORD"));
        login.clickSignIn();
        login.enterOtpAndSubmit();
    }
}
