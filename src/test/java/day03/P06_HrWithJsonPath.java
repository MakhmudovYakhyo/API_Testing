package day03;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.HrTestBase;

import java.util.List;

import static io.restassured.RestAssured.get;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P06_HrWithJsonPath extends HrTestBase {

    @DisplayName("GET all countries")
    @Test
    public void test1() {

        Response response = get("/countries");

        response.prettyPrint();

        //Then status code is 200
        assertEquals(200, response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        // get me 3rd country information
        System.out.println("jsonPath.getString(\"items[2]\") = " + jsonPath.getString("items[2]"));
        // can we store them as MAP
        // Yes we can. We are gonna do later

        // get me 3rd country name
        String countryThird = jsonPath.getString("items[2].country_name");
        System.out.println("countryThird = " + countryThird);

        // get me 3rd country and 4th country name
        System.out.println("jsonPath.getString(\"items[3,4].country.name\") = " + jsonPath.getString("items[2,3,4,5.country.name"));

        // get me all country name where region_id is 2
        List<Object> list = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println("list = " + list);

        /*
        * It refers each item from items array
        * */
    }

}
