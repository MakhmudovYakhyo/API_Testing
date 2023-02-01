package day03;

import groovy.json.JsonParser;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanTestBase;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P05_SpartanWithJsonPath extends SpartanTestBase {

    /*
     Given accept type is json
     And path param id is 10
     When user sends a get request to "api/spartans/{id}"
     Then status code is 200
     And content-type is "application/json"
     And response payload values match the following:
          id is 10,
          name is "Lorenza",
          gender is "Female",
          phone is 3312820936
   */
    @DisplayName("GET spartan with Response Path")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 10).
                when().get("/api/spartans/{id}");

        //Then status code is 200
        assertEquals(200, response.statusCode());

        System.out.println(response.path("id").toString());

        JsonPath jsonPath = response.jsonPath();

        // is it possible to get status code / content type / headers with jsonPath?
        // if you want to do assertion for then still we need to use response Object

        System.out.println("jsonPath.getInt(\"id\") = " + jsonPath.getInt("id"));

        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");

        assertEquals(10, id);
        assertEquals("Lorenza", name);
        assertEquals("Female", gender);
        assertEquals(3312820936l, phone);

    }


}
