package com.api.tests;

import org.testng.annotations.Test;

import static com.api.constants.Role.*;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailApiTest {
	@Test
	public void userDetailApiTest() {
		Header authHeader=new Header("Authorization", AuthTokenProvider.getToken(ENG));
	
		RestAssured.given()
				.and()
				.baseUri(ConfigManager.getProperty("BASE_URI"))
				.and()
				.accept(ContentType.JSON)
				.and()
				.header(authHeader)
				.log().uri()
				.log().method()
				.log().headers()
				.when()
				.get("userdetails")
				.then()
				.log().all()
				.statusCode(200)
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailResponseSchema.json"));
		
	}

}
