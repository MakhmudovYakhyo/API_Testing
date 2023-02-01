package day10;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.everyItem;

public class P04_CsvSource {

    @ParameterizedTest
    @CsvSource({"1,3,4",
                "2,3,5",
                "3,4,7",
                "5,6,11"})
    public void test1(int num1,int num2,int sum){
        System.out.println(num1+" "+num2+" "+sum);
        Assertions.assertEquals(sum,(num1+num2));
    }

    @ParameterizedTest
    @CsvSource({"NY,New York",
            "CO, Denver",
            "VA, Fairfax",
            "MA, Boston",
            "MD, Annapolis"})
    public void testZipCodeDDT(String state,String city){
        System.out.println("state = " + state);
        System.out.println("city = " + city);

        int placeNumber = given().baseUri("https://api.zippopotam.us")
                .pathParam("state", state)
                .pathParam("city", city)
                .when().get("us/{state}/{city}")
                .then().statusCode(200)
                .body("places.'place name'", everyItem(containsString(city)))
                .extract().jsonPath().getList("places").size();

        System.out.println("city+placeNumber = " + city + placeNumber);

    }

}
