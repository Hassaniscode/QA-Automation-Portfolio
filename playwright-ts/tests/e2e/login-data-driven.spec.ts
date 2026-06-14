import { test, expect } from '@playwright/test';
import { LoginPage } from '../../pages/LoginPage';
import { USERS } from '../utils/constants';

const invalidLoginCases = [
  {
    name: 'invalid credentials',
    username: USERS.invalid.username,
    password: USERS.invalid.password,
    expectedError: 'Username and password do not match',
  },
  {
    name: 'locked out user',
    username: USERS.locked.username,
    password: USERS.locked.password,
    expectedError: 'locked out',
  },
  {
    name: 'empty username',
    username: '',
    password: USERS.standard.password,
    expectedError: 'Username is required',
  },
  {
    name: 'empty password',
    username: USERS.standard.username,
    password: '',
    expectedError: 'Password is required',
  },
  {
    name: 'both fields empty',
    username: '',
    password: '',
    expectedError: 'Username is required',
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

const validLoginUsers = [
  { name: 'standard_user', username: 'standard_user' },
  { name: 'problem_user', username: 'problem_user' },
  { name: 'performance_glitch_user', username: 'performance_glitch_user' },
];

test.describe('Login - Data-driven valid users', () => {
  for (const { name, username } of validLoginUsers) {
    test(`should login successfully as ${name}`, async ({ page }) => {
      const loginPage = new LoginPage(page);
      await loginPage.goto();
      await loginPage.login(username, 'secret_sauce');

      await expect(page).toHaveURL(/inventory/);
    });
  }
});
