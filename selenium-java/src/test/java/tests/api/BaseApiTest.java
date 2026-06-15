package tests.api;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import tests.TestData;

public class BaseApiTest {

    @BeforeClass
    public void setupApi() {
        RestAssured.baseURI = TestData.API_BASE_URL;
    }
}
