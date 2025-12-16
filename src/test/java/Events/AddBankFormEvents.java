package Events;

import Object.AddBankForm;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddBankFormEvents {

    private final AddBankForm page;
    private final WebDriverWait wait;

    public AddBankFormEvents(WebDriver driver) {
        this.page = new AddBankForm(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void submitForm() {
        wait.until(ExpectedConditions.elementToBeClickable(page.submitButton)).click();
    }

    /* ================= ERROR GETTERS ================= */

    public String bankNameError() {
        return wait.until(ExpectedConditions.visibilityOf(page.bankNameError)).getText();
    }

    public String bankCodeError() {
        return wait.until(ExpectedConditions.visibilityOf(page.bankCodeError)).getText();
    }

    public String countryError() {
        return wait.until(ExpectedConditions.visibilityOf(page.countryError)).getText();
    }

    public String stateError() {
        return wait.until(ExpectedConditions.visibilityOf(page.stateError)).getText();
    }

    public String latitudeError() {
        return wait.until(ExpectedConditions.visibilityOf(page.latitudeError)).getText();
    }

    public String longitudeError() {
        return wait.until(ExpectedConditions.visibilityOf(page.longitudeError)).getText();
    }

    public String phoneError() {
        return wait.until(ExpectedConditions.visibilityOf(page.phoneError)).getText();
    }

    public String logoError() {
        return wait.until(ExpectedConditions.visibilityOf(page.logoError)).getText();
    }
}
