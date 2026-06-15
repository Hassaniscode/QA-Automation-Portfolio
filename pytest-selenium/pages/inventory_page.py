from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webdriver import WebDriver
from selenium.webdriver.support.ui import WebDriverWait, Select
from selenium.webdriver.support import expected_conditions as EC


class InventoryPage:
    def __init__(self, driver: WebDriver):
        self.driver = driver
        self._cart_badge = (By.CSS_SELECTOR, ".shopping_cart_badge")
        self._cart_link = (By.CSS_SELECTOR, ".shopping_cart_link")
        self._menu_button = (By.ID, "react-burger-menu-btn")
        self._logout_link = (By.ID, "logout_sidebar_link")
        self._sort_dropdown = (By.CSS_SELECTOR, '[data-test="product-sort-container"]')
        self._item_names = (By.CSS_SELECTOR, ".inventory_item_name")
        self._item_prices = (By.CSS_SELECTOR, ".inventory_item_price")

    def _item_slug(self, item_name: str) -> str:
        return item_name.lower().replace(" ", "-")

    def add_item_to_cart(self, item_name: str):
        slug = self._item_slug(item_name)
        add_locator = (By.CSS_SELECTOR, f'[data-test="add-to-cart-{slug}"]')
        remove_locator = (By.CSS_SELECTOR, f'[data-test="remove-{slug}"]')
        wait = WebDriverWait(self.driver, 10)
        btn = wait.until(EC.element_to_be_clickable(add_locator))
        self.driver.execute_script("arguments[0].click();", btn)
        wait.until(EC.presence_of_element_located(remove_locator))

    def remove_item_from_cart(self, item_name: str):
        slug = self._item_slug(item_name)
        locator = (By.CSS_SELECTOR, f'[data-test="remove-{slug}"]')
        wait = WebDriverWait(self.driver, 10)
        btn = wait.until(EC.element_to_be_clickable(locator))
        self.driver.execute_script("arguments[0].click();", btn)

    def go_to_cart(self):
        cart = self.driver.find_element(*self._cart_link)
        self.driver.execute_script("arguments[0].click();", cart)
        WebDriverWait(self.driver, 10).until(EC.url_contains("cart"))

    def get_cart_badge_count(self) -> str:
        return self.driver.find_element(*self._cart_badge).text

    def is_cart_badge_visible(self) -> bool:
        return len(self.driver.find_elements(*self._cart_badge)) > 0

    def sort_by(self, option: str):
        dropdown = self.driver.find_element(*self._sort_dropdown)
        Select(dropdown).select_by_value(option)

    def get_product_names(self) -> list[str]:
        elements = self.driver.find_elements(*self._item_names)
        return [el.text for el in elements]

    def get_product_prices(self) -> list[float]:
        elements = self.driver.find_elements(*self._item_prices)
        return [float(el.text.replace("$", "")) for el in elements]

    def logout(self):
        self.driver.find_element(*self._menu_button).click()
        wait = WebDriverWait(self.driver, 10)
        logout_link = wait.until(EC.visibility_of_element_located(self._logout_link))
        self.driver.execute_script("arguments[0].click();", logout_link)
