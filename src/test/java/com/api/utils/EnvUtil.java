package com.api.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvUtil {

	public static Dotenv dotenv;
	
	static {
		dotenv= Dotenv.load();
	}
	
	private EnvUtil() {
		// TODO Auto-generated constructor stub
	}
	public static String getValue(String varName) {
		return dotenv.get(varName);
	}
}
