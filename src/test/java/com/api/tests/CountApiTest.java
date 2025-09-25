package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CountApiTest {
	@Test
	public void verifyCountApiResponse() {
		RestAssured.given()
					.spec(SpecUtil.requestSpecWithAuth(Role.FD))
					.when()
					.get("/dashboard/count")
					.then()
					.log().ifValidationFails()
					.spec(SpecUtil.responseSpec_OK())
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
		.spec(SpecUtil.requestSpec())
		.when()
		.get("/dashboard/count")
		.then()
		.log().all()
		.spec(SpecUtil.responseSpec_Text(401));
		
		
	}

}
