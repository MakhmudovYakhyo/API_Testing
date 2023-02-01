package day06;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Search;
import pojo.Spartan;
import utilities.SpartanTestBase;

import java.util.List;

import static io.restassured.RestAssured.given;

public class P02_SpartanDeserializationPOJO extends SpartanTestBase {


    @DisplayName("GET Single Spartan for deserialization to POJO")
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}").prettyPeek()
                .then().statusCode(200).extract().response();



        /*{
           "id": 15,
           "name": "Meta",
           "gender": "Female",
           "phone": 1938695106
        }*/

        // RESPONSE
        System.out.println("===== RESPONSE =====");

        Spartan spartan = response.as(Spartan.class);
        System.out.println("spartan = " + spartan);

        System.out.println("spartan.getId() = " + spartan.getId());
        System.out.println("spartan.getName() = " + spartan.getName());
        System.out.println("spartan.getGender() = " + spartan.getGender());
        System.out.println("spartan.getPhone() = " + spartan.getPhone());

        // JSONPATH
        System.out.println("===== JSON =====");

        JsonPath jsonPath = response.jsonPath();
        Spartan spartan1 = jsonPath.getObject("", Spartan.class);

        System.out.println("spartan1 = " + spartan1);
        System.out.println("spartan1.getId() = " + spartan1.getId());
        System.out.println("spartan1.getName() = " + spartan1.getName());
        System.out.println("spartan1.getGender() = " + spartan1.getGender());
        System.out.println("spartan1.getPhone() = " + spartan1.getPhone());

    }

    @DisplayName("GET Spartans from endpoint for deserialization to POJO ")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON).
                when().get("/api/spartans/search").prettyPeek()
                .then().
                statusCode(200).extract().response();

        // RESPONSE
        System.out.println(" ---- RESPONSE ----");
        // response.as() --> since we can not put path in here to get


        // JSONPATH
        System.out.println(" ---- JSON ----");
        JsonPath jsonPath = response.jsonPath();

        Spartan spartan = jsonPath.getObject("content[0]", Spartan.class);
        System.out.println("spartan = " + spartan);

    }

    @DisplayName("GET Spartans from search endpoint for deserialization to Search class")
    @Test
    public void test3() {
        Response response = given().accept(ContentType.JSON).
                when().get("/api/spartans/search").prettyPeek()
                .then().
                statusCode(200).extract().response();

        // RESPONSE
        System.out.println(" ---- RESPONSE ----");
        Search search1 = response.as(Search.class);

        // since we are not providing path for response still we can use response.as() to make deserialization

        // JSONPATH
        System.out.println(" ---- JSON ----");
        JsonPath jsonPath = response.jsonPath();

        Search search = jsonPath.getObject("", Search.class);

        System.out.println(search.getTotalElement());
        System.out.println("search.getContent().get(0) = " + search.getContent().get(0));
        System.out.println("search.getContent().get(0).getName() = " + search.getContent().get(0).getName());
        System.out.println("search.getContent().get(0).getId() = " + search.getContent().get(0).getId());


    }


    @DisplayName("GET Spartans from search endpoint for deserialization to list of Spartan class")
    @Test
    public void test4(){

        Response response = given().accept(ContentType.JSON).
                when().get("/api/spartans/search").prettyPeek()
                .then().
                statusCode(200).extract().response();

        JsonPath jsonPath = response.jsonPath();

        List<Spartan> allSpartans = jsonPath.getList("content",Spartan.class);
        for (Spartan eachSpartan : allSpartans) {
            System.out.println("eachSpartan = " + eachSpartan);
        }

        System.out.println("allSpartans.get(0) = " + allSpartans.get(0));
        System.out.println("allSpartans.get(0).getId() = " + allSpartans.get(0).getId());

    }

}
