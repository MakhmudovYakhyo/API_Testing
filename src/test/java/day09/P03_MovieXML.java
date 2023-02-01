package day09;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class P03_MovieXML {

    @Test
    public void test1(){
        Response response = given().queryParam("t", "Superman")
                .queryParam("r", "xml")
                .queryParam("apikey", "81815fe6")
                .when().get("https://www.omdbapi.com").prettyPeek();

        XmlPath xmlPath = response.xmlPath();

        System.out.println("xmlPath.getString(\"root.movie.@year\") = " + xmlPath.getString("root.movie.@year"));

        System.out.println("xmlPath.getString(\"root.movie.@title\") = " + xmlPath.getString("root.movie.@title"));
    }

}
