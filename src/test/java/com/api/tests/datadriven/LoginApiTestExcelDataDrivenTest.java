package com.api.tests.datadriven;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.request.model.UserCredential;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;


public class LoginApiTestExcelDataDrivenTest {
	
	

	@Test(description = "verifying the login api is able to login for FD user", 
			groups = {"api","regression","smoke"},
			dataProviderClass = com.dataproviders.DataProviderUtils.class,
			dataProvider = "LoginAPIExcelnDataProvider"	)
	public void loginTest(UserCredential credential) {

		given()
			.spec(SpecUtil.requestSpec(credential))
		.when()
			.post("login")
		.then()
			.spec(SpecUtil.responseSpec_OK())
			.body("message", Matchers.equalTo("Success"))
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/loginSchema.json"))
			.log().all();
	}
	

}
