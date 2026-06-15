package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

import java.time.Duration;

public class LogoutTest extends BaseTest {

    @Test(description = "Logout should redirect to login page")
    public void testLogoutRedirectsToLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(TestData.STANDARD_USER, TestData.PASSWORD);

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.logout();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-test='login-button']")));

        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"),
                "Expected to be redirected to login page after logout");
    }
}
