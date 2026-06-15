import requests
from tests.constants import URLS, API_TEST_DATA

BASE_URL = URLS["api"]


class TestPostsApi:
    def test_get_posts(self):
        response = requests.get(f"{BASE_URL}/posts")
        assert response.status_code == 200
        data = response.json()
        assert len(data) == 100
        assert "userId" in data[0]
        assert "title" in data[0]

    def test_get_posts_by_user(self):
        response = requests.get(f"{BASE_URL}/posts", params={"userId": 1})
        assert response.status_code == 200
        data = response.json()
        assert len(data) > 0
        assert all(post["userId"] == 1 for post in data)

    def test_get_post_comments(self):
        response = requests.get(f"{BASE_URL}/posts/1/comments")
        assert response.status_code == 200
        data = response.json()
        assert len(data) > 0
        assert data[0]["postId"] == 1

    def test_create_post(self):
        new_post = API_TEST_DATA["posts"]["create"]
        response = requests.post(f"{BASE_URL}/posts", json=new_post)
        assert response.status_code == 201
        data = response.json()
        assert data["title"] == new_post["title"]
        assert data["id"]

    def test_update_post(self):
        updated_post = API_TEST_DATA["posts"]["update"]
        response = requests.put(f"{BASE_URL}/posts/1", json=updated_post)
        assert response.status_code == 200
        assert response.json()["title"] == updated_post["title"]

    def test_delete_post(self):
        response = requests.delete(f"{BASE_URL}/posts/1")
        assert response.status_code == 200
