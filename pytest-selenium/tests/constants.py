URLS = {
    "e2e": "https://www.saucedemo.com",
    "api": "https://jsonplaceholder.typicode.com",
}

USERS = {
    "standard": {"username": "standard_user", "password": "secret_sauce"},
    "locked": {"username": "locked_out_user", "password": "secret_sauce"},
    "invalid": {"username": "wrong_user", "password": "wrong_pass"},
}

VALID_USERNAMES = ["standard_user", "problem_user", "performance_glitch_user"]

ITEMS = {
    "backpack": "Sauce Labs Backpack",
    "bikeLight": "Sauce Labs Bike Light",
    "tShirt": "Sauce Labs Bolt T-Shirt",
}

ERROR_MESSAGES = {
    "invalidCredentials": "Username and password do not match",
    "lockedOut": "locked out",
    "usernameRequired": "Username is required",
    "passwordRequired": "Password is required",
}

CHECKOUT = {
    "firstName": "Hassan",
    "lastName": "Faal",
    "postalCode": "75013",
    "confirmationMessage": "Thank you for your order!",
}

SORT_OPTIONS = {
    "nameAZ": "az",
    "nameZA": "za",
    "priceLowHigh": "lohi",
    "priceHighLow": "hilo",
}

API_TEST_DATA = {
    "users": {
        "create": {"name": "Hassan Faal", "username": "hassanf", "email": "hassan@example.com"},
        "update": {"name": "Hassan Updated", "username": "hassanf"},
        "patch": {"name": "Hassan Patched"},
    },
    "posts": {
        "create": {"title": "API Testing with Pytest", "body": "Demonstrating API test automation", "userId": 1},
        "update": {"id": 1, "title": "Updated Title", "body": "Updated body", "userId": 1},
    },
}
