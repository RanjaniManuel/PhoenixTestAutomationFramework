package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigManager {
	
	private static final Logger LOGGER=LogManager.getLogger(ConfigManager.class);

	private ConfigManager() {

	}
	private static Properties prop = new Properties();
	private static String path = "config/config.properties";
	public static String env;
	static {
		LOGGER.info("Reading env value from terminal");
		if(System.getProperty("env")==null) {
			LOGGER.warn("env variable is not set..... Using qa as the env");
		
		}
		env = System.getProperty("env", "qa");
		env = env.toLowerCase().trim();
		switch (env) {
			case "dev" -> path = "config/config.dev.properties";
			case "qa" -> path = "config/config.qa.properties";
			case "uat" -> path = "config/config.uat.properties";
			default -> path = "config/config.qa.properties";

		}
		LOGGER.info("Tests are running on {} environment ",env);
		
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		if (inputStream == null) {
			LOGGER.error("CANNOT FIND THE FILE AT THE PATH {}",path);
			throw new RuntimeException("cannot find the file at the path" + path);
		}
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			LOGGER.error("CANNOT FIND THE FILE AT THE PATH {} OR  SOMETHING WENT WRONG.... PLEASE CHECK THE FILE",path);
			e.printStackTrace();
		}
	}
	public static String getProperty(String key) {
		
		
		return prop.getProperty(key.toUpperCase());
	}
}
