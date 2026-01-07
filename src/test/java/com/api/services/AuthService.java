package com.api.services;

import static io.restassured.RestAssured.given;

import com.api.request.model.UserCredential;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

public class AuthService {

	public static final String END_POINT = "login";

	public Response login(UserCredential credential) {
		return given()
		.spec(SpecUtil.requestSpec(credential))
		.when()
		.post(END_POINT);
	}
}
