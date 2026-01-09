package com.api.services;

import com.api.constants.Role;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class MasterService {
	private static final String MASTER_ENDPOINT="master";
	public Response master(Role role) {
		return RestAssured.given()
			       .spec(SpecUtil.requestSpecWithAuth(role))
			       
			       .when()
			       .post(MASTER_ENDPOINT); 
	}
	public Response master() {
		
		 return RestAssured.given()
			       .spec(SpecUtil.requestSpec())			       
			       .when()
			       .post(MASTER_ENDPOINT); 
		 }

}
