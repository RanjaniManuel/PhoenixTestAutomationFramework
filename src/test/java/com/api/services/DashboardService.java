package com.api.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constants.Role;
import com.api.utils.SpecUtil;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DashboardService {
		private static final Logger LOGGER=LogManager.getLogger(DashboardService.class);

		public static final String COUNT_ENDPOINT="/dashboard/count";
		public static final String DETAIL_ENDPOINT="/dashboard/details";
		@Step("Making Count API Request for the role")
		public Response count(Role role) {
			LOGGER.info("Making request to the {} for the {}",COUNT_ENDPOINT, role);
			return RestAssured.given()
			.spec(SpecUtil.requestSpecWithAuth(role))
			.when()
			.get(COUNT_ENDPOINT);
		}
		@Step(" Making Count API request without Auth token")
		public Response count() {
			LOGGER.info("Making request to the {} with no Authenticate token",COUNT_ENDPOINT);
			return RestAssured.given()
					.spec(SpecUtil.requestSpec())
					.when()
					.get(COUNT_ENDPOINT);
		}
		@Step("Making details API Request")
		public Response details(Role	role,Object payload) {
			LOGGER.info("Making request to the {} for the Role {} and Payload {}",DETAIL_ENDPOINT,role,payload);
			return RestAssured.given().spec(SpecUtil.requestSpecWithAuth(role, payload)).post(DETAIL_ENDPOINT);
		}
}
