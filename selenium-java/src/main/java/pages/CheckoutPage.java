package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;

    private final By firstNameInput = By.cssSelector("[data-test='firstName']");
    private final By lastNameInput = By.cssSelector("[data-test='lastName']");
    private final By postalCodeInput = By.cssSelector("[data-test='postalCode']");
    private final By continueButton = By.cssSelector("[data-test='continue']");
    private final By finishButton = By.cssSelector("[data-test='finish']");
    private final By confirmationHeader = By.cssSelector(".complete-header");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        wait.until(ExpectedConditions.urlContains("checkout-step-one"));
    }

    public void fillShippingInfo(String firstName, String lastName, String zip) {
        setInputValue(firstNameInput, firstName);
        setInputValue(lastNameInput, lastName);
        setInputValue(postalCodeInput, zip);
        js.executeScript("arguments[0].closest('form').requestSubmit(arguments[0]);",
                wait.until(ExpectedConditions.elementToBeClickable(continueButton)));
        wait.until(ExpectedConditions.urlContains("checkout-step-two"));
    }

    public void finish() {
        js.executeScript("arguments[0].click();",
                wait.until(ExpectedConditions.elementToBeClickable(finishButton)));
        wait.until(ExpectedConditions.urlContains("checkout-complete"));
    }

    public String getConfirmationText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationHeader)).getText();
    }

    private void setInputValue(By locator, String value) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        js.executeScript(
                "var nativeSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
                "nativeSetter.call(arguments[0], arguments[1]);" +
                "arguments[0].dispatchEvent(new Event('input', {bubbles: true}));" +
                "arguments[0].dispatchEvent(new Event('change', {bubbles: true}));",
                element, value);
    }
}
