import pytest
from pages.login_page import LoginPage
from pages.inventory_page import InventoryPage
from tests.constants import USERS, SORT_OPTIONS


@pytest.fixture
def inventory_page(driver):
    login_page = LoginPage(driver)
    login_page.login(USERS["standard"]["username"], USERS["standard"]["password"])
    from selenium.webdriver.support.ui import WebDriverWait
    from selenium.webdriver.support import expected_conditions as EC
    WebDriverWait(driver, 10).until(EC.url_contains("inventory"))
    return InventoryPage(driver)


class TestProductSort:
    def test_sort_name_a_to_z(self, driver, inventory_page):
        inventory_page.sort_by(SORT_OPTIONS["nameAZ"])
        names = inventory_page.get_product_names()
        assert names == sorted(names)

    def test_sort_name_z_to_a(self, driver, inventory_page):
        inventory_page.sort_by(SORT_OPTIONS["nameZA"])
        names = inventory_page.get_product_names()
        assert names == sorted(names, reverse=True)

    def test_sort_price_low_to_high(self, driver, inventory_page):
        inventory_page.sort_by(SORT_OPTIONS["priceLowHigh"])
        prices = inventory_page.get_product_prices()
        assert prices == sorted(prices)

    def test_sort_price_high_to_low(self, driver, inventory_page):
        inventory_page.sort_by(SORT_OPTIONS["priceHighLow"])
        prices = inventory_page.get_product_prices()
        assert prices == sorted(prices, reverse=True)
