package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.services.MasterService;
import com.api.utils.SpecUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.module.jsv.JsonSchemaValidator;

@Listeners(com.listeners.APITestListener.class)

@Epic("Job Management")
@Feature("Master API")
public class MasterAPITest {
	private MasterService masterService;
	@BeforeTest(description = "Instantiating the Master Service Object")
	public void setup() {
		masterService=new MasterService();
	}
	
	@Story("Master API bring OEM details, Problem type, Warranty Status")
	@Description("verifying if Mater Api is able to give correct response")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "verifying if Mater Api is able to give correct response", groups = {"api","regression","smoke"})

	public void masterAPITest() {
		masterService.master(Role.FD)
			       .then()
			       .spec(SpecUtil.responseSpec_OK())
			       .body("message", Matchers.equalTo("Success"))
			       .body("data", Matchers.notNullValue())
			       .body("$", Matchers.hasKey("message"))
			       .body("$", Matchers.hasKey("data"))
			       .body("data", Matchers.hasKey("mst_oem"))
			       .body("data", Matchers.hasKey("mst_model"))
			       .body("data", Matchers.hasKey("mst_action_status"))
			       .body("data", Matchers.hasKey("mst_warrenty_status"))
			       .body("data", Matchers.hasKey("mst_platform"))
			       .body("data", Matchers.hasKey("mst_product"))
			       .body("data", Matchers.hasKey("mst_role"))
			       .body("data", Matchers.hasKey("mst_service_location"))
			       .body("data", Matchers.hasKey("mst_problem"))
			       .body("data", Matchers.hasKey("map_fst_pincode"))
			       .body("data.mst_oem.size()", Matchers.greaterThan(0))
			       .body("data.mst_model.size()", Matchers.greaterThan(0))
			       .body("data.mst_oem.id", Matchers.notNullValue())
			       .body("data.mst_oem.name", Matchers.notNullValue())
			       .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"))
			       .log().all();
	}
	@Test(description = "verifying if Master APi is able to provice correct response for invalid token", groups = {"api","negative","regression","smoke"})

	
	public void masterAPITest_NoToken() {
		masterService.master()
	       .then()
	       .spec(SpecUtil.responseSpec_Text(401))
	       .log().ifValidationFails();
		}

}
