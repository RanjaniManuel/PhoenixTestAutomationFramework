package com.api.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JobService {
	private static final Logger LOGGER=LogManager.getLogger(JobService.class);


	
	private static final String CREATE_ENDPOINT="/job/create";
	private static final String SEARCH_ENDPOINT="/job/search";
	
	public Response create(Role role, CreateJobPayload createJobPayload) {
		LOGGER.info("Making request to the {} for the Role {} and Payload {}",CREATE_ENDPOINT,role,createJobPayload);

		return RestAssured.given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
		.when().post(CREATE_ENDPOINT);
	}
	
	public Response search(Role role, Object payload) {
		LOGGER.info("Making request to the {} for the Role {} and Payload {}",SEARCH_ENDPOINT,role,payload);

		return RestAssured.given()
				.spec(SpecUtil.requestSpecWithAuth(Role.FD, payload))
				.when().post(SEARCH_ENDPOINT);
	}
}
