package com.api.tests.datadriven;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
// created by Ranjani
public class CreateJobAPIFakerTest {
	

	
	@Test(description = "verifying the Create Api is able to create a new job", 
			groups = { "api", "regression", "DataDriven","faker" },
			dataProviderClass = com.dataproviders.DataProviderUtils.class,
			dataProvider = "CreateJobAPIFakerDataProvider")
	public void createJobApi(CreateJobPayload createJobPayload) {

		RestAssured.given().spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
					.when().post("job/create")
					.then()
				.spec(SpecUtil.responseSpec_OK())
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/createJobAPIResponseSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_"));

	}

}
