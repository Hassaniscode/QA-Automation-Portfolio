import requests
from tests.constants import URLS, API_TEST_DATA

BASE_URL = URLS["api"]


class TestUsersApi:
    def test_get_users(self):
        response = requests.get(f"{BASE_URL}/users")
        assert response.status_code == 200
        data = response.json()
        assert len(data) == 10
        assert "id" in data[0]
        assert "name" in data[0]
        assert "email" in data[0]

    def test_get_single_user(self):
        response = requests.get(f"{BASE_URL}/users/1")
        assert response.status_code == 200
        data = response.json()
        assert data["id"] == 1
        assert data["name"]

    def test_get_user_not_found(self):
        response = requests.get(f"{BASE_URL}/users/9999")
        assert response.status_code == 404

    def test_create_user(self):
        new_user = API_TEST_DATA["users"]["create"]
        response = requests.post(f"{BASE_URL}/users", json=new_user)
        assert response.status_code == 201
        data = response.json()
        assert data["name"] == new_user["name"]
        assert data["id"]

    def test_update_user(self):
        updated_user = API_TEST_DATA["users"]["update"]
        response = requests.put(f"{BASE_URL}/users/1", json=updated_user)
        assert response.status_code == 200
        assert response.json()["name"] == updated_user["name"]

    def test_patch_user(self):
        patch_data = API_TEST_DATA["users"]["patch"]
        response = requests.patch(f"{BASE_URL}/users/1", json=patch_data)
        assert response.status_code == 200
        assert response.json()["name"] == patch_data["name"]

    def test_delete_user(self):
        response = requests.delete(f"{BASE_URL}/users/1")
        assert response.status_code == 200
