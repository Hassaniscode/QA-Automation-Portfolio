package tests.api;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseApiTest {

    @BeforeClass
    public void setupApi() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }
}
