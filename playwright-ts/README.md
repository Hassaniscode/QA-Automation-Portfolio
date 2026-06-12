# Playwright TypeScript Suite

E2E tests for [SauceDemo](https://www.saucedemo.com) using Playwright with TypeScript and Page Object Model.

## Structure

```
playwright-ts/
├── pages/                  # Page Object Models
│   ├── LoginPage.ts
│   ├── InventoryPage.ts
│   └── CartPage.ts         # CartPage + CheckoutPage
├── tests/
│   ├── e2e/                # Test specs
│   │   ├── login.spec.ts
│   │   ├── cart.spec.ts
│   │   └── logout.spec.ts
│   └── utils/
│       └── constants.ts    # Shared test data
└── playwright.config.ts
```

## Test Coverage

### Login (`login.spec.ts`)
- ✅ Valid login navigates to inventory
- ✅ Invalid credentials show error
- ✅ Locked out user shows specific error
- ✅ Missing username shows validation error
- ✅ Missing password shows validation error

### Cart (`cart.spec.ts`)
- ✅ Add single item updates badge
- ✅ Remove item clears badge
- ✅ Add multiple items shows correct count
- ✅ Cart page reflects added items
- ✅ Full checkout flow completes successfully

### Logout (`logout.spec.ts`)
- ✅ Logout redirects to login page

## Design Decisions

- **Page Object Model**: Locators and actions live in page classes, keeping tests readable and maintainable
- **data-test attributes**: Using `[data-test="..."]` selectors for resilience against UI changes
- **Headless CI**: Tests run headless in GitHub Actions; screenshots and video captured on failure
- **Retry on failure**: Single retry configured to reduce flakiness from network timing
