package com.demo.csv;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class ReadCSVFile_MapToPojo {
	public static void main(String[] args) {
		InputStream inputStream= Thread.currentThread().getContextClassLoader().getResourceAsStream("TestData/LoginCreds.csv");
		InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
		CSVReader csvReader=new CSVReader(inputStreamReader);
		
		CsvToBean<UserPojo> csvToBean=new CsvToBeanBuilder(inputStreamReader)
												.withType(UserPojo.class)
												.withIgnoreEmptyLine(true)
												.build();
		List<UserPojo> list = csvToBean.parse();
		System.out.println(list);							
	
	
	
	
	
	
	}
}
