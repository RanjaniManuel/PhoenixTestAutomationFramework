package com.api.services;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.utils.SpecUtil;
import com.dataproviders.api.bean.UserBean;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class AuthService {

	private static final Logger LOGGER = LogManager.getLogger(AuthService.class);

	private static final String LOGIN_END_POINT = "login";

	@Step("Perform login requestt with user Crecdential") // Aspect J weaver helps running @step code.
	// Because java doesnt have the jar file to execute this line("in fact java does
	// not know this step
	// annotation
	/*
	  Java can compile and run code with @Step as long as the annotation’s JAR is
	  on the classpath. What Java cannot do by itself is:
	  
	  1. intercept the method call
	  
	  2.collect step information
	  
	  3.send step details to Allure reports
	  
	  That’s where AspectJ Weaver comes in.
	 */
	public Response login(Object credential) {
		LOGGER.info("Making Login Request for the payload {}", ((UserBean) credential).getUsername());
		return given().spec(SpecUtil.requestSpec(credential)).when().post(LOGIN_END_POINT);
	}
}
