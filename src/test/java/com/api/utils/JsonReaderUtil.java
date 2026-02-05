package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.qameta.allure.Step;

public class JsonReaderUtil {
	private static final Logger LOGGER=LogManager.getLogger(EnvUtil.class);
	
	@Step("Loading Test Data from JSON file")
	public static <T> Iterator<T> loadJSON(String fileName, Class<T[]> clazz) {
		LOGGER.info("Reading Json file from the path {}",fileName);
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);		
		ObjectMapper mapper=new ObjectMapper();
		T[] classArray;
		List<T> list = null;
		try {
			LOGGER.info("Mapping Json file to Bean class {}",clazz);
			classArray = mapper.readValue(inputStream,clazz);
			 list = Arrays.asList(classArray);
		} catch (IOException e) {
			LOGGER.error("Cannot read the json from the file {}",fileName,e);
			e.printStackTrace();
		}
		return list.iterator();
		
	}
	/* public static void main(String[] args) throws StreamReadException, DatabindException, IOException {

		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("TestData/demo.json");		
		ObjectMapper mapper=new ObjectMapper();
		UserCredential credential = mapper.readValue(inputStream, UserCredential.class);
		System.out.println(credential);
		
		
		} 
		
		public static void main(String[] args) throws StreamReadException, DatabindException, IOException {

		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("TestData/demo.json");		
		ObjectMapper mapper=new ObjectMapper();
		List credential = mapper.readValue(inputStream,List.class);
		System.out.println(credential);
		
		
	}
	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {

		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("TestData/demo.json");		
		ObjectMapper mapper=new ObjectMapper();
		UserCredential[] credential = mapper.readValue(inputStream,UserCredential[].class);
		Arrays.stream(credential).forEach(x->System.out.println(x.username()+"    "+x.password()));
	
		
		
	}
	public static Iterator<UserCredential> loadJSON(String fileName) {

		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);		
		ObjectMapper mapper=new ObjectMapper();
		UserCredential[] credential;
		List<UserCredential> userCredentials = null;
		try {
			credential = mapper.readValue(inputStream,UserCredential[].class);
			 userCredentials = Arrays.asList(credential);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userCredentials.iterator();
		
	}*/
}
