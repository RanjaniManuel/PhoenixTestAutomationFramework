package com.api.utils;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;

import static com.api.constants.Role.*;

import com.api.constants.Role;
import com.api.request.model.UserCredential;

import io.restassured.http.ContentType;

public class AuthTokenProvider {
	private AuthTokenProvider() {
		// TODO Auto-generated constructor stub
	}
	public static String getToken(Role role) {
		UserCredential credential=null;
		if(role==FD) {
			credential=new UserCredential("iamfd", "password");
		}
		else if(role==SUP) {
			credential=new UserCredential("iamsup", "password");
		}
		else if(role==ENG){
			credential=new UserCredential("iameng", "password");
		}
		else if(role==QC){
			credential=new UserCredential("iamqc", "password");
		}
		
		String token = given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.contentType(ContentType.JSON)
		.body(credential)
		.when()
		.post("login")
		.then()
		.log().ifValidationFails()
		.statusCode(200)
		.body("message", Matchers.equalTo( "Success"))
		.extract()
		.body()
		.jsonPath()
	
		.getString("data.token");
		
		return token;
	}
	
	
}
