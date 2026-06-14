package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class CartDataDrivenTest extends BaseTest {

    private InventoryPage inventoryPage;

    @BeforeMethod
    public void loginBeforeEach() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(STANDARD_USER, PASSWORD);
        inventoryPage = new InventoryPage(driver);
    }

    @DataProvider(name = "cartItems")
    public Object[][] cartItemData() {
        return new Object[][] {
            { "Sauce Labs Backpack" },
            { "Sauce Labs Bike Light" },
            { "Sauce Labs Bolt T-Shirt" },
        };
    }

    @Test(dataProvider = "cartItems", description = "Adding item should show cart badge")
    public void testAddItemToCart(String itemName) {
        inventoryPage.addItemToCart(itemName);
        Assert.assertTrue(inventoryPage.isCartBadgeVisible(),
                "Cart badge should be visible after adding: " + itemName);
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), "1");
    }

    @Test(dataProvider = "cartItems", description = "Adding and removing item should clear badge")
    public void testAddAndRemoveItem(String itemName) {
        inventoryPage.addItemToCart(itemName);
        Assert.assertTrue(inventoryPage.isCartBadgeVisible());

        inventoryPage.removeItemFromCart(itemName);
        Assert.assertFalse(inventoryPage.isCartBadgeVisible(),
                "Cart badge should not be visible after removing: " + itemName);
    }
}
