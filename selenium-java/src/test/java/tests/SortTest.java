package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortTest extends BaseTest {

    private InventoryPage inventoryPage;

    @BeforeMethod
    public void loginBeforeEach() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(TestData.STANDARD_USER, TestData.PASSWORD);
        inventoryPage = new InventoryPage(driver);
    }

    @Test(description = "Products should sort by name A to Z")
    public void testSortNameAZ() {
        inventoryPage.sortBy(TestData.SORT_NAME_AZ);
        List<String> names = inventoryPage.getProductNames();
        List<String> sorted = new ArrayList<>(names);
        Collections.sort(sorted);
        Assert.assertEquals(names, sorted);
    }

    @Test(description = "Products should sort by name Z to A")
    public void testSortNameZA() {
        inventoryPage.sortBy(TestData.SORT_NAME_ZA);
        List<String> names = inventoryPage.getProductNames();
        List<String> sorted = new ArrayList<>(names);
        sorted.sort(Collections.reverseOrder());
        Assert.assertEquals(names, sorted);
    }

    @Test(description = "Products should sort by price low to high")
    public void testSortPriceLowHigh() {
        inventoryPage.sortBy(TestData.SORT_PRICE_LOW_HIGH);
        List<Double> prices = inventoryPage.getProductPrices();
        List<Double> sorted = new ArrayList<>(prices);
        Collections.sort(sorted);
        Assert.assertEquals(prices, sorted);
    }

    @Test(description = "Products should sort by price high to low")
    public void testSortPriceHighLow() {
        inventoryPage.sortBy(TestData.SORT_PRICE_HIGH_LOW);
        List<Double> prices = inventoryPage.getProductPrices();
        List<Double> sorted = new ArrayList<>(prices);
        sorted.sort(Collections.reverseOrder());
        Assert.assertEquals(prices, sorted);
    }
}
