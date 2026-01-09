package com.api.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.services.UserService;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailApiTest {
	private UserService service;
	@BeforeTest
	public void setUp() {
		service=new UserService();
	}
	@Test(description = "verifying if UserDetail APi is able to provice correct response", groups = {"api","regression","smoke"})

	public void userDetailApiTest() {
			service.userDetail(Role.FD)
				.then()
				.spec(SpecUtil.responseSpec_OK())
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailResponseSchema.json"));
		
	}

}
