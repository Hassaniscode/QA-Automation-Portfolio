import { test, expect } from '@playwright/test';
import { LoginPage } from '../../pages/LoginPage';
import { InventoryPage } from '../../pages/InventoryPage';
import { USERS, ITEMS } from '../utils/constants';

const cartItems = [
  { name: ITEMS.backpack, expectedBadge: '1' },
  { name: ITEMS.bikeLight, expectedBadge: '1' },
  { name: ITEMS.tShirt, expectedBadge: '1' },
];

test.describe('Cart - Data-driven add items', () => {
  let inventoryPage: InventoryPage;

  test.beforeEach(async ({ page }) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();
    await loginPage.login(USERS.standard.username, USERS.standard.password);
    inventoryPage = new InventoryPage(page);
  });

  for (const { name, expectedBadge } of cartItems) {
    test(`should add "${name}" to cart and show badge`, async () => {
      await inventoryPage.addItemToCart(name);
      await expect(inventoryPage.cartBadge).toHaveText(expectedBadge);
    });
  }

  for (const { name } of cartItems) {
    test(`should add and remove "${name}" from cart`, async () => {
      await inventoryPage.addItemToCart(name);
      await expect(inventoryPage.cartBadge).toBeVisible();

      await inventoryPage.removeItemFromCart(name);
      await expect(inventoryPage.cartBadge).not.toBeVisible();
    });
  }
});
