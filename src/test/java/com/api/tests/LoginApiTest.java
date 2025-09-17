package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.pojos.UserCredential;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class LoginApiTest {
	UserCredential credential=new UserCredential("iamfd","password");
	@Test
	public void loginTest() {
		given()
			.baseUri("http://64.227.160.186:9000/v1")
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
