package com.api.utils;

import static com.api.constants.Role.ENG;
import static com.api.constants.Role.FD;
import static com.api.constants.Role.QC;
import static com.api.constants.Role.SUP;
import static io.restassured.RestAssured.given;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hamcrest.Matchers;

import com.api.constants.Role;
import com.api.request.model.UserCredential;

import io.restassured.http.ContentType;

public class AuthTokenProvider {
	private static Map<Role, String> tokenCache=new ConcurrentHashMap<Role, String>();
	private AuthTokenProvider() {
		// TODO Auto-generated constructor stub
	}
	public static String getToken(Role role) {
		
		if(tokenCache.containsKey(role))
			return tokenCache.get(role);
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
		tokenCache.put(role, token);
		return token;
	}
	
	
}
