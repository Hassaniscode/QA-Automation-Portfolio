from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webdriver import WebDriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


class CheckoutPage:
    def __init__(self, driver: WebDriver):
        self.driver = driver
        self._first_name = (By.CSS_SELECTOR, '[data-test="firstName"]')
        self._last_name = (By.CSS_SELECTOR, '[data-test="lastName"]')
        self._postal_code = (By.CSS_SELECTOR, '[data-test="postalCode"]')
        self._continue_button = (By.CSS_SELECTOR, '[data-test="continue"]')
        self._finish_button = (By.CSS_SELECTOR, '[data-test="finish"]')
        self._confirmation_header = (By.CSS_SELECTOR, ".complete-header")

    def _set_react_input(self, selector: str, value: str):
        self.driver.execute_script(
            """
            var el = document.querySelector(arguments[0]);
            var setter = Object.getOwnPropertyDescriptor(
                window.HTMLInputElement.prototype, 'value'
            ).set;
            setter.call(el, arguments[1]);
            el.dispatchEvent(new Event('input', { bubbles: true }));
            el.dispatchEvent(new Event('change', { bubbles: true }));
            """,
            selector,
            value,
        )

    def fill_shipping_info(self, first_name: str, last_name: str, postal_code: str):
        wait = WebDriverWait(self.driver, 5)
        wait.until(EC.visibility_of_element_located(self._first_name))
        self._set_react_input('[data-test="firstName"]', first_name)
        self._set_react_input('[data-test="lastName"]', last_name)
        self._set_react_input('[data-test="postalCode"]', postal_code)
        self.driver.execute_script(
            "document.querySelector('[data-test=\"continue\"]').closest('form').requestSubmit();"
        )
        wait.until(EC.url_contains("checkout-step-two"))

    def finish(self):
        wait = WebDriverWait(self.driver, 5)
        finish_btn = wait.until(EC.element_to_be_clickable(self._finish_button))
        self.driver.execute_script("arguments[0].click();", finish_btn)

    def get_confirmation_text(self) -> str:
        wait = WebDriverWait(self.driver, 5)
        wait.until(EC.visibility_of_element_located(self._confirmation_header))
        return self.driver.find_element(*self._confirmation_header).text
