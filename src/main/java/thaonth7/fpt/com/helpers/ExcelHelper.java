package thaonth7.fpt.com.helpers;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashMap;
import java.util.Hashtable;
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

    public void setExcelFile(String excelPath, String sheetName){
        try{
            File f = new File(excelPath);

            if (!f.exists()){
                throw  new Exception("File doesn't exist.");
            }
            fis = new FileInputStream(excelPath);
            wb = WorkbookFactory.create(fis);
            sh = wb.getSheet(sheetName);

            if (sh == null){
                throw new Exception("Sheet name doesn't exist.");
            }
            this.excelFilePath = excelPath;

            //adding all the column header names to the map 'columns'
            sh.getRow(0).forEach(cell1 -> {
                columns.put(cell.getStringCellValue(), cell.getColumnIndex());
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getCellData(int columnIndex, int rowIndex) {
        try {
            cell = sh.getRow(rowIndex).getCell(columnIndex);
            String cellData = null;
            switch (cell.getCellType()) {
                case STRING:
                    cellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellData = String.valueOf(cell.getDateCellValue());
                    } else {
                        cellData = String.valueOf((long) cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    cellData = Boolean.toString(cell.getBooleanCellValue());
                    break;
                case BLANK:
                    cellData = "";
                    break;
            }
            return cellData;
        } catch (Exception e) {
            return "";
        }
    }

    //Set by column index
    public void setCellData(String text, int columnIndex, int rowIndex){

        try {
            row = sh.getRow(rowIndex);
            if (row == null){
                row = sh.createRow(rowIndex);
            }

            cell = row.getCell(columnIndex);
            if (cell == null){
                cell = row.createCell(columnIndex);
            }
            cell.setCellValue(text);

            XSSFCellStyle style = (XSSFCellStyle) wb.createCellStyle();
            style.setFillPattern(FillPatternType.NO_FILL);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);

            cell.setCellStyle(style);

            fos = new FileOutputStream(excelFilePath);
            wb.write(fos);
            fos.flush();
            fos.close();
            System.out.println("Set data completed.");

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //Get all data from a sheet
    public  Object[][] getExcelData(String filePath, String sheetName){
        Object[][] data = null;
        Workbook  workbook = null;

        try {
            //Load the file
            FileInputStream fis = new FileInputStream(filePath);

            //Load the workbook
            workbook = new XSSFWorkbook(fis);

            //load the sheet
            Sheet sh = workbook.getSheet(sheetName);

            //load the row
            Row row = sh.getRow(0);

            int noOfRows = sh.getPhysicalNumberOfRows();
            int noOfColumns = row.getLastCellNum();

            System.out.println(noOfRows + " - " + noOfColumns);

            Cell cell;
            data = new Object[noOfRows - 1][noOfColumns];
            for (int i = 1; i < noOfRows ; i++) {
                for (int j = 0; j < noOfColumns; j++) {
                    row = sh.getRow(i);
                    cell = row.getCell(j);

                    switch (cell.getCellType()){
                        case STRING:
                            data[i -1][j] = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            data[i - 1][j] = String.valueOf(cell.getNumericCellValue());
                            break;
                        case BLANK:
                            data[i - 1][j] = cell.getStringCellValue();
                            break;
                        default:
                            data[i - 1][j] = cell.getStringCellValue();
                            break;
                    }
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw  new RuntimeException(e);
        }
        return data;
    }

    //Hàm này dùng cho trường hợp nhiều Field trong File Excel
    public int getColumns() {
        try {
            row = sh.getRow(0);
            return row.getLastCellNum();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw (e);
        }
    }

    //Get last row number (lấy vị trí dòng cuối cùng tính từ 0)
    public int getLastRowNum() {
        return sh.getLastRowNum();
    }

    //Lấy số dòng có data đang sử dụng
    public int getPhysicalNumberOfRows() {
        return sh.getPhysicalNumberOfRows();
    }

    public Object[][] getDataHashTable(String excelPath, String sheetName, int startRow, int endRow) {
        System.out.println("Excel Path: " + excelPath);
        Object[][] data = null;

        try {
            File f = new File(excelPath);
            if (!f.exists()) {
                try {
                    System.out.println("File Excel path not found.");
                    throw new IOException("File Excel path not found.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            fis = new FileInputStream(excelPath);

            wb = new XSSFWorkbook(fis);

            sh = wb.getSheet(sheetName);

            int rows = getLastRowNum();
            int columns = getColumns();

            System.out.println("Row: " + rows + " - Column: " + columns);
            System.out.println("StartRow: " + startRow + " - EndRow: " + endRow);

            data = new Object[(endRow - startRow) + 1][1];
            Hashtable<String, String> table = null;
            for (int rowNums = startRow; rowNums <= endRow; rowNums++) {
                table = new Hashtable<>();
                for (int colNum = 0; colNum < columns; colNum++) {
                    table.put(getCellData(colNum, 0), getCellData(colNum, rowNums));
                }
                data[rowNums - startRow][0] = table;
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return data;
    }
}
