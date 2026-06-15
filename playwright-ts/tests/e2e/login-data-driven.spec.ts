import { test, expect } from '@playwright/test';
import { LoginPage } from '../../pages/LoginPage';
import { USERS, VALID_USERNAMES, ERROR_MESSAGES } from '../utils/constants';

const invalidLoginCases = [
  {
    name: 'invalid credentials',
    username: USERS.invalid.username,
    password: USERS.invalid.password,
    expectedError: ERROR_MESSAGES.invalidCredentials,
  },
  {
    name: 'locked out user',
    username: USERS.locked.username,
    password: USERS.locked.password,
    expectedError: ERROR_MESSAGES.lockedOut,
  },
  {
    name: 'empty username',
    username: '',
    password: USERS.standard.password,
    expectedError: ERROR_MESSAGES.usernameRequired,
  },
  {
    name: 'empty password',
    username: USERS.standard.username,
    password: '',
    expectedError: ERROR_MESSAGES.passwordRequired,
  },
  {
    name: 'both fields empty',
    username: '',
    password: '',
    expectedError: ERROR_MESSAGES.usernameRequired,
  },
];

test.describe('Login - Data-driven error scenarios', () => {
  for (const { name, username, password, expectedError } of invalidLoginCases) {
    test(`should show error: ${name}`, async ({ page }) => {
      const loginPage = new LoginPage(page);
      await loginPage.goto();
      await loginPage.login(username, password);

      await expect(loginPage.errorMessage).toBeVisible();
      await expect(loginPage.errorMessage).toContainText(expectedError);
    });
  }
});

test.describe('Login - Data-driven valid users', () => {
  for (const username of VALID_USERNAMES) {
    test(`should login successfully as ${username}`, async ({ page }) => {
      const loginPage = new LoginPage(page);
      await loginPage.goto();
      await loginPage.login(username, USERS.standard.password);

      await expect(page).toHaveURL(/inventory/);
    });
  }
});
