import pytest
from pages.login_page import LoginPage
from tests.constants import USERS, VALID_USERNAMES, ERROR_MESSAGES


class TestLogin:
    def test_valid_login(self, driver):
        login_page = LoginPage(driver)
        login_page.login(USERS["standard"]["username"], USERS["standard"]["password"])
        assert "inventory" in driver.current_url

    @pytest.mark.parametrize(
        "username, password, expected_error",
        [
            (USERS["invalid"]["username"], USERS["invalid"]["password"], ERROR_MESSAGES["invalidCredentials"]),
            (USERS["locked"]["username"], USERS["locked"]["password"], ERROR_MESSAGES["lockedOut"]),
            ("", USERS["standard"]["password"], ERROR_MESSAGES["usernameRequired"]),
            (USERS["standard"]["username"], "", ERROR_MESSAGES["passwordRequired"]),
            ("", "", ERROR_MESSAGES["usernameRequired"]),
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

    @pytest.mark.parametrize("username", VALID_USERNAMES)
    def test_valid_users_reach_inventory(self, driver, username):
        login_page = LoginPage(driver)
        login_page.login(username, USERS["standard"]["password"])
        assert "inventory" in driver.current_url
