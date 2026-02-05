package com.api.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.services.UserService;
import com.api.utils.SpecUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.module.jsv.JsonSchemaValidator;

@Listeners(com.listeners.APITestListener.class)
@Epic("User Management")
@Feature("User Details")
public class UserDetailApiTest {
	private UserService service;
	@BeforeTest
	public void setUp() {
		service=new UserService();
	}
	@Story("User Detail should be shown here")
	@Description("verifying if UserDetail APi is able to provice correct response")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "verifying if UserDetail APi is able to provice correct response", groups = {"api","regression","smoke"})
	
	public void userDetailApiTest() {
			service.userDetail(Role.FD)
				.then()
				.spec(SpecUtil.responseSpec_OK())
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailResponseSchema.json"));
		
	}

}
