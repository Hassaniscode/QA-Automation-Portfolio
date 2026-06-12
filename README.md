# QA Automation Portfolio

End-to-end test automation portfolio demonstrating QA engineering across two frameworks against [SauceDemo](https://www.saucedemo.com), a standard e-commerce demo app.

## Structure

```
qa-portfolio/
├── playwright-ts/       # E2E tests using Playwright + TypeScript
├── selenium-java/       # E2E tests using Selenium WebDriver + Java (TestNG)
└── .github/workflows/   # CI pipelines for both suites
```

## What's Tested

| Flow | Playwright | Selenium |
|------|-----------|---------|
| Login (valid credentials) | ✅ | ✅ |
| Login (invalid credentials) | ✅ | ✅ |
| Add item to cart | ✅ | ✅ |
| Remove item from cart | ✅ | ✅ |
| Full checkout flow | ✅ | ✅ |
| Logout | ✅ | ✅ |

## CI/CD

Both suites run automatically on every push and pull request via GitHub Actions.

---

## Playwright (TypeScript)

**Stack:** Playwright · TypeScript · Page Object Model  
**Target:** https://www.saucedemo.com

### Setup
```bash
cd playwright-ts
npm install
npx playwright install
```

### Run Tests
```bash
npx playwright test                  # all tests
npx playwright test --headed         # watch mode
npx playwright test --reporter=html  # with HTML report
```

---

## Selenium (Java)

**Stack:** Selenium WebDriver · Java · TestNG · Maven · Page Object Model  
**Target:** https://www.saucedemo.com

### Setup
```bash
cd selenium-java
mvn clean install
```

### Run Tests
```bash
mvn test
```

---

## Author

**Hassan Faal** — [LinkedIn](https://www.linkedin.com/in/hassan-faal) · [hfaalszn@gmail.com](mailto:hfaalszn@gmail.com)
