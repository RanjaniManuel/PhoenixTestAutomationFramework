package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.api.utils.ConfigManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCPDemo {
	public static void main(String[] args) throws SQLException {
		HikariConfig config=new HikariConfig();
		config.setJdbcUrl(ConfigManager.getProperty("DB_URL"));
		config.setUsername(ConfigManager.getProperty("DB_USER_NAME"));
		config.setPassword(ConfigManager.getProperty("DB_PASSWORD"));
		
		
		//I can connect the database with 10 connections
		config.setMaximumPoolSize(10);	
		
		//I can make some connection idle by setting as follows
		config.setMinimumIdle(2);
		
		//
		config.setConnectionTimeout(10000);
		
		/*This property controls the maximum amount of time (in milliseconds) 
		  that a connection is allowed to sit idle in the pool.
		   Whether a connection is retired as idle or not is subject to a maximum variation of +30 seconds, 
		  and average variation of +15 seconds. A connection will never be retired as idle before this timeout.
		   A value of 0 means that idle connections are never removed from the pool.	 */
	
		config.setIdleTimeout(10000);
		
		
		config.setMaxLifetime(1800000);// 30 mins 30*60*1000
		 
		config.setPoolName("Phoenix Test Automation Framework Hikari Pool");
		
		
		HikariDataSource dataSource=new HikariDataSource(config);
		Connection connection = dataSource.getConnection();
		System.out.println(connection);
		
		Statement statement = connection.createStatement();
		
		ResultSet restultSet = statement.executeQuery("select first_name, last_name, mobile_number  from tr_customer ;");
	
		while(restultSet.next()) {
			System.out.println(restultSet.getString("first_name"));
			System.out.println(restultSet.getString("last_name"));
			System.out.println(restultSet.getString("mobile_number"));
		}
		
		dataSource.close();
	
	}

}
