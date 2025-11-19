package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.api.utils.ConfigManager;

public class DatabaseManager {

	private static final String DB_URL = ConfigManager.getProperty("DB_URL");
	private static final String DB_USER_NAME = ConfigManager.getProperty("DB_USER_NAME");
	private static final String DB_PASSWORD = ConfigManager.getProperty("DB_PASSWORD");
	private static volatile Connection connection; // Any update that happens to this variable
	// All the thread will be aware of it

	private DatabaseManager() {
		// TODO Auto-generated constructor stub
	}

	public  static void  createConnection() throws SQLException {
		if (connection == null) {
			synchronized (DatabaseManager.class) {
				if (connection == null) {
					connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);

				}
			}
			System.out.println(connection);
			//return connection;

		}
	}

}
