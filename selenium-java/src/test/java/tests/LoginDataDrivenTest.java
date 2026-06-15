package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginDataDrivenTest extends BaseTest {

    @DataProvider(name = "invalidLogins")
    public Object[][] invalidLoginData() {
        return new Object[][] {
            { "wrong_user",         "wrong_pass",       TestData.ERROR_INVALID_CREDENTIALS },
            { TestData.LOCKED_USER, TestData.PASSWORD,  TestData.ERROR_LOCKED_OUT },
            { "",                   TestData.PASSWORD,   TestData.ERROR_USERNAME_REQUIRED },
            { TestData.STANDARD_USER, "",                TestData.ERROR_PASSWORD_REQUIRED },
            { "",                   "",                  TestData.ERROR_USERNAME_REQUIRED },
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
            { TestData.VALID_USERNAMES[0] },
            { TestData.VALID_USERNAMES[1] },
            { TestData.VALID_USERNAMES[2] },
        };
    }

    @Test(dataProvider = "validUsers", description = "Valid user should reach inventory page")
    public void testValidLogin(String username) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, TestData.PASSWORD);
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
                "Expected to land on inventory page for user: " + username);
    }
}
