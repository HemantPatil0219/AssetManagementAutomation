package Object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddBankForm {

    public AddBankForm(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//span[normalize-space()='BANK MANAGEMENT']")
    public WebElement clickBankManagementMenu;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    public WebElement clickAddBankButton;

    // Bank Name
    @FindBy(name = "bankName") // or use CSS/XPath
    public WebElement bankNameInput;

    // Bank Code
    @FindBy(name = "bankCode")
    public WebElement bankCodeInput;

    // Country dropdown
    @FindBy(name = "countryId")
    public WebElement countryDropdown;

    // State dropdown
    @FindBy(name = "stateId")
    public WebElement stateDropdown;

    // City dropdown
    @FindBy(name = "cityId")
    public WebElement cityDropdown;

    // Latitude
    @FindBy(name = "latitude")
    public WebElement latitudeInput;

    // Longitude
    @FindBy(name = "longitude")
    public WebElement longitudeInput;

    // Contact Person Name
    @FindBy(name = "contactPersonName")
    public WebElement contactPersonNameInput;

    // Contact Person Email
    @FindBy(name = "contactPersonEmail")
    public WebElement contactPersonEmailInput;

    // Contact Person Phone
    @FindBy(name = "contactPersonPhone")
    public WebElement contactPersonPhoneInput;

    // Status dropdown
    @FindBy(name = "status")
    public WebElement statusDropdown;

    // Logo upload
    @FindBy(css = "input[type='file']")
    public WebElement logoUploadInput;

    // Submit button
    @FindBy(css = "button[type='submit']")
    public WebElement submitButton;
}
