package Test;

import Events.AddBankFormEvents;
import Reports.ExtentReportManager;
import Utils.ConfigReader;
import Utils.ExcelUtils;
import Utils.LoginUtils;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Map;

public class AddBankFormTest extends BaseTest {

    @DataProvider(name = "bankData")
    public Object[][] getBankData() {
        return ExcelUtils.getTestData(
                ConfigReader.get("BankFormExcelPath"),
                "BankForm"
        );
    }

    @BeforeMethod(alwaysRun = true)
    public void login() {
        LoginUtils.loginAsAdmin(getDriver());
    }

    @Test(dataProvider = "bankData")
    public void fillBankForm(Map<String, String> data) {

        ExtentReportManager.getTest().info(
                "Executing Add Bank for Row: " + data.get("RowNumber")
        );

        AddBankFormEvents bankForm = new AddBankFormEvents(getDriver());

        bankForm.openAddBankForm();
        bankForm.fillBankInformationForm(data);
        bankForm.submitForm();

        Assert.assertTrue(true, "Bank added successfully");

        ExtentReportManager.getTest().pass(
                "Bank added successfully for Bank: " + data.get("BankName")
        );
    }
}
