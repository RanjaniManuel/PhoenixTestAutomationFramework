package com.demo.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadCSVFile {
	public static void main(String[] args) throws IOException, CsvException {
		
		InputStream file = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("TestData/LoginCreds.csv");
		InputStreamReader inputStreamReader=new InputStreamReader(file);
		
		
	
		try (CSVReader csvReader = new CSVReader(inputStreamReader)) {
			List<String[]> dataList = csvReader.readAll();
			for (String[] data : dataList) {
					System.out.println(data[0]);
					System.out.println(data[1]);
				}			
		}
	}
}
