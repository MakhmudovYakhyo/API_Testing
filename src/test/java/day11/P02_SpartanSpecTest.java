package day11;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import utilities.SpartanNewTestBase;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class P02_SpartanSpecTest extends SpartanNewTestBase {

    @Test
    public void getAllSpartans(){
        given().log().all().accept(ContentType.JSON)
                .auth().basic("admin","admin")
                .when().get("/spartans")
                .then().statusCode(200)
                .contentType(ContentType.JSON);
    }


    @Test
    public void getAllSpartansWithReqResSpec(){
        RequestSpecification reqSpec = given().log().all().accept(ContentType.JSON)
                .auth().basic("admin", "admin");

        ResponseSpecification respSpec = expect().statusCode(200)
                .contentType(ContentType.JSON);

        given().spec(reqSpec)
                .when().get("/spartans")
                .then().spec(respSpec);
    }

    @Test
    public void getSingleSpartanWithReqSpec(){
        given().spec(reqSpec)
                .pathParam("id",3)
                .when().get("/spartans/{id}").prettyPeek()
                .then().spec(respSpec)
                .body("id",is(3));
    }

    @Test
    public void getSingleSpartanAsUser(){
        given().spec(dynamicReqSpec("user","user"))
                .pathParam("id",3)
                .when().get("/spartans/{id}").prettyPeek()
                .then().spec(dynamicResSpec(200));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/GET_RBAC.csv",numLinesToSkip = 1)
    public void getSingleSpartan_GETRBACS(String user,String pass,int id,int statusCode){
        given().spec(dynamicReqSpec(user,pass))
                .pathParam("id",id)
                .when().delete("/spartan/{id}").prettyPeek()
                .then().spec(dynamicResSpec(statusCode));

    }

}
