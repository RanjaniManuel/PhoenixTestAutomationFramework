package com.database;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvRunner {
	public static void main(String[] args) {
		
		Dotenv dotenv = Dotenv.load();
		String data_url=dotenv.get("DB_URL");
		System.out.println(data_url);
		String data_DB_USER_NAME=dotenv.get("DB_USER_NAME");
		System.out.println(data_DB_USER_NAME);
		String data_DB_PASSWORD=dotenv.get("DB_PASSWORD");
		System.out.println(data_DB_PASSWORD);
		

	}
}
