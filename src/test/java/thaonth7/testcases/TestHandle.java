package thaonth7.testcases;

import org.testng.annotations.Test;
import thaonth7.helpers.ExcelHelper;
import thaonth7.helpers.PropertiesHelper;
import thaonth7.utils.LogUtils;

public class TestHandle {
    @Test
    public void testReadPropertiesFile(){
        PropertiesHelper.loadAllFiles();
        System.out.println(PropertiesHelper.getValue("EMAIL"));
    }

    @Test
    public void testLog4j(){
        LogUtils.info("Test CucumberNG");
        LogUtils.error("Error ");
    }

    @Test
    public void testReadExcelData(){
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/dataTest/CRM.xlsx", "Login");
        System.out.println(excelHelper.getCellData("EMAIL", 1));
        System.out.println(excelHelper.getCellData("PASSWORD", 1));

    }

}
