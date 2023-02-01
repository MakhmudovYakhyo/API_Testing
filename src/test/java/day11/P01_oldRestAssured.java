package day11;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import utilities.SpartanNewTestBase;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class P01_oldRestAssured extends SpartanNewTestBase {

    @Test
    public void getAllSpartans(){
        given().accept(ContentType.JSON)
                .auth().basic("admin","admin")
                .when().get("/spartans")
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]",is(101))
                .body("id[1]",is(102));
    }

    @Test
    public void getAllSpartansOldWay(){
        given().accept(ContentType.JSON)
                .auth().basic("admin","admin")
                .expect().statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]",is(101))
                .body("id[1]",is(102))
                .when().get("/spartans");
    }



}
