package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class MasterAPITest {
	@Test
	public void masterAPITest() {
		RestAssured.given()
			       .spec(SpecUtil.requestSpecWithAuth(Role.FD))
			       .contentType("")
			       .when()
			       .post("master")
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
	@Test
	
	public void masterAPITest_NoToken() {
		RestAssured.given()
	       .spec(SpecUtil.requestSpec())
	       .contentType("")
	       .when()
	       .post("master")
	       .then()
	       .spec(SpecUtil.responseSpec_Text(401))
	       .log().ifValidationFails();
		}

}
