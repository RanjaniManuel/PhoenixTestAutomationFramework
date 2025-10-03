package com.api.tests;

import static com.api.constants.Role.FD;

import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailApiTest {
	@Test(description = "verifying if UserDetail APi is able to provice correct response", groups = {"api","regression","smoke"})

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
