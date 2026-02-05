package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DatabaseManager;
import com.db.model.MapJobProblemModel;

import io.qameta.allure.Step;

public class MapJobProblemDao {
	private static final Logger LOGGER=LogManager.getLogger(MapJobProblemDao.class);
	private static final String PROBLEM_QUERY = """
			select *  from map_job_problem where tr_job_head_id =?;
			""";
	private MapJobProblemDao() {
		// TODO Auto-generated constructor stub
	}
	@Step("Retriving the Customer Product Details data from Database for a specific job head id")
	public static MapJobProblemModel getProblemDetails(int id) {
		
		MapJobProblemModel jobProblemModel=null;
		Connection connection;
		PreparedStatement statement;
		try {
			LOGGER.info("Getting the connection from the Database Manager");
			connection = DatabaseManager.getConnection();
			 statement = connection.prepareStatement(PROBLEM_QUERY);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			LOGGER.info("Executing the sql query {}",PROBLEM_QUERY);
			while(resultSet.next()) {
				jobProblemModel=new MapJobProblemModel(resultSet.getInt("id"),
						resultSet.getInt("tr_job_head_id"), 
						resultSet.getInt("mst_problem_id"),
						resultSet.getString("remark"));
			}
		} catch (SQLException e) {
			  LOGGER.error("Cannot convert the result set to the jobProblemModel bean ",e);
			e.printStackTrace();
		}
		return jobProblemModel;
		
	}

}
