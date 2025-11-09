package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReaderUtil {
	public static <T> Iterator<T> loadJSON(String fileName, Class<T[]> clazz) {

		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);		
		ObjectMapper mapper=new ObjectMapper();
		T[] classArray;
		List<T> list = null;
		try {
			classArray = mapper.readValue(inputStream,clazz);
			 list = Arrays.asList(classArray);
		} catch (IOException e) {
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
