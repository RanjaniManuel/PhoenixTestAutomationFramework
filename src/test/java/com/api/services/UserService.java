package com.api.services;

import com.api.constants.Role;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserService {
	private static final String USERDETAILS_ENDPOINT="/userdetails";
	public Response userDetail(Role role) {

		return RestAssured.given()
				.spec(SpecUtil.requestSpecWithAuth(role))
				.when()
				.get(USERDETAILS_ENDPOINT);
	}
}
