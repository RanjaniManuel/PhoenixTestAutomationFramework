package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.api.request.model.UserCredential;

public class ExcelReaderUtil2 {
	private ExcelReaderUtil2() {
		// TODO Auto-generated constructor stub
	}
	public static Iterator<UserCredential> loadExcel(){
		
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("TestData/PhoenixTestData.xlsx");
		
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		
		XSSFRow headerRow=sheet.getRow(0);
		
		int usernameIndex=-1;
		int passwordIndex=-1;
		for(Cell cell:headerRow) {
			
			if(cell.getStringCellValue().trim().equalsIgnoreCase("username")) {
				usernameIndex = cell.getColumnIndex();
			
			}
			if(cell.getStringCellValue().trim().equalsIgnoreCase("password")) {
				passwordIndex= cell.getColumnIndex();
				
			}			
		}	
		
		int rowCount=sheet.getLastRowNum();
		List<UserCredential> userList = new ArrayList<UserCredential>();
		for(int i=1; i<= rowCount; i++) {
			
			XSSFRow row=sheet.getRow(i);
			UserCredential credential=new UserCredential(row.getCell(usernameIndex).toString(), row.getCell(passwordIndex).toString());
			userList.add(credential);			
		}	
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList.iterator();
		
	}

}
