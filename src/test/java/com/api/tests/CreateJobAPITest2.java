package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import com.api.utils.FakerDataGenerator;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
// created by Ranjani
public class CreateJobAPITest2 {
	private CreateJobPayload createJobPayload;
	

	@BeforeMethod(description = "Creating CreateJob Api request payload")
	public void setUp() {
				
		 createJobPayload=FakerDataGenerator.generateFakeCreateJobData();
		
		
	}

	@Test(description = "verifying the Create Api is able to create a new job", groups = { "api", "regression",
			"smoke" })
	public void createJobApi() {

		RestAssured.given().spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload)).when().post("job/create").then()
				.spec(SpecUtil.responseSpec_OK())
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/createJobAPIResponseSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_"));

	}

}
