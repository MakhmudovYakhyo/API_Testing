package day08;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.BookITUtils;
import utilities.BookitTestBase;

import static io.restassured.RestAssured.*;

public class P02_BookITTest extends BookitTestBase {

    String email = "lfinnisz@yolasite.com";
    String password = "lissiefinni";
    String accessToken = BookITUtils.getToken(email,password);

    @DisplayName("GET /api/campuses")
    @Test
    public void test1(){
        System.out.println(accessToken);

        given().accept(ContentType.JSON)
                .header("Authorization",accessToken)
                .when().get("/api/campuses").prettyPeek()
                .then().statusCode(200);
    }

    @DisplayName("GET /api/users/me")
    @Test
    public void test2(){
        System.out.println(accessToken);

        given().accept(ContentType.JSON)
                .header("Authorization",accessToken)
                .when().get("/api/users/me").prettyPeek()
                .then().statusCode(200);
    }

}
