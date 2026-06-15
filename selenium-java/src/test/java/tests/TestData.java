package tests;

public final class TestData {

    private TestData() {}

    // URLs
    public static final String BASE_URL = "https://www.saucedemo.com";
    public static final String API_BASE_URL = "https://jsonplaceholder.typicode.com";

    // Credentials
    public static final String STANDARD_USER = "standard_user";
    public static final String LOCKED_USER = "locked_out_user";
    public static final String PASSWORD = "secret_sauce";

    // Valid users for data-driven tests
    public static final String[] VALID_USERNAMES = {
        "standard_user", "problem_user", "performance_glitch_user"
    };

    // Product names
    public static final String ITEM_BACKPACK = "Sauce Labs Backpack";
    public static final String ITEM_BIKE_LIGHT = "Sauce Labs Bike Light";
    public static final String ITEM_TSHIRT = "Sauce Labs Bolt T-Shirt";

    // Error messages
    public static final String ERROR_INVALID_CREDENTIALS = "Username and password do not match";
    public static final String ERROR_LOCKED_OUT = "locked out";
    public static final String ERROR_USERNAME_REQUIRED = "Username is required";
    public static final String ERROR_PASSWORD_REQUIRED = "Password is required";

    // Checkout form data
    public static final String CHECKOUT_FIRST_NAME = "Hassan";
    public static final String CHECKOUT_LAST_NAME = "Faal";
    public static final String CHECKOUT_POSTAL_CODE = "75013";
    public static final String CHECKOUT_CONFIRMATION = "Thank you for your order!";

    // API test data - Users
    public static final String API_USER_NAME = "Hassan Faal";
    public static final String API_USER_USERNAME = "hassanf";
    public static final String API_USER_EMAIL = "hassan@example.com";
    public static final String API_USER_UPDATED_NAME = "Hassan Updated";
    public static final String API_USER_UPDATED_EMAIL = "hassan.updated@example.com";
    public static final String API_USER_PATCHED_NAME = "Hassan Patched";

    // API test data - Posts
    public static final String API_POST_TITLE = "API Testing with REST Assured";
    public static final String API_POST_BODY = "Demonstrating API test automation";
    public static final String API_POST_UPDATED_TITLE = "Updated Title";
    public static final String API_POST_UPDATED_BODY = "Updated body content";
}
