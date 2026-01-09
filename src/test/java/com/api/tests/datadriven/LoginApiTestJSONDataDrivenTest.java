package com.api.tests.datadriven;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.api.request.model.UserCredential;
import com.api.services.AuthService;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;


public class LoginApiTestJSONDataDrivenTest {
	
	private AuthService authService;
	
	@BeforeTest(description = " Initializing Auth Service")
	public void setUp() {
		authService=new AuthService();
	}

	@Test(description = "verifying the login api is able to login for FD user", 
			groups = {"api","regression","smoke"},
			dataProviderClass = com.dataproviders.DataProviderUtils.class,
			dataProvider = "LoginAPIJsonDataProvider"
	
			
			)
	
	public void loginTest(UserCredential credential) {

		authService.login(credential)
		.then()
			.spec(SpecUtil.responseSpec_OK())
			.body("message", Matchers.equalTo("Success"))
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/loginSchema.json"))
			.log().all();
	}
	

}
