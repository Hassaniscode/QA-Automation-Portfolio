from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webdriver import WebDriver


class CartPage:
    def __init__(self, driver: WebDriver):
        self.driver = driver
        self._checkout_button = (By.CSS_SELECTOR, '[data-test="checkout"]')
        self._cart_items = (By.CSS_SELECTOR, ".cart_item")

    def get_cart_item_count(self) -> int:
        return len(self.driver.find_elements(*self._cart_items))

    def proceed_to_checkout(self):
        self.driver.find_element(*self._checkout_button).click()
