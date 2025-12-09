package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.DatabaseManager;
import com.db.model.CustomerProductDBModel;

public class CustomerProductDao {
	
	private static final String CUSTOMER_PRODUCT_QUERY="""
				select * from tr_customer_product where tr_customer_id=?;
			""";
	
	private CustomerProductDao() {
		// TODO Auto-generated constructor stub
	}
	public static CustomerProductDBModel getCustomerProductDetail(int productID) {
		CustomerProductDBModel customerProductDBModel = null;
		try {
			Connection connection = DatabaseManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(CUSTOMER_PRODUCT_QUERY);
			statement.setInt(1, productID);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				customerProductDBModel=new CustomerProductDBModel(resultSet.getInt("id"),
						resultSet.getInt("tr_customer_id") ,
						resultSet.getInt("mst_model_id"),
						resultSet.getString("dop"), 
						resultSet.getString("serial_number"), 
						resultSet.getString("imei1"), 
						resultSet.getString("imei2"), 
						resultSet.getString("popurl")					
					);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerProductDBModel;
		
	}

}
