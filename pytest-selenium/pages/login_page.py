from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webdriver import WebDriver


class LoginPage:
    def __init__(self, driver: WebDriver):
        self.driver = driver
        self._username = (By.CSS_SELECTOR, '[data-test="username"]')
        self._password = (By.CSS_SELECTOR, '[data-test="password"]')
        self._login_button = (By.CSS_SELECTOR, '[data-test="login-button"]')
        self._error_message = (By.CSS_SELECTOR, '[data-test="error"]')

    def login(self, username: str, password: str):
        self.driver.find_element(*self._username).clear()
        self.driver.find_element(*self._username).send_keys(username)
        self.driver.find_element(*self._password).clear()
        self.driver.find_element(*self._password).send_keys(password)
        self.driver.find_element(*self._login_button).click()

    def get_error_message(self) -> str:
        return self.driver.find_element(*self._error_message).text

    def is_error_displayed(self) -> bool:
        return len(self.driver.find_elements(*self._error_message)) > 0
