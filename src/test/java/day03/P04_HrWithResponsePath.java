package day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.HrTestBase;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P04_HrWithResponsePath extends HrTestBase {

    @DisplayName("GET request /countries with using Response path")
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON).queryParam("q","{\"region\":2}").when().get("/countries");

        response.prettyPrint();

        // print limit
        System.out.println("response.path(\"limit\") = " + response.path("limit"));
        // print hasMore
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));
        // print second country
        System.out.println("response.path(\"items[1].country.id\") = " + response.path("items[1].country.id"));
        // print fourth element's country name
        System.out.println("response.path(\"items[3].country_name\") = " + response.path("items[3].country_name"));
        // print fourth element's href
        System.out.println("response.path(\"items[3].links[0].href\") = " + response.path("items[3].links[0].href"));

        // get all countries name
        List<String> allCountryNames = response.path("items.country_name");
        System.out.println("allCountryNames = " + allCountryNames);

        // Verify all region_id is 2
        List<Integer> allRegionId = response.path("items.region_id");

        for (Integer regionID : allRegionId) {
            System.out.println("regionID = " + regionID);
            assertEquals(2,regionID);
        }

        allRegionId.stream().allMatch(each -> each == 2);
    }


    /*
    * Send a GET request to /employees endpoint to see only job_id = IT_PROG
    * Query Param
    *  q = {"job_id":"IT_PROG"}
    * Then assert all job_ids are IT_PROG
    * */



}
