package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginDataDrivenTest extends BaseTest {

    @DataProvider(name = "invalidLogins")
    public Object[][] invalidLoginData() {
        return new Object[][] {
            { "wrong_user",      "wrong_pass",   "Username and password do not match" },
            { "locked_out_user", "secret_sauce",  "locked out" },
            { "",                "secret_sauce",  "Username is required" },
            { "standard_user",   "",              "Password is required" },
            { "",                "",              "Username is required" },
        };
    }

    @Test(dataProvider = "invalidLogins", description = "Invalid login should show expected error")
    public void testInvalidLogin(String username, String password, String expectedError) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message should be visible");
        Assert.assertTrue(loginPage.getErrorMessage().contains(expectedError),
                "Expected error to contain: " + expectedError);
    }

    @DataProvider(name = "validUsers")
    public Object[][] validUserData() {
        return new Object[][] {
            { "standard_user" },
            { "problem_user" },
            { "performance_glitch_user" },
        };
    }

    @Test(dataProvider = "validUsers", description = "Valid user should reach inventory page")
    public void testValidLogin(String username) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, PASSWORD);
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
                "Expected to land on inventory page for user: " + username);
    }
}
