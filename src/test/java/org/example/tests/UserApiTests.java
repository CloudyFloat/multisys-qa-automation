package org.example.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserApiTests {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test(priority = 1)
    public void testGetUsersSuccess() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body(".", is(instanceOf(java.util.List.class)))
                .body("size()", greaterThan(0))
                .extract().response();

        System.out.println("=== TEST 1: GET Users Success ===");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Total Users Retrieved: " + response.jsonPath().getList("id").size());
        System.out.println("First User Name: " + response.jsonPath().getString("[0].name"));
        System.out.println("Validation Result: PASSED\n");
    }

    @Test(priority = 2)
    public void testGetSingleUser() {
        int userId = 1;
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/users/" + userId)
                .then()
                .statusCode(200)
                .body("id", equalTo(userId))
                .extract().response();

        System.out.println("=== TEST 2: GET Single Resource ===");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Fetched User ID: " + response.jsonPath().getInt("id"));
        System.out.println("Fetched User Name: " + response.jsonPath().getString("name"));
        System.out.println("Fetched User Email: " + response.jsonPath().getString("email"));
        System.out.println("Validation Result: PASSED\n");
    }

    @Test(priority = 3)
    public void testCreateUser() {
        String requestBody = "{\n" +
                "  \"name\": \"Byron Arcega\",\n" +
                "  \"username\": \"byronqa\",\n" +
                "  \"email\": \"byron.arcega@example.com\"\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .extract().response();

        System.out.println("=== TEST 3: POST Data Creation ===");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Generated Resource ID: " + response.jsonPath().getString("id"));
        System.out.println("Created Name: " + response.jsonPath().getString("name"));
        System.out.println("Created Username: " + response.jsonPath().getString("username"));
        System.out.println("Validation Result: PASSED\n");
    }
}