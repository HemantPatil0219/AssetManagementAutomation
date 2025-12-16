package Object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddBankForm {

    public AddBankForm(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /* ================= INPUT FIELDS ================= */

    @FindBy(name = "bankName")
    public WebElement bankNameInput;

    @FindBy(name = "bankCode")
    public WebElement bankCodeInput;

    @FindBy(name = "countryId")
    public WebElement countryDropdown;

    @FindBy(name = "stateId")
    public WebElement stateDropdown;

    @FindBy(name = "cityId")
    public WebElement cityDropdown;

    @FindBy(name = "latitude")
    public WebElement latitudeInput;

    @FindBy(name = "longitude")
    public WebElement longitudeInput;

    @FindBy(name = "contactPersonName")
    public WebElement contactPersonNameInput;

    @FindBy(name = "contactPersonEmail")
    public WebElement contactPersonEmailInput;

    @FindBy(name = "contactPersonPhone")
    public WebElement contactPersonPhoneInput;

    @FindBy(name = "status")
    public WebElement statusDropdown;

    @FindBy(xpath = "//input[@type='file']")
    public WebElement logoUploadInput;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submitButton;

    /* ================= ERROR MESSAGES ================= */

    @FindBy(xpath = "//input[@name='bankName']/following-sibling::div")
    public WebElement bankNameError;

    @FindBy(xpath = "//input[@name='bankCode']/following-sibling::div")
    public WebElement bankCodeError;

    @FindBy(xpath = "//select[@name='countryId']/following-sibling::div")
    public WebElement countryError;

    @FindBy(xpath = "//select[@name='stateId']/following-sibling::div")
    public WebElement stateError;

    @FindBy(xpath = "//input[@name='latitude']/following-sibling::div")
    public WebElement latitudeError;

    @FindBy(xpath = "//input[@name='longitude']/following-sibling::div")
    public WebElement longitudeError;

    @FindBy(xpath = "//input[@name='contactPersonPhone']/following-sibling::div")
    public WebElement phoneError;

    @FindBy(xpath = "//input[@type='file']/following-sibling::div")
    public WebElement logoError;
}
