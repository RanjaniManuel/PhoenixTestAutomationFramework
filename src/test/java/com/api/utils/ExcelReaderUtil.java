package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.poiji.bind.Poiji;

import io.qameta.allure.Step;

public class ExcelReaderUtil {
	private static final Logger LOGGER = LogManager.getLogger(ExcelReaderUtil.class);

	private ExcelReaderUtil() {
		// TODO Auto-generated constructor stub
	}
	@Step("Loading Test Data from Excel")
	public static <T> Iterator<T> loadExcel(String fileName, String sheetName, Class<T> clazz) {
		LOGGER.info("Reading test data from the excel file {} with sheet name {}", fileName, sheetName);
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		System.out.println("Hai");
		XSSFWorkbook workbook = null;
		try {

			workbook = new XSSFWorkbook(inputStream);
		} catch (IOException e) {
			LOGGER.error("Cannot load the excel file {}", fileName);
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet(sheetName);
		LOGGER.info("Converting the excel sheet {} to POJO class {}",sheetName, clazz);
		List<T> list = Poiji.fromExcel(sheet, clazz);
		return list.iterator();
	}

	/*
	 * public static Iterator<UserCredential> loadExcel( ){
	 * 
	 * InputStream inputStream =
	 * Thread.currentThread().getContextClassLoader().getResourceAsStream(
	 * "TestData/PhoenixTestData.xlsx");
	 * 
	 * XSSFWorkbook workbook = null; try { workbook = new XSSFWorkbook(inputStream);
	 * } catch (IOException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } XSSFSheet sheet = workbook.getSheet("Sheet1");
	 * 
	 * XSSFRow headerRow=sheet.getRow(0);
	 * 
	 * int usernameIndex=-1; int passwordIndex=-1; for(Cell cell:headerRow) {
	 * 
	 * if(cell.getStringCellValue().trim().equalsIgnoreCase("username")) {
	 * usernameIndex = cell.getColumnIndex();
	 * 
	 * } if(cell.getStringCellValue().trim().equalsIgnoreCase("password")) {
	 * passwordIndex= cell.getColumnIndex();
	 * 
	 * } }
	 * 
	 * int rowCount=sheet.getLastRowNum(); List<UserCredential> userList = new
	 * ArrayList<UserCredential>(); for(int i=1; i<= rowCount; i++) {
	 * 
	 * XSSFRow row=sheet.getRow(i); UserCredential credential=new
	 * UserCredential(row.getCell(usernameIndex).toString(),
	 * row.getCell(passwordIndex).toString()); userList.add(credential); } try {
	 * workbook.close(); } catch (IOException e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); } return userList.iterator();
	 * 
	 * } public static Iterator<UserBean> loadExcel(){
	 * 
	 * InputStream inputStream =
	 * Thread.currentThread().getContextClassLoader().getResourceAsStream(
	 * "TestData/PhoenixTestData.xlsx");
	 * 
	 * XSSFWorkbook workbook = null; try { workbook = new XSSFWorkbook(inputStream);
	 * } catch (IOException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } XSSFSheet sheet = workbook.getSheet("Sheet1");
	 * 
	 * XSSFRow headerRow=sheet.getRow(0);
	 * 
	 * List<UserBean> list = Poiji.fromExcel(sheet, UserBean.class); return
	 * list.iterator(); }
	 */

}
