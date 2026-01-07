package com.api.services;

import static io.restassured.RestAssured.given;

import com.api.utils.SpecUtil;

import io.restassured.response.Response;

public class AuthService {

	public static final String LOGIN_END_POINT = "/login";

	public Response login(Object credential) {
		return given()
		.spec(SpecUtil.requestSpec(credential))
		.when()
		.post(LOGIN_END_POINT);
	}
}
