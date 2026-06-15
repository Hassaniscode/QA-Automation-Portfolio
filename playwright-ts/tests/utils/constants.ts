export const URLS = {
  e2e: 'https://www.saucedemo.com',
  api: 'https://jsonplaceholder.typicode.com',
};

export const USERS = {
  standard: {
    username: 'standard_user',
    password: 'secret_sauce',
  },
  locked: {
    username: 'locked_out_user',
    password: 'secret_sauce',
  },
  invalid: {
    username: 'not_a_user',
    password: 'wrong_password',
  },
};

export const VALID_USERNAMES = [
  'standard_user',
  'problem_user',
  'performance_glitch_user',
];

export const ITEMS = {
  backpack: 'Sauce Labs Backpack',
  bikeLight: 'Sauce Labs Bike Light',
  tShirt: 'Sauce Labs Bolt T-Shirt',
};

export const ERROR_MESSAGES = {
  invalidCredentials: 'Username and password do not match',
  lockedOut: 'locked out',
  usernameRequired: 'Username is required',
  passwordRequired: 'Password is required',
};

export const CHECKOUT = {
  firstName: 'Hassan',
  lastName: 'Faal',
  postalCode: '75013',
  confirmationMessage: 'Thank you for your order!',
};

export const SORT_OPTIONS = {
  nameAZ: 'az',
  nameZA: 'za',
  priceLowHigh: 'lohi',
  priceHighLow: 'hilo',
};

export const API_TEST_DATA = {
  users: {
    create: { name: 'Hassan Faal', username: 'hassanf', email: 'hassan@example.com' },
    update: { name: 'Hassan Updated', username: 'hassanf', email: 'hassan.updated@example.com' },
    patch: { name: 'Hassan Patched' },
  },
  posts: {
    create: { title: 'API Testing with Playwright', body: 'Demonstrating API test automation', userId: 1 },
    update: { id: 1, title: 'Updated Title', body: 'Updated body content', userId: 1 },
  },
};
