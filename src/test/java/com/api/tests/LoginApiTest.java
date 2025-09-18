package com.api.tests;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.pojos.UserCredential;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginApiTest {
	UserCredential credential=new UserCredential("iamfd","password");
	@Test
	public void loginTest() {

		given()
			.baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
			.contentType(ContentType.JSON)
		.and()
			.accept(ContentType.JSON)
		.and()
			.body(credential)
		.log().uri()
		.log().body()
		.log().method()
		.log().headers()
		.when()
			.post("login")
		.then()
			.statusCode(200)
			.and()
			.body("message", Matchers.equalTo("Success"))
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/loginSchema.json"))
		.log().all();
	}

}
