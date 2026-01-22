package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.services.AuthService;
import com.api.utils.SpecUtil;
import com.dataproviders.api.bean.UserBean;

import io.restassured.module.jsv.JsonSchemaValidator;

@Listeners(com.listeners.APITestListener.class)
public class LoginApiTest {
	
	private UserBean credential;
	private AuthService authService;
	@BeforeMethod
	public void setup() {
		
		credential=new UserBean("iamfd","password");
		authService=new AuthService();
	}

	@Test(description = "verifying the login api is able to login for FD user", groups = {"api","regression","smoke"})
	
	public void loginTest() {

		authService.login(credential)
			.then()
			.spec(SpecUtil.responseSpec_OK())
			.body("message", Matchers.equalTo("Success"))
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/loginSchema.json"))
			.log().all();
	}
	

}
