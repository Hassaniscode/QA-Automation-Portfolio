# QA Automation Portfolio

![Playwright Tests](https://github.com/Hassaniscode/QA-Automation-Portfolio/actions/workflows/playwright.yml/badge.svg)
![Selenium Tests](https://github.com/Hassaniscode/QA-Automation-Portfolio/actions/workflows/selenium.yml/badge.svg)

End-to-end and API test automation portfolio demonstrating QA engineering across two frameworks and two test layers against real-world web and REST targets.

---

## Skills Demonstrated

| Skill | Details |
|-------|---------|
| Page Object Model (POM) | Shared across both Playwright and Selenium suites |
| Cross-browser Testing | Chromium, Firefox, and WebKit via Playwright |
| API Test Automation | REST API testing with Playwright APIRequestContext and REST Assured |
| CI/CD Pipelines | GitHub Actions workflows for both suites, triggered on push and PR |
| Test Reporting | Playwright HTML reports and TestNG Surefire reports as CI artifacts |
| Screenshot on Failure | Automatic screenshot capture when Selenium tests fail |
| Video on Failure | Playwright retains video recordings for failed tests |
| Data-driven Constants | Centralized test data via constants files and base test classes |
| Multiple Protocols | HTTP methods: GET, POST, PUT, PATCH, DELETE |
| BDD-style Assertions | REST Assured given/when/then with Hamcrest matchers |

---

## Test Results

```
Playwright (TypeScript)             Selenium + REST Assured (Java)
─────────────────────────           ──────────────────────────────
E2E (Chromium)   11 passed          E2E (Chrome)      7 passed
E2E (Firefox)    11 passed          API (Users)       7 passed
E2E (WebKit)     11 passed          API (Posts)       6 passed
API (Users)       7 passed          ──────────────────────────────
API (Posts)       6 passed          Total            20 passed
─────────────────────────
Total            46 passed
```

---

## Structure

```
qa-portfolio/
├── playwright-ts/           # E2E + API tests — Playwright + TypeScript
│   ├── pages/               # Page Object classes (Login, Inventory, Cart, Checkout)
│   ├── tests/e2e/           # E2E browser tests against SauceDemo
│   ├── tests/api/           # API tests against JSONPlaceholder
│   └── playwright.config.ts # Multi-project config (3 browsers + API)
├── selenium-java/           # E2E + API tests — Selenium + REST Assured + Java
│   ├── src/main/java/pages/ # Page Object classes
│   ├── src/test/java/tests/ # E2E tests (BaseTest, LoginTest, CartTest)
│   ├── src/test/java/tests/api/ # API tests (UsersApiTest, PostsApiTest)
│   └── pom.xml              # Maven config with Selenium, REST Assured, TestNG
└── .github/workflows/       # CI pipelines for both suites
```

---

## What's Tested

### E2E Tests (UI) — [SauceDemo](https://www.saucedemo.com)

| Flow | Playwright | Selenium |
|------|-----------|---------|
| Login (valid credentials) | ✅ | ✅ |
| Login (invalid credentials) | ✅ | ✅ |
| Login (locked out user) | ✅ | ✅ |
| Add item to cart | ✅ | ✅ |
| Remove item from cart | ✅ | ✅ |
| Add multiple items | ✅ | ✅ |
| Full checkout flow | ✅ | ✅ |
| Logout | ✅ | — |

### API Tests — [JSONPlaceholder](https://jsonplaceholder.typicode.com)

| Operation | Playwright | REST Assured |
|-----------|-----------|-------------|
| GET collection | ✅ | ✅ |
| GET single resource | ✅ | ✅ |
| GET 404 (not found) | ✅ | ✅ |
| GET filtered (query params) | ✅ | ✅ |
| GET nested resource | ✅ | ✅ |
| POST (create) | ✅ | ✅ |
| PUT (full update) | ✅ | ✅ |
| PATCH (partial update) | ✅ | ✅ |
| DELETE | ✅ | ✅ |

---

## CI/CD

Both suites run automatically on every push and pull request via GitHub Actions. Test reports are uploaded as artifacts on every run.

| Workflow | Artifacts |
|----------|-----------|
| Playwright Tests | HTML report (`playwright-report/`) |
| Selenium Tests | Surefire XML reports + failure screenshots |

---

## Playwright (TypeScript)

**Stack:** Playwright · TypeScript · Page Object Model
**E2E Target:** https://www.saucedemo.com
**API Target:** https://jsonplaceholder.typicode.com

### Setup
```bash
cd playwright-ts
npm install
npx playwright install
```

### Run Tests
```bash
npx playwright test                     # all tests (E2E + API)
npx playwright test --project=api       # API tests only
npx playwright test --project=chromium  # E2E on Chrome only
npx playwright test --headed            # watch mode
npx playwright test --reporter=html     # with HTML report
```

---

## Selenium + REST Assured (Java)

**Stack:** Selenium WebDriver · REST Assured · Java · TestNG · Maven · Page Object Model
**E2E Target:** https://www.saucedemo.com
**API Target:** https://jsonplaceholder.typicode.com

### Setup
```bash
cd selenium-java
mvn clean install
```

### Run Tests
```bash
mvn test                                                             # all tests (E2E + API)
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng-api.xml  # API tests only
```

---

## Author

**Hassan Faal** — [LinkedIn](https://www.linkedin.com/in/hassan-faal) · [hfaalszn@gmail.com](mailto:hfaalszn@gmail.com)
