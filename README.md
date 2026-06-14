# QA Automation Portfolio

![Playwright Tests](https://github.com/Hassaniscode/QA-Automation-Portfolio/actions/workflows/playwright.yml/badge.svg)
![Selenium Tests](https://github.com/Hassaniscode/QA-Automation-Portfolio/actions/workflows/selenium.yml/badge.svg)
![Pytest Tests](https://github.com/Hassaniscode/QA-Automation-Portfolio/actions/workflows/pytest.yml/badge.svg)
![Allure Report](https://github.com/Hassaniscode/QA-Automation-Portfolio/actions/workflows/allure-report.yml/badge.svg)

End-to-end and API test automation portfolio demonstrating QA engineering across three frameworks and languages against real-world web and REST targets.

**[View Allure Report](https://hassaniscode.github.io/QA-Automation-Portfolio/)**

---

## Skills Demonstrated

| Skill | Details |
|-------|---------|
| Page Object Model (POM) | Shared across Playwright, Selenium Java, and Pytest suites |
| Cross-browser Testing | Chromium, Firefox, and WebKit via Playwright |
| API Test Automation | REST API testing with Playwright, REST Assured, and Python requests |
| CI/CD Pipelines | GitHub Actions workflows for all three suites, triggered on push and PR |
| Test Reporting | Playwright HTML, TestNG Surefire, and pytest-html reports as CI artifacts |
| Screenshot on Failure | Automatic screenshot capture when Selenium tests fail |
| Video on Failure | Playwright retains video recordings for failed tests |
| Data-driven Testing | Parameterized tests via Playwright loops, TestNG @DataProvider, pytest @parametrize |
| Multiple Protocols | HTTP methods: GET, POST, PUT, PATCH, DELETE |
| BDD-style Assertions | REST Assured given/when/then with Hamcrest matchers |
| Multi-language Coverage | TypeScript, Java, and Python across three frameworks |

---

## Test Results

```
Playwright (TypeScript)     Selenium + REST Assured (Java)     Pytest + Selenium (Python)
─────────────────────────   ──────────────────────────────     ──────────────────────────
E2E (Chromium)   11 passed  E2E (Chrome)      7 passed        E2E (Chrome)     17 passed
E2E (Firefox)    11 passed  API (Users)       7 passed        API (Users)       7 passed
E2E (WebKit)     11 passed  API (Posts)       6 passed        API (Posts)       6 passed
API (Users)       7 passed  ──────────────────────────────     ──────────────────────────
API (Posts)       6 passed  Total            20 passed        Total            30 passed
─────────────────────────
Total            46 passed                     Grand Total: 96 passed
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
├── pytest-selenium/         # E2E + API tests — Pytest + Selenium + Python
│   ├── pages/               # Page Object classes (Login, Inventory, Cart, Checkout)
│   ├── tests/e2e/           # E2E browser tests against SauceDemo
│   ├── tests/api/           # API tests against JSONPlaceholder
│   └── requirements.txt     # Python dependencies
└── .github/workflows/       # CI pipelines for all three suites
```

---

## What's Tested

### E2E Tests (UI) — [SauceDemo](https://www.saucedemo.com)

| Flow | Playwright | Selenium | Pytest |
|------|-----------|---------|--------|
| Login (valid credentials) | ✅ | ✅ | ✅ |
| Login (invalid credentials) | ✅ | ✅ | ✅ |
| Login (locked out user) | ✅ | ✅ | ✅ |
| Add item to cart | ✅ | ✅ | ✅ |
| Remove item from cart | ✅ | ✅ | ✅ |
| Add multiple items | ✅ | ✅ | ✅ |
| Full checkout flow | ✅ | ✅ | ✅ |
| Logout | ✅ | — | ✅ |

### API Tests — [JSONPlaceholder](https://jsonplaceholder.typicode.com)

| Operation | Playwright | REST Assured | Pytest |
|-----------|-----------|-------------|--------|
| GET collection | ✅ | ✅ | ✅ |
| GET single resource | ✅ | ✅ | ✅ |
| GET 404 (not found) | ✅ | ✅ | ✅ |
| GET filtered (query params) | ✅ | ✅ | ✅ |
| GET nested resource | ✅ | ✅ | ✅ |
| POST (create) | ✅ | ✅ | ✅ |
| PUT (full update) | ✅ | ✅ | ✅ |
| PATCH (partial update) | ✅ | ✅ | ✅ |
| DELETE | ✅ | ✅ | ✅ |

---

## CI/CD

All three suites run automatically on every push and pull request via GitHub Actions. Test reports are uploaded as artifacts on every run.

| Workflow | Artifacts |
|----------|-----------|
| Playwright Tests | HTML report (`playwright-report/`) |
| Selenium Tests | Surefire XML reports + failure screenshots |
| Pytest Tests | pytest-html reports (`reports/`) |

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

## Pytest + Selenium (Python)

**Stack:** Pytest · Selenium WebDriver · Python · Page Object Model
**E2E Target:** https://www.saucedemo.com
**API Target:** https://jsonplaceholder.typicode.com

### Setup
```bash
cd pytest-selenium
python -m venv venv
source venv/bin/activate
pip install -r requirements.txt
```

### Run Tests
```bash
pytest                          # all tests (E2E + API)
pytest tests/api/ -v            # API tests only
pytest tests/e2e/ -v            # E2E tests only
pytest --html=report.html       # with HTML report
```

---

## Author

**Hassan Faal** — [LinkedIn](https://www.linkedin.com/in/hassan-faal) · [hfaalszn@gmail.com](mailto:hfaalszn@gmail.com)
