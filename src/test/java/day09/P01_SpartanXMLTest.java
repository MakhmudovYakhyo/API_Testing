package day09;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import utilities.SpartanTestBase;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P01_SpartanXMLTest extends SpartanTestBase {

    @Test
    public void test1(){
        given().accept(ContentType.XML)
                .auth().basic("admin","admin")
                .when().get("/api/spartans").prettyPeek()
                .then().statusCode(200)
                .contentType(ContentType.XML)
                .body("List.item[0].name",is("Meade"))
                .body("List.item[0].gender",is("Male"));
    }

    @Test
    public void test2(){
        Response response = given().accept(ContentType.XML)
                .auth().basic("admin", "admin")
                .when().get("/api/spartans");

        XmlPath xmlPath = response.xmlPath();

        // get first spartan name
        System.out.println("xmlPath.getString(\"List.item[0].name\") = " + xmlPath.getString("List.item[0].name"));

        // get 3rd spartan name
        System.out.println("xmlPath.getString(\"List.item[0].name\") = " + xmlPath.getString("List.item[0].name"));

        // get last spartan name
        System.out.println("xmlPath.getString(\"List.item[-1].name\") = " + xmlPath.getString("List.item[-1].name"));

        // get all spartan names
        List<String> listNames = xmlPath.getList("List.item.name");
        System.out.println("listNames = " + listNames);

        // how many spartans do we have
        List<String> allSpartans = xmlPath.getList("List.item");
        // Deserialization still possible to do it. We should use another dependencies or some Java logic to store into POJO
        // We are not going to touch this
        System.out.println("allSpartans.size() = " + allSpartans.size());

    }

}
