package day06;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Employee;
import pojo.Region;

import static io.restassured.RestAssured.*;

public class P03_HRDeserializationPOJO {

    @DisplayName("GET regions to deserialization to POJO - LOMBOK - JSON PROPERTY")
    @Test
    public void test1(){

        JsonPath jsonPath = get("/regions").then().statusCode(200).extract().jsonPath();

        Region firstRegion = jsonPath.getObject("items[0]", Region.class);

        System.out.println("firstRegion = " + firstRegion);

        System.out.println("firstRegion.getLinks().get(0).getHref() = " + firstRegion.getLinks().get(0).getHref());



    }

    @DisplayName("GET employees to deserialization to POJO with required field")
    @Test
    public void test2(){

        JsonPath jsonPath = get("/employees").then().statusCode(200).extract().jsonPath();

        Employee employee = jsonPath.getObject("items[0]", Employee.class);

        System.out.println("employee = " + employee);

    }



}
