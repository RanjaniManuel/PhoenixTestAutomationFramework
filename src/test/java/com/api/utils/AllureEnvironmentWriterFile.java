package com.api.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AllureEnvironmentWriterFile{
	private static final Logger LOGGER=LogManager.getLogger(AllureEnvironmentWriterFile.class);
	public static void createEnvironmentPropertyFile()  {
		String filePath="target/allure-results";
		File file=new File(filePath);
		file.mkdirs();
		
		Properties properties=new Properties();
		properties.setProperty("Application Name", "PHOENIX TEST AUTOMATION");
		properties.setProperty("Env", ConfigManager.env);
		properties.setProperty("BASE URI", ConfigManager.getProperty("BASE_URI"));
		properties.setProperty("Operating System", System.getProperty("os.name"));
		properties.setProperty("Operating System Version", System.getProperty("os.version"));
		properties.setProperty("Language", "Java");
		properties.setProperty("Java Version", System.getProperty("java.version"));
	
	
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(filePath+"/environment.properties");
			properties.store(fileWriter, "My Properties File");
			LOGGER.info("Create Environment Properties File at {} ",filePath);
		} catch (IOException e) {
			LOGGER.error("Unable to create Environment Properties 	File {} ",e);
			e.printStackTrace();
		}
		
		
	}

}
