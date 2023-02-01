package day10;

import org.junit.jupiter.api.*;

public class P01_Junit5AnnotationsLifeCycle {

    @BeforeAll
    public static void init(){
        System.out.println("===== BeforeAll is working =====");
    }

    @BeforeEach
    public void beforeEach(){
        System.out.println("===== BeforeEach is working =====");
    }

    @Test
    public void test1(){
        System.out.println("===== Test 1 is working =====");
    }

    @Test
    public void test2(){
        System.out.println("===== Test 2 is working =====");
    }

    @AfterEach
    public void afterEach(){
        System.out.println("===== AfterEach is working =====");
    }

    @AfterAll
    public static void destroy(){
        System.out.println("===== AfterAll is working =====");
    }
}
