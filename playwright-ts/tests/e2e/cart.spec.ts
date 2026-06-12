import { test, expect } from '@playwright/test';
import { LoginPage } from '../../pages/LoginPage';
import { InventoryPage } from '../../pages/InventoryPage';
import { CartPage, CheckoutPage } from '../../pages/CartPage';
import { USERS, ITEMS } from '../utils/constants';

test.describe('Shopping Cart', () => {
  let inventoryPage: InventoryPage;

  test.beforeEach(async ({ page }) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();
    await loginPage.login(USERS.standard.username, USERS.standard.password);
    inventoryPage = new InventoryPage(page);
  });

  test('should add an item to cart', async () => {
    await inventoryPage.addItemToCart(ITEMS.backpack);
    await expect(inventoryPage.cartBadge).toHaveText('1');
  });

  test('should remove an item from cart', async () => {
    await inventoryPage.addItemToCart(ITEMS.backpack);
    await inventoryPage.removeItemFromCart(ITEMS.backpack);
    await expect(inventoryPage.cartBadge).not.toBeVisible();
  });

  test('should add multiple items to cart', async () => {
    await inventoryPage.addItemToCart(ITEMS.backpack);
    await inventoryPage.addItemToCart(ITEMS.bikeLight);
    await expect(inventoryPage.cartBadge).toHaveText('2');
  });

  test('should reflect cart items on cart page', async ({ page }) => {
    await inventoryPage.addItemToCart(ITEMS.backpack);
    await inventoryPage.goToCart();
    const cartPage = new CartPage(page);
    await expect(cartPage.cartItems).toHaveCount(1);
  });
});

test.describe('Checkout', () => {
  test('should complete full checkout flow', async ({ page }) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();
    await loginPage.login(USERS.standard.username, USERS.standard.password);

    const inventoryPage = new InventoryPage(page);
    await inventoryPage.addItemToCart(ITEMS.backpack);
    await inventoryPage.goToCart();

    const cartPage = new CartPage(page);
    await cartPage.proceedToCheckout();

    const checkoutPage = new CheckoutPage(page);
    await checkoutPage.fillShippingInfo('Hassan', 'Faal', '75013');
    await checkoutPage.finish();

    await expect(checkoutPage.confirmationHeader).toHaveText('Thank you for your order!');
  });
});
