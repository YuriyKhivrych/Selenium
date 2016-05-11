package com.yrkh.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

/**
 * Created by Yuriy on 20.04.2016.
 */
public class ExcelWriter {

    public ExcelWriter(){
    }

    public void writeToFile(ArrayList<String[]> data, String path, String sheetName){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(sheetName);

        int rownum = 0;
        for (String[] rowData : data) {
            Row row = sheet.createRow(rownum++);
            int cellnum = 0;
            for (String  cellData : rowData) {
                Cell cell = row.createCell(cellnum++);
                cell.setCellValue(cellData);
            }
        }

        try {
            FileOutputStream out =
                    new FileOutputStream(new File(path));
            workbook.write(out);
            out.close();
            System.out.println("File was written successfully.");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}