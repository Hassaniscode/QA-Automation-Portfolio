from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webdriver import WebDriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


class InventoryPage:
    def __init__(self, driver: WebDriver):
        self.driver = driver
        self._cart_badge = (By.CSS_SELECTOR, ".shopping_cart_badge")
        self._cart_link = (By.CSS_SELECTOR, ".shopping_cart_link")
        self._menu_button = (By.ID, "react-burger-menu-btn")
        self._logout_link = (By.ID, "logout_sidebar_link")

    def add_item_to_cart(self, item_name: str):
        item = self.driver.find_element(
            By.XPATH, f"//div[@class='inventory_item' and .//div[text()='{item_name}']]"
        )
        item.find_element(By.CSS_SELECTOR, "button").click()

    def remove_item_from_cart(self, item_name: str):
        item = self.driver.find_element(
            By.XPATH, f"//div[@class='inventory_item' and .//div[text()='{item_name}']]"
        )
        item.find_element(By.CSS_SELECTOR, "button").click()

    def go_to_cart(self):
        self.driver.find_element(*self._cart_link).click()

    def get_cart_badge_count(self) -> str:
        return self.driver.find_element(*self._cart_badge).text

    def is_cart_badge_visible(self) -> bool:
        return len(self.driver.find_elements(*self._cart_badge)) > 0

    def logout(self):
        self.driver.find_element(*self._menu_button).click()
        wait = WebDriverWait(self.driver, 10)
        logout_link = wait.until(EC.visibility_of_element_located(self._logout_link))
        self.driver.execute_script("arguments[0].click();", logout_link)
