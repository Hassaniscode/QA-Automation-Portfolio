package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By checkoutButton = By.cssSelector("[data-test='checkout']");
    private final By cartItems = By.cssSelector(".cart_item");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void proceedToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }

    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }
}
