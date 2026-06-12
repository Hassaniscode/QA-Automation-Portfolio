package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;

public class CartTest extends BaseTest {

    private InventoryPage inventoryPage;

    @BeforeMethod
    public void loginBeforeEach() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(STANDARD_USER, PASSWORD);
        inventoryPage = new InventoryPage(driver);
    }

    @Test(description = "Adding item to cart should update badge count")
    public void testAddItemToCart() {
        inventoryPage.addItemToCart("Sauce Labs Backpack");
        Assert.assertTrue(inventoryPage.isCartBadgeVisible());
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), "1");
    }

    @Test(description = "Removing item should clear cart badge")
    public void testRemoveItemFromCart() {
        inventoryPage.addItemToCart("Sauce Labs Backpack");
        inventoryPage.removeItemFromCart("Sauce Labs Backpack");
        Assert.assertFalse(inventoryPage.isCartBadgeVisible());
    }

    @Test(description = "Multiple items should reflect correct count")
    public void testAddMultipleItems() {
        inventoryPage.addItemToCart("Sauce Labs Backpack");
        inventoryPage.addItemToCart("Sauce Labs Bike Light");
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), "2");
    }

    @Test(description = "Full checkout flow should complete successfully")
    public void testFullCheckoutFlow() {
        inventoryPage.addItemToCart("Sauce Labs Backpack");
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getCartItemCount(), 1);
        cartPage.proceedToCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillShippingInfo("Hassan", "Faal", "75013");
        checkoutPage.finish();

        Assert.assertEquals(checkoutPage.getConfirmationText(), "Thank you for your order!");
    }
}
