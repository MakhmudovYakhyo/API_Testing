package day08;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanAuthTestBase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class P01_SpartanAuthTest extends SpartanAuthTestBase {

    @DisplayName("GET /spartans as GUEST user --> EXPECT --> 401")
    @Test
    void test1() {
        // it is negative test case
        given().accept(ContentType.JSON)
                .when().get("/api/spartans")
                .then().log().ifValidationFails()
                .statusCode(401)
                .body("error",is("Unauthorized"));
    }


    @DisplayName("GET /spartans as GUEST user --> EXPECT --> 200")
    @Test
    void test2() {
        // it is positive test case
        given().accept(ContentType.JSON)
                .auth().basic("user","user") // this is to send basic auth credentials through Rest Assured
                .when().get("/api/spartans").prettyPeek()
                .then().log().ifValidationFails().statusCode(200);
    }

    @DisplayName("GET /spartans as GUEST user --> EXPECT --> 403 --> FORBIDDEN")
    @Test
    void test3() {

        given().pathParam("id",52)
                .accept(ContentType.JSON)
                //.header("Accept","application/json")
                .auth().basic("editor","editor")
                .when().delete("/api/spartans/{id}")
                .then().statusCode(200)
                .body("error",is("Forbidden"));
    }

    @DisplayName("GET /spartans as GUEST user --> EXPECT --> 204 --> SUCCESS")
    @Test
    void test4() {

        given().pathParam("id",52)
                .auth().basic("admin","admin")
                .when().delete("/api/spartans/{id}")
                .then().statusCode(200);
    }

    /**
     * HOMEWORKS
     *
     *      ROLE Based Control Test --> RBAC
     *
     *      ADMIN   --> GET   POST    PUT     PATCH   DELETE   --> Spartan Flow
     *      EDITOR  --> GET   POST    PUT     PATCH   403
     *      USER    --> GET   403     403     403     403
     *      GUEST   --> 401   401     401     401     401
     *
     *  -- Create RBAC Test for all different roles from Spartan Application including with Negative Test cases
     *  -- Create SpartanUtil Class
     *            public static Map<String, Object> SpartanUtil.getRandomSpartan(){
     *                Map<String, Object> spartanMap = new HashMap();
     *                spartanMap.put("name", faker.funnyName());
     *
     *                user Faker class to create each time different spartan information
     *
     *                return spartanMap
     *            }
     *
     *            public static void GETSpartans (String role, String password, int statusCode, int id){
     *
     *                given().pathParam("id", id)
     *                .auth().basic(role, password).
     *                when().delete("/api/spartans/{id}").then().statusCode(statusCode);
     *
     *
     *            }
     *
     *      Q --> can we create class in utilities class with loop for each user, admin, guest with passport make more dynamic
     *              - YES we can but what if first user test is failing
     *
     *              - We should od it --> Data Driven Test
     *
     *              - JUnit5 DDT to implement
     *
     */
}
