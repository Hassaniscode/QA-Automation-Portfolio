# QA Automation Portfolio

End-to-end and API test automation portfolio demonstrating QA engineering across two frameworks and two test layers against real-world web and REST targets.

## Structure

```
qa-portfolio/
├── playwright-ts/       # E2E + API tests using Playwright + TypeScript
├── selenium-java/       # E2E + API tests using Selenium/REST Assured + Java (TestNG)
└── .github/workflows/   # CI pipelines for both suites
```

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

## CI/CD

Both suites run automatically on every push and pull request via GitHub Actions.

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
npx playwright test                  # all tests (E2E + API)
npx playwright test --project=api    # API tests only
npx playwright test --project=chromium  # E2E on Chrome only
npx playwright test --headed         # watch mode
npx playwright test --reporter=html  # with HTML report
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
mvn test                                                    # all tests (E2E + API)
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng-api.xml  # API tests only
```

---

## Author

**Hassan Faal** — [LinkedIn](https://www.linkedin.com/in/hassan-faal) · [hfaalszn@gmail.com](mailto:hfaalszn@gmail.com)
