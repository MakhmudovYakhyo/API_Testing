package day10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class P02_Junit5Assertions {

    /**
     *
     * HARD ASSERT --> ASSERT
     *  - tEST EXECUTION will be aborted if the assert condition is not met
     *  - Rest of execution will stop
     *
     *  - Use Case --> if we are checking critical functionality
     */
    @Test
    public void hardAssert(){
        assertEquals(10,5+5);
        System.out.println("=== First Assert is done ===");

        assertEquals(9,5+5);
        System.out.println("=== Second Assert is done ===");

        assertEquals(10,5+5);
        System.out.println("=== Third Assert is done ===");
    }


    /**
     *  SOFT ASSERT
     *  - Test Execution will continue till end of the code fragment even if one the assertion is failing
     * <p>
     *  TESTNG --> SoftAssert softAssert = new SoftAssert()
     *                        softAssert.assertEquals()..
     *                        softAssert.assertAll()
     */
    @DisplayName("JUNIT5 S ")
    @Test
    public void softAssert(){
        assertAll("Learning Soft Assert",
                () -> assertEquals(10,5+5),
                () -> assertEquals(9,5+5),
                () -> assertEquals(10,5+5));
    }
}
