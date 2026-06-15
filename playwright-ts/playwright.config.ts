import { defineConfig, devices } from '@playwright/test';
import { URLS } from './tests/utils/constants';

export default defineConfig({
  timeout: 30000,
  retries: 1,
  snapshotPathTemplate: '{testDir}/__snapshots__/{arg}{ext}',
  reporter: [
    ['html', { open: 'never' }],
    ['list'],
    ['allure-playwright', { outputFolder: 'allure-results' }],
  ],
  projects: [
    // E2E browser tests
    {
      name: 'chromium',
      testDir: './tests/e2e',
      use: {
        ...devices['Desktop Chrome'],
        baseURL: URLS.e2e,
        screenshot: 'only-on-failure',
        video: 'retain-on-failure',
        trace: 'retain-on-failure',
        headless: true,
      },
    },
    {
      name: 'firefox',
      testDir: './tests/e2e',
      use: {
        ...devices['Desktop Firefox'],
        baseURL: URLS.e2e,
        screenshot: 'only-on-failure',
        video: 'retain-on-failure',
        trace: 'retain-on-failure',
        headless: true,
      },
    },
    {
      name: 'webkit',
      testDir: './tests/e2e',
      use: {
        ...devices['Desktop Safari'],
        baseURL: URLS.e2e,
        screenshot: 'only-on-failure',
        video: 'retain-on-failure',
        trace: 'retain-on-failure',
        headless: true,
      },
    },
    // API tests (no browser needed)
    {
      name: 'api',
      testDir: './tests/api',
      use: {
        baseURL: URLS.api,
      },
    },
  ],
});
