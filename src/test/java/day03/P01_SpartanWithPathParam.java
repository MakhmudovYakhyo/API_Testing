package day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanTestBase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class P01_SpartanWithPathParam extends SpartanTestBase {

    /*   Given to accept type is Json
      And ID parameter value is 24
      When user sends GET request to /api/spartans/{id}
      Then response status code should be 200
      And response content-type: application/json
      And "Blythe" should be in response payload(body)
   */
    @DisplayName("Get Spartan /api/spartans/{id} with path param 24")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON).and().pathParam("id", 24).when().get("/api/spartans/{id}");
        response.prettyPrint();

        // Response status code should be 200
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        // Response content type: application/json
        assertEquals("application/json", response.contentType());
        // Julio should be in response payload(body)
        assertTrue(response.body().asString().contains("Julio"));

    }


    @DisplayName("Get Spartan /api/spartans/{id} with invalid id")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON).and().pathParam("id", 500).when().get("/api/spartans/{id}");
        response.prettyPrint();

        // Response status code should be 200
        assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());
        // Response content type: application/json
        assertEquals("application/json", response.contentType());
        // Julio should be in response payload(body)
        assertTrue(response.body().asString().contains("Not Found"));

    }

    /*
        Given Accept type is Json
        And query parameter values are:
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */
    @DisplayName("Get request /api/spartans/search with Query Param")
    @Test
    public void test3() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "e")
                .when().get("/api/spartans/search");

        // Response status code should be 200
        assertEquals(200,response.statusCode());
        // Response content type: application/json
        assertEquals("application/json",response.contentType());
        // Female should be in response payload(body)
        assertTrue(response.body().asString().contains("Female"));
        // Janette should be in response payload(body)
        assertTrue(response.body().asString().contains("Janette"));
    }


    @DisplayName("Get request /api/spartans/search with Query Param")
    @Test
    public void test4() {

        Map<String, Object> queryMaps = new HashMap<>();
        queryMaps.put("gender","Female");
        queryMaps.put("nameContains","e");

        Response response = given().accept(ContentType.JSON).queryParams(queryMaps).when().get("/api/spartans/search");

        response.prettyPrint();

        // Response status code should be 200
        assertEquals(200,response.statusCode());
        // Response content type: application/json
        assertEquals("application/json",response.contentType());

    }

}
