package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DatabaseManager;
import com.db.model.CustomerProductDBModel;

public class CustomerProductDao {
	private static final Logger LOGGER = LogManager.getLogger(CustomerProductDao.class);

	private static final String CUSTOMER_PRODUCT_QUERY = """
				select * from tr_customer_product where tr_customer_id=?;
			""";

	private CustomerProductDao() {
		// TODO Auto-generated constructor stub
	}

	public static CustomerProductDBModel getCustomerProductDetail(int productID) {
		CustomerProductDBModel customerProductDBModel = null;
		try {
			LOGGER.info("Getting the connection from the Database Manager");

			Connection connection = DatabaseManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(CUSTOMER_PRODUCT_QUERY);
			statement.setInt(1, productID);
			LOGGER.info("Executing the sql query {}", CUSTOMER_PRODUCT_QUERY);

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				customerProductDBModel = new CustomerProductDBModel(resultSet.getInt("id"),
						resultSet.getInt("tr_customer_id"), resultSet.getInt("mst_model_id"),
						resultSet.getString("dop"), resultSet.getString("serial_number"), resultSet.getString("imei1"),
						resultSet.getString("imei2"), resultSet.getString("popurl"));
			}

		} catch (SQLException e) {
			LOGGER.error("Cannot convert the result set to the customerProductDBModel bean ", e);

			e.printStackTrace();
		}
		return customerProductDBModel;

	}

}
