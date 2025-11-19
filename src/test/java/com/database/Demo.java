package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo {
	
	public static void main(String[] args) throws SQLException {
		Connection connection = DatabaseManager.getConnection();
		//System.out.println(connection);
		
	Statement statement = connection.createStatement();
		
		ResultSet restultSet = statement.executeQuery("select first_name, last_name, mobile_number  from tr_customer ;");
	
		while(restultSet.next()) {
			System.out.println(restultSet.getString("first_name"));
			System.out.println(restultSet.getString("last_name"));
			System.out.println(restultSet.getString("mobile_number"));
		}
		
		
		
	}

}
