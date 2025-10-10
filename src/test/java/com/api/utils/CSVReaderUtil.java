package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtil {

	
	private CSVReaderUtil() {
	} 
	public static void loadCsv(String pathOfCSVFile) {
		InputStream inputStream= Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSVFile);
		InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
		CSVReader csvReader=new CSVReader(inputStreamReader);
		
		CsvToBean<UserBean> csvToBean=new CsvToBeanBuilder(csvReader)
												.withType(UserBean.class)
												.withIgnoreEmptyLine(true)
												.build();
		List<UserBean> list = csvToBean.parse();
		System.out.println(list);				
	
	}
}
