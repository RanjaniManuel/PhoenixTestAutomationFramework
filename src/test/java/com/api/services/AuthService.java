package com.api.services;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.request.model.UserCredential;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

public class AuthService {
	
	private static final Logger LOGGER=LogManager.getLogger(AuthService.class);

	private static final String LOGIN_END_POINT = "login";

	public Response login(Object credential) {
		LOGGER.info("Making Login Request for the payload {}",((UserCredential)credential).username());
		return given()
		.spec(SpecUtil.requestSpec(credential))
		.when()
		.post(LOGIN_END_POINT);
	}
}
