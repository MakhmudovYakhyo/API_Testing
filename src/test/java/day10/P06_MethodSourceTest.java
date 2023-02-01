package day10;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import utilities.ExcelUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class P06_MethodSourceTest {

    @ParameterizedTest
    @MethodSource("getNames")
    public void test1(String name) {
        System.out.println("name = " + name);
    }

    @ParameterizedTest
    @MethodSource("getExcelData")
    public void credentialsTest(Map<String, String> userInfo) {
        System.out.println("userInfo = " + userInfo);
        System.out.println("userInfo.get(\"Email\") = " + userInfo.get("Email"));
        System.out.println("userInfo.get(\"Password\") = " + userInfo.get("Password"));
    }

    public static List<String> getNames() {
        List<String> namesList = Arrays.asList("Joe","Biden","Donald","Trump");
        return namesList;
    }

    public static List<Map<String, String>> getExcelData() {

        ExcelUtils excelUtils = new ExcelUtils("src/test/resources/Library.xlsx","library1-short");

        return excelUtils.getDataList();
    }

}