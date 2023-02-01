package day10;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;

public class P03_ValueSourceTest {

    @ParameterizedTest
    @ValueSource(ints = {10,20,30,40,50})
    @Test
    public void test1(int number){

        System.out.println(number);
        Assertions.assertTrue(number<40);

    }

    @ParameterizedTest(name = "{index}")
    @ValueSource(strings = {"A","BC","C","DE","E"})
    @Test
    public void test2(String str){

        System.out.println(str);
        Assertions.assertEquals(2, str.length());

    }


    @ParameterizedTest(name = "{index}")
    @ValueSource(ints = {22030,22031,22032,22033,22034,220,35,22036})
    @Test
    public void test3(int zipCode){

        System.out.println(zipCode);
        given().baseUri("https://api.zippopotam.us")
                        .pathParam("zipCode",zipCode)
                                .when().get("/us/{zipCode}").prettyPeek()
                        .then().statusCode(200);
    }



}
