package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DatabaseManager;
import com.db.model.JobHeadModel;

public class JobHeadDao {
	private static final Logger LOGGER=LogManager.getLogger(JobHeadDao.class);

	private static final String JOB_HEAD_QUERY = """
			select * from tr_job_head  where tr_customer_id=?;
			""";

	private JobHeadDao() {
		// TODO Auto-generated constructor stub
	}

	public static JobHeadModel getJobHeadDetail(int customerId) {

		JobHeadModel headModel = null;
		try {
			LOGGER.info("Getting the connection from the Database Manager");
			Connection connection = DatabaseManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(JOB_HEAD_QUERY);
			statement.setInt(1, customerId);
			  LOGGER.info("Executing the sql query {}",JOB_HEAD_QUERY);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				headModel=new JobHeadModel(resultSet.getInt("id"),
						resultSet.getString("job_number"), 
						resultSet.getInt("tr_customer_id"), 
						resultSet.getInt("tr_customer_product_id"), 
						resultSet.getInt("mst_service_location_id"),
						resultSet.getInt("mst_platform_id"), 
						resultSet.getInt("mst_warrenty_status_id"),
						resultSet.getInt("mst_oem_id"));
						
					
			}

		} catch (SQLException e) {			  
			LOGGER.error("Cannot convert the result set to the JobHeadModel bean ",e);
			e.printStackTrace();
		}
		return headModel;
	}

}
