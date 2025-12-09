package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.DatabaseManager;
import com.db.model.MapJobProblemModel;

public class MapJobProblemDao {

	private static final String PROBLEM_QUERY = """
			select *  from map_job_problem where tr_job_head_id =?;
			""";
	private MapJobProblemDao() {
		// TODO Auto-generated constructor stub
	}
	
	public static MapJobProblemModel getProblemDetails(int id) {
		
		MapJobProblemModel jobProblemModel=null;
		Connection connection;
		PreparedStatement statement;
		try {
			connection = DatabaseManager.getConnection();
			 statement = connection.prepareStatement(PROBLEM_QUERY);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				jobProblemModel=new MapJobProblemModel(resultSet.getInt("id"),
						resultSet.getInt("tr_job_head_id"), 
						resultSet.getInt("mst_problem_id"),
						resultSet.getString("remark"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jobProblemModel;
		
	}

}
