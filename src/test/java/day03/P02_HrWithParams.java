package day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class P02_HrWithParams {

    @DisplayName("GET request /countries with regionID")
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON).queryParam("q", "{\"region_id\":2}").when().get("/countries");

        response.prettyPrint();

        // Status code is 200
        assertEquals(200,response.getStatusCode());
        // Content type is application/json
        assertEquals("application/json",response.contentType());
        // Payload should contain "United States of America"
        assertTrue(response.body().asString().contains("United States of America"));
    }

}
