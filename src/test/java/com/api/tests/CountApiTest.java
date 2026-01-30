package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.services.DashboardService;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

@Listeners(com.listeners.APITestListener.class)
public class CountApiTest {
	private DashboardService dashboardService;
	
	@BeforeTest
	public void setup() {
		dashboardService=new DashboardService();
	}
	
	@Test(description = "verifying the Count api gives correct response", groups = {"api","regression","smoke"})
	public void verifyCountApiResponse() {
					dashboardService.count(Role.FD)
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
	@Test(description = "verifying the count api gives correct response for invalid token", groups = {"api","negative","regression","smoke"})
	public void verifyCountApi_missingAuthToken() {
		dashboardService.count()
		.then()
		.log().all()
		.spec(SpecUtil.responseSpec_Text(401));
		
		
	}

}
