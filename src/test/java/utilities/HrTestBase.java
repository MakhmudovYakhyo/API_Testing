package utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class HrTestBase {

    @BeforeAll
    public void init(){
        RestAssured.baseURI = "http://3.80.111.193:1000/ords/hr";
    }

}