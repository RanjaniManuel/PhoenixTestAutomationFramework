package com.database.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.database.DatabaseManager;
import com.db.model.CustomerDBModel;

public class CustomerDao {
	private final static String CUSTOMER_DETAIL_QUERY="""
			select * from tr_customer where id=118196;
			""";

	public static CustomerDBModel getCustomerInfo() throws SQLException {

		Connection connection = DatabaseManager.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(CUSTOMER_DETAIL_QUERY);
		CustomerDBModel customerDBModel = null;

		while (resultSet.next()) {
			System.out.println(resultSet.getString("first_name"));
			customerDBModel = new CustomerDBModel(resultSet.getString("first_name"), resultSet.getString("last_name"),
					resultSet.getString("mobile_number"), resultSet.getString("mobile_number_alt"),
					resultSet.getString("email_id"), resultSet.getString("email_id_alt"));

		}
		return customerDBModel;
	}

	
}
