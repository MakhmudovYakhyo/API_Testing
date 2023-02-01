package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class P01_SpartanGetRequest {

    String url = "http://3.80.111.193:8000";

    /*
     * Given to accept is application/json
     * When user sends GET request /api/spartans endpoint
     * Then status code should be 200
     * And Content type should be application/json
     */

    @Test
    public void getAllSpartans() {
        Response response = RestAssured.given().accept(ContentType.JSON).when().get(url + "/api/spartans");

        response.prettyPrint();

        int actualStatusCode = response.statusCode();

        Assertions.assertEquals(200, actualStatusCode);

        String actualContentType = response.contentType();

        Assertions.assertEquals("application/json", actualContentType);

        String connection = response.header("Connection");
        System.out.println("connection = " + connection);

        System.out.println("response.header(\"Content-Type\") = " + response.header("Content-Type"));

        // get Date header
        System.out.println("response.header(\"Date\") = " + response.header("Date"));

        boolean isDateExist = response.headers().hasHeaderWithName("Date");
        Assertions.assertTrue(isDateExist);

    }

    @DisplayName("GET Single Name")
    @Test
    public void getSpartan() {
        Response response = RestAssured.given().accept(ContentType.JSON).when().get(url + "/api/spartans/3");

        response.prettyPrint();

        // Verify status code
        int actualStatusCode = response.statusCode();

        Assertions.assertEquals(200, actualStatusCode);

        // Verify content type
        String actualContentType = response.contentType();

        Assertions.assertEquals("application/json", actualContentType);
        Assertions.assertEquals("application/json", response.header("Content-Type"));
        Assertions.assertEquals(ContentType.JSON.toString(), response.header("Content-Type"));

        // Verify body contains Fidole
        Assertions.assertTrue(response.body().asString().contains("Fidole"));

        //What if we have typo while we are getting header
        System.out.println("response.header(\"KeepAlive\") = " + response.header("Keep-Alive"));

    }

    /*
     Given no headers provided
     When Users send GET request to /api/hello
     Then response status code should be 200
     And Content header should be "text/plain;charset=UTF-8"
     And header should contain Date
     And Content-Length should be 17
     And body should be "Hello from Sparta"
     */
    @DisplayName("GET Hello Name")
    @Test
    public void helloSpartan() {
        Response response = RestAssured.when().get(url + "/api/hello");

        Assertions.assertEquals(200, response.getStatusCode());

        Assertions.assertEquals("text/plain;charset=UTF-8", response.contentType());

        Assertions.assertTrue( response.headers().hasHeaderWithName("Date"));

        Assertions.assertEquals( "17",response.header("Content-Length"));

        Assertions.assertTrue( response.body().asString().contains("Hello from Sparta"));
    }


}
