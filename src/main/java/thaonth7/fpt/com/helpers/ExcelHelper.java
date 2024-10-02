package thaonth7.fpt.com.helpers;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelHelper {

    private FileInputStream fis;
    private FileOutputStream fos;
    private Workbook wb;
    private Sheet sh;
    private Cell cell;
    private Row row;
    private CellStyle cellStyle;
    private Color myColor;
    private String excelFilePath;
    private Map<String, Integer> columns = new HashMap<>();
}
