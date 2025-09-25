package com.api.utils;

import org.hamcrest.Matchers;

import com.api.constants.Role;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {
	
	//get- del
	public static RequestSpecification requestSpec() {
		RequestSpecification requestSpecification = new RequestSpecBuilder()
					.setBaseUri(ConfigManager.getProperty("BASE_URI"))
					.setContentType(ContentType.JSON)
					.setAccept(ContentType.JSON)
					.log(LogDetail.URI)
					.log(LogDetail.HEADERS)
					.log(LogDetail.METHOD)
					.log(LogDetail.BODY)
					.build();
		return requestSpecification;
		
	}
	//post, put, patch
	public static RequestSpecification requestSpec(Object credential) {
		RequestSpecification requestSpecification = new RequestSpecBuilder()
					.setBaseUri(ConfigManager.getProperty("BASE_URI"))
					.setContentType(ContentType.JSON)
					.setAccept(ContentType.JSON)
					.setBody(credential)
					.log(LogDetail.URI)
					.log(LogDetail.HEADERS)
					.log(LogDetail.METHOD)
					.log(LogDetail.BODY)
					.build();
		return requestSpecification;
		
	}
	public static RequestSpecification requestSpecWithAuth(Role role) {
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getToken(role))
				.log(LogDetail.URI)
				.log(LogDetail.HEADERS)
				.log(LogDetail.METHOD)
				.log(LogDetail.BODY)
				.build();
		return requestSpecification;
		
	}
	public static ResponseSpecification responseSpec_OK() {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.expectResponseTime(Matchers.lessThan(15000L))
				.log(LogDetail.ALL)
				.build();
		return responseSpecification;
	}
	public static ResponseSpecification responseSpec_Json(int statusCode) {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
				.expectStatusCode(statusCode)
				.expectContentType(ContentType.JSON)
				.expectResponseTime(Matchers.lessThan(15000L))
				.log(LogDetail.ALL)
				.build();
		return responseSpecification;
	}
	public static ResponseSpecification responseSpec_Text(int statusCode) {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
				.expectStatusCode(statusCode)
				.expectContentType(ContentType.HTML)
				.expectResponseTime(Matchers.lessThan(15000L))
				.log(LogDetail.ALL)
				.build();
		return responseSpecification;
	}

}
