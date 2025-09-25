package com.api.tests;

import static com.api.constants.Role.FD;

import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailApiTest {
	@Test
	public void userDetailApiTest() {
	
		RestAssured.given()
				.spec(SpecUtil.requestSpecWithAuth(FD))
				.when()
				.get("userdetails")
				.then()
				.spec(SpecUtil.responseSpec_OK())
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailResponseSchema.json"));
		
	}

}
