package Test;

import Events.AddBankFormEvents;
import Object.AddBankForm;
import Utils.LoginUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddBankFormTest extends BaseTest {

    private AddBankFormEvents events;
    private AddBankForm page;

    @BeforeMethod
    public void setup() {
        LoginUtils.loginAsAdmin(getDriver());
        page = new AddBankForm(getDriver());
        events = new AddBankFormEvents(getDriver());
    }

    @Test
    public void verifyAllMandatoryFieldValidations() {

        events.submitForm();

        Assert.assertEquals(
                events.bankNameError(),
                "Bank name must be at least 2 characters."
        );

        Assert.assertEquals(
                events.bankCodeError(),
                "Bank code must be exactly 5 alphanumeric characters."
        );

        Assert.assertEquals(
                events.countryError(),
                "Country is required."
        );

        Assert.assertEquals(
                events.stateError(),
                "State is required."
        );

        Assert.assertEquals(
                events.latitudeError(),
                "Enter a valid latitude."
        );

        Assert.assertEquals(
                events.longitudeError(),
                "Enter a valid longitude."
        );

        Assert.assertEquals(
                events.phoneError(),
                "Enter a valid mobile number."
        );

        Assert.assertEquals(
                events.logoError(),
                "Logo is required."
        );
    }

    @Test
    public void verifyInvalidBankName() {

        page.bankNameInput.sendKeys("A");
        events.submitForm();

        Assert.assertEquals(
                events.bankNameError(),
                "Bank name must be at least 2 characters."
        );
    }

    @Test
    public void verifyInvalidBankCode() {

        page.bankCodeInput.sendKeys("12@");
        events.submitForm();

        Assert.assertEquals(
                events.bankCodeError(),
                "Bank code must be exactly 5 alphanumeric characters."
        );
    }

    @Test
    public void verifyInvalidLatitudeLongitude() {

        page.latitudeInput.sendKeys("ABC");
        page.longitudeInput.sendKeys("XYZ");
        events.submitForm();

        Assert.assertEquals(events.latitudeError(), "Enter a valid latitude.");
        Assert.assertEquals(events.longitudeError(), "Enter a valid longitude.");
    }

    @Test
    public void verifyInvalidPhoneNumber() {

        page.contactPersonPhoneInput.sendKeys("ABCDE");
        events.submitForm();

        Assert.assertEquals(
                events.phoneError(),
                "Enter a valid mobile number."
        );
    }
}
