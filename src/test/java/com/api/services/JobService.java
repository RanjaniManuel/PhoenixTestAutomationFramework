package com.api.services;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JobService {
	private static final String CREATE_ENDPOINT="/job/create";
	private static final String SEARCH_ENDPOINT="/job/search";
	
	public Response create(Role role, CreateJobPayload createJobPayload) {
		return RestAssured.given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
		.when().post(CREATE_ENDPOINT);
	}
	
	public Response search(Role role, Object Payload) {
		return RestAssured.given()
				.spec(SpecUtil.requestSpecWithAuth(Role.FD, Payload))
				.when().post(SEARCH_ENDPOINT);
	}
}
