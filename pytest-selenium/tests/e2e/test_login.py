import pytest
from pages.login_page import LoginPage
from conftest import STANDARD_USER, LOCKED_USER, PASSWORD


class TestLogin:
    def test_valid_login(self, driver):
        login_page = LoginPage(driver)
        login_page.login(STANDARD_USER, PASSWORD)
        assert "inventory" in driver.current_url

    @pytest.mark.parametrize(
        "username, password, expected_error",
        [
            ("wrong_user", "wrong_pass", "Username and password do not match"),
            ("locked_out_user", "secret_sauce", "locked out"),
            ("", "secret_sauce", "Username is required"),
            ("standard_user", "", "Password is required"),
            ("", "", "Username is required"),
        ],
        ids=[
            "invalid_credentials",
            "locked_out_user",
            "empty_username",
            "empty_password",
            "both_fields_empty",
        ],
    )
    def test_invalid_login(self, driver, username, password, expected_error):
        login_page = LoginPage(driver)
        login_page.login(username, password)
        assert login_page.is_error_displayed()
        assert expected_error in login_page.get_error_message()

    @pytest.mark.parametrize(
        "username",
        ["standard_user", "problem_user", "performance_glitch_user"],
    )
    def test_valid_users_reach_inventory(self, driver, username):
        login_page = LoginPage(driver)
        login_page.login(username, PASSWORD)
        assert "inventory" in driver.current_url
