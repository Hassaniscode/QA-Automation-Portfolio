package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cartBadge = By.cssSelector(".shopping_cart_badge");
    private final By cartIcon = By.cssSelector(".shopping_cart_link");
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By inventoryItems = By.cssSelector(".inventory_item");
    private final By sortDropdown = By.cssSelector("[data-test='product-sort-container']");
    private final By itemNames = By.cssSelector(".inventory_item_name");
    private final By itemPrices = By.cssSelector(".inventory_item_price");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".inventory_item button")));
    }

    public void addItemToCart(String itemName) {
        int target = getCartCount() + 1;
        clickButton(findItemButton(itemName));
        wait.until(d -> getCartCount() == target);
    }

    public void removeItemFromCart(String itemName) {
        int target = getCartCount() - 1;
        clickButton(findItemButton(itemName));
        if (target == 0) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(cartBadge));
        } else {
            wait.until(d -> getCartCount() == target);
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

    private void clickButton(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
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
        clickButton(wait.until(ExpectedConditions.elementToBeClickable(cartIcon)));
    }

    public void sortBy(String option) {
        new Select(driver.findElement(sortDropdown)).selectByValue(option);
    }

    public List<String> getProductNames() {
        return driver.findElements(itemNames).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<Double> getProductPrices() {
        return driver.findElements(itemPrices).stream()
                .map(el -> Double.parseDouble(el.getText().replace("$", "")))
                .collect(Collectors.toList());
    }

    public void logout() {
        clickButton(driver.findElement(menuButton));
        clickButton(wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink)));
    }
}
