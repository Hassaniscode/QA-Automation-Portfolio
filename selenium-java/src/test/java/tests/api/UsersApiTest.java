package tests.api;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import tests.TestData;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UsersApiTest extends BaseApiTest {

    @Test(description = "GET /users should return list of users")
    public void testGetUsers() {
        given()
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .body("$", hasSize(10))
            .body("[0].id", notNullValue())
            .body("[0].name", notNullValue())
            .body("[0].email", notNullValue())
            .body("[0].username", notNullValue());
    }

    @Test(description = "GET /users/:id should return a single user")
    public void testGetSingleUser() {
        given()
        .when()
            .get("/users/1")
        .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("name", notNullValue())
            .body("email", notNullValue());
    }

    @Test(description = "GET /users/:id should return 404 for non-existent user")
    public void testGetUserNotFound() {
        given()
        .when()
            .get("/users/9999")
        .then()
            .statusCode(404);
    }

    @Test(description = "POST /users should create a new user")
    public void testCreateUser() {
        String body = String.format("""
            {
                "name": "%s",
                "username": "%s",
                "email": "%s"
            }
            """, TestData.API_USER_NAME, TestData.API_USER_USERNAME, TestData.API_USER_EMAIL);

        given()
            .contentType(ContentType.JSON)
            .body(body)
        .when()
            .post("/users")
        .then()
            .statusCode(201)
            .body("name", equalTo(TestData.API_USER_NAME))
            .body("username", equalTo(TestData.API_USER_USERNAME))
            .body("email", equalTo(TestData.API_USER_EMAIL))
            .body("id", notNullValue());
    }

    @Test(description = "PUT /users/:id should update a user")
    public void testUpdateUser() {
        String body = String.format("""
            {
                "name": "%s",
                "username": "%s",
                "email": "%s"
            }
            """, TestData.API_USER_UPDATED_NAME, TestData.API_USER_USERNAME, TestData.API_USER_UPDATED_EMAIL);

        given()
            .contentType(ContentType.JSON)
            .body(body)
        .when()
            .put("/users/1")
        .then()
            .statusCode(200)
            .body("name", equalTo(TestData.API_USER_UPDATED_NAME));
    }

    @Test(description = "PATCH /users/:id should partially update a user")
    public void testPartialUpdateUser() {
        String body = String.format("""
            {
                "name": "%s"
            }
            """, TestData.API_USER_PATCHED_NAME);

        given()
            .contentType(ContentType.JSON)
            .body(body)
        .when()
            .patch("/users/1")
        .then()
            .statusCode(200)
            .body("name", equalTo(TestData.API_USER_PATCHED_NAME));
    }

    @Test(description = "DELETE /users/:id should delete a user")
    public void testDeleteUser() {
        given()
        .when()
            .delete("/users/1")
        .then()
            .statusCode(200);
    }
}
