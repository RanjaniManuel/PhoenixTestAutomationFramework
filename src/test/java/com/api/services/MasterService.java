package com.api.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constants.Role;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class MasterService {
	private static final Logger LOGGER=LogManager.getLogger(AuthService.class);

	private static final String MASTER_ENDPOINT="master";
	public Response master(Role role) {
		LOGGER.info("Making request to the {} for the Role {}",MASTER_ENDPOINT,role);

		return RestAssured.given()
			       .spec(SpecUtil.requestSpecWithAuth(role))
			       
			       .when()
			       .post(MASTER_ENDPOINT); 
	}
	public Response master() {
		
		LOGGER.info("Making request to the {} ",MASTER_ENDPOINT);
		 return RestAssured.given()
			       .spec(SpecUtil.requestSpec())			       
			       .when()
			       .post(MASTER_ENDPOINT); 
		 }

}
