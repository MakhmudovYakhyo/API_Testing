package day10;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.Csv;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

public class P05_CsvFileSourceTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/math.csv",numLinesToSkip = 1)
    public void test1(int num1,int num2, int sum){
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        System.out.println("sum = " + sum);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/zipCode.csv",numLinesToSkip = 1)
    public void test2(String state, String city, int zipCode){
        given().baseUri("https://api.zippopotam.us")
                .pathParam("state",state)
                .pathParam("city",city)
                .when().get("/us/{state}/{city}")
                .then().statusCode(200).body("places",hasSize(zipCode));
    }

}
