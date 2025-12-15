package Events;

import Object.LoginPage;
import Test.BaseTest;
import org.apache.commons.math3.analysis.function.Exp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPageEvents extends LoginPage {

    WebDriverWait wait;

    public LoginPageEvents(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void loadHomePage(String url) {
        BaseTest.getDriver().get(url);
        wait.until(ExpectedConditions.visibilityOf(emailInput));
    }

    public void enterEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickSignIn() {
        signInButton.click();
    }

    public void enterOtpAndSubmit() {
        String otpText = wait.until(ExpectedConditions.visibilityOf(otpLabel)).getText();
        String otp = otpText.replaceAll("\\D+", "");
        otpInput.sendKeys(otp);
        otpSubmitButton.click();
    }



    public boolean isDashboardVisible() {
        return wait.until(ExpectedConditions.visibilityOf(dashboardHeader)).isDisplayed();
    }

    public String getInvalidCredentialsMessage() {
        return wait.until(ExpectedConditions.visibilityOf(invalidCredentialMsg)).getText();
    }

    public String getEmailErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(emailErrorMsg)).getText();
    }

    public String getPasswordErrorMessage(){
        return wait.until(ExpectedConditions.visibilityOf(passwordErrorMsg)).getText();
    }
}
