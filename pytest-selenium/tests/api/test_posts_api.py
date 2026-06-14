import requests

BASE_URL = "https://jsonplaceholder.typicode.com"


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
        response = requests.post(
            f"{BASE_URL}/posts",
            json={
                "title": "API Testing with Pytest",
                "body": "Demonstrating API test automation",
                "userId": 1,
            },
        )
        assert response.status_code == 201
        data = response.json()
        assert data["title"] == "API Testing with Pytest"
        assert data["id"]

    def test_update_post(self):
        response = requests.put(
            f"{BASE_URL}/posts/1",
            json={"id": 1, "title": "Updated Title", "body": "Updated body", "userId": 1},
        )
        assert response.status_code == 200
        assert response.json()["title"] == "Updated Title"

    def test_delete_post(self):
        response = requests.delete(f"{BASE_URL}/posts/1")
        assert response.status_code == 200
