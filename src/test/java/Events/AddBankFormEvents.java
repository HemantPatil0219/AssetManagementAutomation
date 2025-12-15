package Events;

import Object.AddBankForm;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.Map;

public class AddBankFormEvents {

    private final AddBankForm bankPage;
    private final WebDriverWait wait;

    public AddBankFormEvents(WebDriver driver) {
        this.bankPage = new AddBankForm(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /**
     * Navigate to Add Bank form
     */
    public void openAddBankForm() {

        wait.until(ExpectedConditions.elementToBeClickable(
                bankPage.clickBankManagementMenu
        )).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                bankPage.clickAddBankButton
        )).click();
    }

    /**
     * Fill Add Bank form using Excel data
     */
    public void fillBankInformationForm(Map<String, String> data) {

        // Bank Name
        wait.until(ExpectedConditions.visibilityOf(bankPage.bankNameInput));
        bankPage.bankNameInput.clear();
        bankPage.bankNameInput.sendKeys(
                data.getOrDefault("BankName", "")
        );

        // Bank Code
        bankPage.bankCodeInput.clear();
        bankPage.bankCodeInput.sendKeys(
                data.getOrDefault("BankCode", "")
        );

        // Country
        wait.until(ExpectedConditions.elementToBeClickable(bankPage.countryDropdown));
        new Select(bankPage.countryDropdown)
                .selectByVisibleText(data.get("Country"));

        // State (dependent dropdown)
        wait.until(driver -> bankPage.stateDropdown.isEnabled());
        new Select(bankPage.stateDropdown)
                .selectByVisibleText(data.get("State"));

        // City (dependent dropdown)
        wait.until(driver -> bankPage.cityDropdown.isEnabled());
        new Select(bankPage.cityDropdown)
                .selectByVisibleText(data.get("City"));

        // Latitude
        bankPage.latitudeInput.clear();
        bankPage.latitudeInput.sendKeys(
                data.getOrDefault("Latitude", "")
        );

        // Longitude
        bankPage.longitudeInput.clear();
        bankPage.longitudeInput.sendKeys(
                data.getOrDefault("Longitude", "")
        );

        // Contact Person Name
        bankPage.contactPersonNameInput.clear();
        bankPage.contactPersonNameInput.sendKeys(
                data.getOrDefault("ContactPersonName", "")
        );

        // Contact Person Email
        bankPage.contactPersonEmailInput.clear();
        bankPage.contactPersonEmailInput.sendKeys(
                data.getOrDefault("ContactPersonEmail", "")
        );

        // Contact Person Phone
        bankPage.contactPersonPhoneInput.clear();
        bankPage.contactPersonPhoneInput.sendKeys(
                data.getOrDefault("ContactPersonPhone", "")
        );

        // Status
        new Select(bankPage.statusDropdown)
                .selectByVisibleText(data.get("Status"));

        // Logo Upload (optional)
        String logoPath = data.get("LogoPath");
        if (logoPath != null && !logoPath.trim().isEmpty()) {
            uploadLogo(logoPath);
        }
    }

    /**
     * Upload logo file
     */
    private void uploadLogo(String filePath) {

        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("Logo file not found: " + filePath);
        }
        bankPage.logoUploadInput.sendKeys(file.getAbsolutePath());
    }

    /**
     * Submit the form
     */
    public void submitForm() {

        wait.until(ExpectedConditions.elementToBeClickable(
                bankPage.submitButton
        )).click();
    }

    /**
     * Fill and submit in one call
     */
    public void fillAndSubmit(Map<String, String> data) {

        fillBankInformationForm(data);
        submitForm();
    }

    /**
     * Placeholder for future success validation
     */
    public boolean isSuccessMessageVisible() {
        // Update when success toast/modal locator is available
        return true;
    }
}
