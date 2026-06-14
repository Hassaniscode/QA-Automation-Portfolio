from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webdriver import WebDriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


class CartPage:
    def __init__(self, driver: WebDriver):
        self.driver = driver
        self._checkout_button = (By.CSS_SELECTOR, '[data-test="checkout"]')
        self._cart_items = (By.CSS_SELECTOR, ".cart_item")

    def get_cart_item_count(self) -> int:
        wait = WebDriverWait(self.driver, 10)
        wait.until(EC.presence_of_element_located(self._cart_items))
        return len(self.driver.find_elements(*self._cart_items))

    def proceed_to_checkout(self):
        wait = WebDriverWait(self.driver, 10)
        btn = wait.until(EC.element_to_be_clickable(self._checkout_button))
        self.driver.execute_script("arguments[0].click();", btn)
        wait.until(EC.url_contains("checkout-step-one"))
