package tests.api;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsApiTest extends BaseApiTest {

    @Test(description = "GET /posts should return all posts")
    public void testGetPosts() {
        given()
        .when()
            .get("/posts")
        .then()
            .statusCode(200)
            .body("$", hasSize(100))
            .body("[0].userId", notNullValue())
            .body("[0].id", notNullValue())
            .body("[0].title", notNullValue())
            .body("[0].body", notNullValue());
    }

    @Test(description = "GET /posts?userId=1 should filter posts by user")
    public void testGetPostsByUser() {
        given()
            .queryParam("userId", 1)
        .when()
            .get("/posts")
        .then()
            .statusCode(200)
            .body("$", hasSize(greaterThan(0)))
            .body("userId", everyItem(equalTo(1)));
    }

    @Test(description = "GET /posts/:id/comments should return comments for a post")
    public void testGetPostComments() {
        given()
        .when()
            .get("/posts/1/comments")
        .then()
            .statusCode(200)
            .body("$", hasSize(greaterThan(0)))
            .body("[0].postId", equalTo(1))
            .body("[0].email", notNullValue())
            .body("[0].body", notNullValue());
    }

    @Test(description = "POST /posts should create a new post")
    public void testCreatePost() {
        String body = """
            {
                "title": "API Testing with REST Assured",
                "body": "Demonstrating API test automation",
                "userId": 1
            }
            """;

        given()
            .contentType(ContentType.JSON)
            .body(body)
        .when()
            .post("/posts")
        .then()
            .statusCode(201)
            .body("title", equalTo("API Testing with REST Assured"))
            .body("userId", equalTo(1))
            .body("id", notNullValue());
    }

    @Test(description = "PUT /posts/:id should replace a post")
    public void testUpdatePost() {
        String body = """
            {
                "id": 1,
                "title": "Updated Title",
                "body": "Updated body content",
                "userId": 1
            }
            """;

        given()
            .contentType(ContentType.JSON)
            .body(body)
        .when()
            .put("/posts/1")
        .then()
            .statusCode(200)
            .body("title", equalTo("Updated Title"))
            .body("body", equalTo("Updated body content"));
    }

    @Test(description = "DELETE /posts/:id should delete a post")
    public void testDeletePost() {
        given()
        .when()
            .delete("/posts/1")
        .then()
            .statusCode(200);
    }
}
