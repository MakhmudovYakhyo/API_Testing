package day10;

import org.junit.jupiter.api.Test;
import utilities.ExcelUtils;

import java.util.List;
import java.util.Map;

public class P07_ExcelUtilPractice {

    @Test
    public void test1(){

        ExcelUtils excelUtils = new ExcelUtils("src/test/resources/Library.xlsx","library1-short");

        List<Map<String,String>> allUsersInfo = excelUtils.getDataList();

        for (Map<String, String> map : allUsersInfo) {
            System.out.println("map = " + map);
        }

    }

}
