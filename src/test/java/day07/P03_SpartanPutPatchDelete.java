package day07;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanTestBase;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class P03_SpartanPutPatchDelete extends SpartanTestBase {

    @DisplayName("PUT Spartan Single Spartan with map")
    @Test
    public void test1(){

        Map<String, Object> requestedBody = new LinkedHashMap<>();
        requestedBody.put("name","Dua Lipa PUT");
        requestedBody.put("gender","Female");
        requestedBody.put("phone",12345678910L);

        // put to update existing ID
        int id = 97;

        JsonPath jsonPath = given()
                .contentType(ContentType.JSON)
                .pathParam("id",id)
                .body(requestedBody)
                .when().put("/api/spartans/{id}")
                .then().statusCode(204)
                .extract().jsonPath();// No connect --> I successfully but no response


    }

}
