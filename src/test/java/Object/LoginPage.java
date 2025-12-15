package Object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

     public LoginPage(WebDriver driver){

         PageFactory.initElements(driver,this);
     }
    @FindBy(css = "input[placeholder='Email']")
    public WebElement emailInput;

    @FindBy(css ="input[placeholder='Password']")
    public WebElement passwordInput;

    @FindBy(css ="button[type='submit']")
    public WebElement signInButton;

    @FindBy(xpath = "//label[contains(normalize-space(),'OTP')]")
    public WebElement otpLabel;

    @FindBy(css = "input[placeholder='Enter Email OTP']")
    public WebElement otpInput;

    @FindBy(css = "button[type='submit']")
    public WebElement otpSubmitButton;

    @FindBy(css ="span[class='dark-logo'] span")
    public WebElement dashboardHeader;

    @FindBy(xpath = "(//div[@class='text-danger'])[1]")
    public WebElement emailErrorMsg;

    @FindBy(xpath = "(//div[@id='3'])[1]")
    public WebElement invalidCredentialMsg;

    @FindBy(xpath = "(//div[@class='text-danger'])[1]")
    public  WebElement passwordErrorMsg;



}
