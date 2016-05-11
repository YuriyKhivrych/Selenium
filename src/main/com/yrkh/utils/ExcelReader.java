package com.yrkh.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Yuriy on 16.11.2015.
 */
public class ExcelReader {
    String path;
    File file;
    FileInputStream fileInputStream;

    public ExcelReader(String path){
        this.open(path);
    }

    public String[][] readData (String sheetName) throws Exception{
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        int rowNum = sheet.getLastRowNum() - sheet.getFirstRowNum() + 1;
        int colNum = sheet.getRow(0).getLastCellNum();
        String[][] data = new String[rowNum][colNum];

        for(int i = 0; i < rowNum; i++){
            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < colNum; j++){
                XSSFCell cell = row.getCell(j);
                String value = cellToString(cell);
                data[i][j] = value;
            }
        }
        return  data;
    }

    private String cellToString(XSSFCell cell){
        Object result;
        int type = cell.getCellType();

        switch (type){
            case Cell.CELL_TYPE_NUMERIC:
                result = cell.getNumericCellValue();
                break;
            case Cell.CELL_TYPE_STRING:
                result = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                result = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                result = cell.getBooleanCellValue();
                break;
            default:
                throw new RuntimeException("This type of data is not supported.");
        }
        return result.toString();
    }

    public void open(String path){
        this.path = path;
        file = new File(path);
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
