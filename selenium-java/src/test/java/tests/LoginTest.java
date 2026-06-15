package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(description = "Valid credentials should navigate to inventory page")
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(TestData.STANDARD_USER, TestData.PASSWORD);
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
                "Expected to land on inventory page after login");
    }

    @Test(description = "Invalid credentials should show error message")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("wrong_user", "wrong_pass");
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Expected error message to be visible");
        Assert.assertTrue(loginPage.getErrorMessage().contains(TestData.ERROR_INVALID_CREDENTIALS));
    }

    @Test(description = "Locked out user should see locked error")
    public void testLockedOutUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(TestData.LOCKED_USER, TestData.PASSWORD);
        Assert.assertTrue(loginPage.isErrorDisplayed());
        Assert.assertTrue(loginPage.getErrorMessage().contains(TestData.ERROR_LOCKED_OUT));
    }
}
