import pytest
from pages.login_page import LoginPage
from pages.inventory_page import InventoryPage
from pages.cart_page import CartPage
from pages.checkout_page import CheckoutPage
from tests.constants import USERS, ITEMS, CHECKOUT


@pytest.fixture
def inventory_page(driver):
    login_page = LoginPage(driver)
    login_page.login(USERS["standard"]["username"], USERS["standard"]["password"])
    from selenium.webdriver.support.ui import WebDriverWait
    from selenium.webdriver.support import expected_conditions as EC
    WebDriverWait(driver, 10).until(EC.url_contains("inventory"))
    return InventoryPage(driver)


class TestCart:
    def test_add_item_to_cart(self, driver, inventory_page):
        inventory_page.add_item_to_cart(ITEMS["backpack"])
        assert inventory_page.is_cart_badge_visible()
        assert inventory_page.get_cart_badge_count() == "1"

    def test_remove_item_from_cart(self, driver, inventory_page):
        inventory_page.add_item_to_cart(ITEMS["backpack"])
        inventory_page.remove_item_from_cart(ITEMS["backpack"])
        assert not inventory_page.is_cart_badge_visible()

    def test_add_multiple_items(self, driver, inventory_page):
        inventory_page.add_item_to_cart(ITEMS["backpack"])
        inventory_page.add_item_to_cart(ITEMS["bikeLight"])
        assert inventory_page.get_cart_badge_count() == "2"

    @pytest.mark.parametrize(
        "item_name",
        [ITEMS["backpack"], ITEMS["bikeLight"], ITEMS["tShirt"]],
    )
    def test_add_item_shows_badge(self, driver, inventory_page, item_name):
        inventory_page.add_item_to_cart(item_name)
        assert inventory_page.get_cart_badge_count() == "1"


class TestCheckout:
    def test_full_checkout_flow(self, driver, inventory_page):
        inventory_page.add_item_to_cart(ITEMS["backpack"])
        inventory_page.go_to_cart()

        cart_page = CartPage(driver)
        assert cart_page.get_cart_item_count() == 1
        cart_page.proceed_to_checkout()

        checkout_page = CheckoutPage(driver)
        checkout_page.fill_shipping_info(CHECKOUT["firstName"], CHECKOUT["lastName"], CHECKOUT["postalCode"])
        checkout_page.finish()

        assert checkout_page.get_confirmation_text() == CHECKOUT["confirmationMessage"]


class TestLogout:
    def test_logout_redirects_to_login(self, driver, inventory_page):
        inventory_page.logout()
        from selenium.webdriver.support.ui import WebDriverWait
        from selenium.webdriver.support import expected_conditions as EC
        from selenium.webdriver.common.by import By
        WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.CSS_SELECTOR, '[data-test="login-button"]'))
        )
