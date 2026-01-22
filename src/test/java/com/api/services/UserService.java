package com.api.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constants.Role;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserService {
	private static final Logger LOGGER=LogManager.getLogger(AuthService.class);

	private static final String USERDETAILS_ENDPOINT="/userdetails";
	public Response userDetail(Role role) {
		
		LOGGER.info("Making request to the {} for the Role {}",USERDETAILS_ENDPOINT,role);

		
		return RestAssured.given()
				.spec(SpecUtil.requestSpecWithAuth(role))
				.when()
				.get(USERDETAILS_ENDPOINT);
	}
}
