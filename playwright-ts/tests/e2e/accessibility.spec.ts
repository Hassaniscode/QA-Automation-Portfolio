import { test, expect } from '@playwright/test';
import AxeBuilder from '@axe-core/playwright';
import { LoginPage } from '../../pages/LoginPage';
import { InventoryPage } from '../../pages/InventoryPage';
import { USERS } from '../utils/constants';

test.describe('Accessibility', () => {
  test('login page should have no critical a11y violations', async ({ page }) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();

    const results = await new AxeBuilder({ page })
      .withTags(['wcag2a', 'wcag2aa'])
      .analyze();

    expect(results.violations.filter(v => v.impact === 'critical')).toHaveLength(0);
  });

  test('inventory page should have no critical a11y violations', async ({ page }) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();
    await loginPage.login(USERS.standard.username, USERS.standard.password);

    const results = await new AxeBuilder({ page })
      .withTags(['wcag2a', 'wcag2aa'])
      // Exclude known SauceDemo bug: sort dropdown has no accessible label
      .exclude('.product_sort_container')
      .analyze();

    expect(results.violations.filter(v => v.impact === 'critical')).toHaveLength(0);
  });

  test('cart page should have no critical a11y violations', async ({ page }) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();
    await loginPage.login(USERS.standard.username, USERS.standard.password);

    const inventoryPage = new InventoryPage(page);
    await inventoryPage.addItemToCart('Sauce Labs Backpack');
    await inventoryPage.goToCart();

    const results = await new AxeBuilder({ page })
      .withTags(['wcag2a', 'wcag2aa'])
      .analyze();

    expect(results.violations.filter(v => v.impact === 'critical')).toHaveLength(0);
  });
});
