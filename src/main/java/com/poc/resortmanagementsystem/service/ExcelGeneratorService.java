package com.poc.resortmanagementsystem.service;

import com.poc.resortmanagementsystem.entity.ControllerInfo;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGeneratorService {
    public void generateExcel(ControllerInfo controllerInfo, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Controller Info");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Controller Name");
        headerRow.createCell(1).setCellValue("Request Mapping URLs");
        headerRow.createCell(2).setCellValue("Method Names");

        Row dataRow = sheet.createRow(1);
        dataRow.createCell(0).setCellValue(controllerInfo.getControllerName());
        dataRow.createCell(1).setCellValue(String.join(", ", controllerInfo.getRequestMappingUrls()));
        dataRow.createCell(2).setCellValue(String.join(", ", controllerInfo.getMethodNames()));

        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
        }
        workbook.close();
    }
}

