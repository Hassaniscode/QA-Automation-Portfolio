import { test, expect } from '@playwright/test';
import { LoginPage } from '../../pages/LoginPage';
import { USERS, ERROR_MESSAGES } from '../utils/constants';

test.describe('Login', () => {
  let loginPage: LoginPage;

  test.beforeEach(async ({ page }) => {
    loginPage = new LoginPage(page);
    await loginPage.goto();
  });

  test('should login successfully with valid credentials', async ({ page }) => {
    await loginPage.login(USERS.standard.username, USERS.standard.password);
    await expect(page).toHaveURL(/inventory/);
  });

  test('should show error for invalid credentials', async () => {
    await loginPage.login(USERS.invalid.username, USERS.invalid.password);
    await expect(loginPage.errorMessage).toBeVisible();
    await expect(loginPage.errorMessage).toContainText(ERROR_MESSAGES.invalidCredentials);
  });

  test('should show error for locked out user', async () => {
    await loginPage.login(USERS.locked.username, USERS.locked.password);
    await expect(loginPage.errorMessage).toBeVisible();
    await expect(loginPage.errorMessage).toContainText(ERROR_MESSAGES.lockedOut);
  });

  test('should show error when username is missing', async () => {
    await loginPage.login('', USERS.standard.password);
    await expect(loginPage.errorMessage).toContainText(ERROR_MESSAGES.usernameRequired);
  });

  test('should show error when password is missing', async () => {
    await loginPage.login(USERS.standard.username, '');
    await expect(loginPage.errorMessage).toContainText(ERROR_MESSAGES.passwordRequired);
  });
});
