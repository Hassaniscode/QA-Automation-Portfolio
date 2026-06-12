package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    private final WebDriver driver;
    private final By checkoutButton = By.cssSelector("[data-test='checkout']");
    private final By cartItems = By.cssSelector(".cart_item");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void proceedToCheckout() {
        driver.findElement(checkoutButton).click();
    }

    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }
}
