package com.api.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderUtil {
	public static void main(String[] args) throws IOException {
		
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("TestData/PhoenixTestData.xlsx");
		
		XSSFWorkbook workbook=new XSSFWorkbook(inputStream);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		
		int lastRowNum = sheet.getLastRowNum();
		System.out.println(lastRowNum);
		XSSFRow rowHeader = sheet.getRow(0);
		short lastCellNum = rowHeader.getLastCellNum();
	
		System.out.println(lastCellNum);
		
		
		for(int i=0; i<=lastRowNum; i++) {
			for(int j=0; j<lastCellNum; j++) {
				XSSFRow row = sheet.getRow(i);
				System.out.print(row.getCell(j)+" ");
			}
			System.out.println("   ");
		}
		//row. 
		
		workbook.close();
		
	}

}
