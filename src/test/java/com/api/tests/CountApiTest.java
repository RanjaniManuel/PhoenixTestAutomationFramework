package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CountApiTest {
	@Test
	public void verifyCountApiResponse() {
		RestAssured.given()
					.baseUri(ConfigManager.getProperty("BASE_URI"))
					.and()
					.accept(ContentType.JSON)
					.and()
					.header("Authorization",AuthTokenProvider.getToken(Role.FD))
					.log().uri()
					.log().method()
					.log().headers()
					.when()
					.get("/dashboard/count")
					.then()
					.log().ifValidationFails()
					.statusCode(200)
					.body("message", Matchers.equalTo("Success"))
					.body("data", Matchers.notNullValue())
					.body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
					.body("data.label", Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
					.body("data.key", Matchers.containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"))
					.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/countAPIResponseSchema.json"));
					

	}
	@Test
	public void verifyCountApi_missingAuthToken() {
		RestAssured.given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
		.accept(ContentType.JSON)
		.and()		
		.log().uri()
		.log().method()
		.log().headers()
		.when()
		.get("/dashboard/count")
		.then()
		.log().all()
		.statusCode(401);
		
		
	}

}
