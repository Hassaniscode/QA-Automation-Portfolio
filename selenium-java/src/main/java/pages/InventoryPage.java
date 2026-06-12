package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
        wait.until(d -> !d.findElements(inventoryItems).isEmpty());
    }

    public void addItemToCart(String itemName) {
        int before = getCartCount();
        findItemButton(itemName).click();
        wait.until(d -> getCartCount() == before + 1);
    }

    public void removeItemFromCart(String itemName) {
        int before = getCartCount();
        findItemButton(itemName).click();
        if (before <= 1) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(cartBadge));
        } else {
            wait.until(d -> getCartCount() == before - 1);
        }
    }

    private WebElement findItemButton(String itemName) {
        return wait.until(d -> {
            for (WebElement item : d.findElements(inventoryItems)) {
                if (item.getText().contains(itemName)) {
                    return item.findElement(By.tagName("button"));
                }
            }
            return null;
        });
    }

    private int getCartCount() {
        List<WebElement> badges = driver.findElements(cartBadge);
        if (badges.isEmpty()) return 0;
        try {
            return Integer.parseInt(badges.get(0).getText().trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String getCartBadgeCount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).getText();
    }

    public boolean isCartBadgeVisible() {
        return !driver.findElements(cartBadge).isEmpty();
    }

    public void goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
    }

    public void logout() {
        driver.findElement(menuButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }
}
