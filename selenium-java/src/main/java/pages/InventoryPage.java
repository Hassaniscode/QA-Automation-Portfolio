package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class InventoryPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cartBadge = By.cssSelector(".shopping_cart_badge");
    private final By cartIcon = By.cssSelector(".shopping_cart_link");
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By inventoryItems = By.cssSelector(".inventory_item");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addItemToCart(String itemName) {
        getItemButton(itemName).click();
        wait.until(d -> getItemButton(itemName).getText().contains("Remove"));
    }

    public void removeItemFromCart(String itemName) {
        getItemButton(itemName).click();
        wait.until(d -> getItemButton(itemName).getText().contains("Add to cart"));
    }

    private WebElement getItemButton(String itemName) {
        List<WebElement> items = driver.findElements(inventoryItems);
        for (WebElement item : items) {
            if (item.getText().contains(itemName)) {
                return item.findElement(By.tagName("button"));
            }
        }
        throw new RuntimeException("Item not found: " + itemName);
    }

    public String getCartBadgeCount() {
        return driver.findElement(cartBadge).getText();
    }

    public boolean isCartBadgeVisible() {
        return !driver.findElements(cartBadge).isEmpty();
    }

    public void goToCart() {
        driver.findElement(cartIcon).click();
    }

    public void logout() {
        driver.findElement(menuButton).click();
        wait.until(d -> d.findElement(logoutLink).isDisplayed());
        driver.findElement(logoutLink).click();
    }
}
