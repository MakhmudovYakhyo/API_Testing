package day09;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanTestBase;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P02_ResponseTimeTest extends SpartanTestBase {

    @DisplayName("GET /api/spartans to get response time")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .when().get("/api/spartans")
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .time(both(greaterThan(500L)).and(lessThan(1000L))).extract().response();

        System.out.println("response.getTimeIn(TimeUnit.MILLISECONDS) = " + response.getTimeIn(TimeUnit.MILLISECONDS));

        System.out.println("response.getTimeIn(TimeUnit.NANOSECONDS) = " + response.getTimeIn(TimeUnit.NANOSECONDS));

    }

}
