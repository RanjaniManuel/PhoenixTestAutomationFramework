package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.DatabaseManager;
import com.db.model.CustomerAddressDBModel;

public class CustomerAddressDao {
	
	private static final String CUSTOMER_ADDRESS_QUERY ="select * from tr_customer_address where id=? ;";
	
	private CustomerAddressDao() {
		
	}
	public static CustomerAddressDBModel getAddress(int customerAddressId)  {
		
		CustomerAddressDBModel addressDBModel=null;
		try {
			Connection connection = DatabaseManager.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(CUSTOMER_ADDRESS_QUERY);
			statement.setInt(1, customerAddressId);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				addressDBModel=new CustomerAddressDBModel( 
						resultSet.getInt("id"), 
						resultSet.getString("flat_number"),
						resultSet.getString("apartment_name"), 
						resultSet.getString("street_name"), 
						resultSet.getString("landmark"), 
						
						resultSet.getString("area"), 
						resultSet.getString("pincode"), 
						resultSet.getString("country"),
						resultSet.getString("state"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addressDBModel;
	}
}
