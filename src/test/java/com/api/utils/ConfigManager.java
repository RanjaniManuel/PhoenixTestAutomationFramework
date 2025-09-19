package com.api.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	private ConfigManager() {

	}

	private static Properties prop = new Properties();
	private static String path = "config/config.properties";
	private static String env;
	static {
	
		env = System.getProperty("env", "qa");
		env = env.toLowerCase().trim();
		switch (env) {
			case "dev" -> path = "config/config.dev.properties";
			case "qa" -> path = "config/config.qa.properties";
			case "uat" -> path = "config/config.uat.properties";
			default -> path = "config/config.qa.properties";

		}
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		if (inputStream == null) {
			throw new RuntimeException("cannot find the file at the path" + path);
		}
		try {
			prop.load(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String getProperty(String key) {

		return prop.getProperty(key.toUpperCase());
	}

}
