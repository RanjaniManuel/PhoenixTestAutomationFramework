package com.api.services;

import com.api.constants.Role;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DashboardService {
		public static final String COUNT_ENDPOINT="/dashboard/count";
		public Response count(Role role) {
			return RestAssured.given()
			.spec(SpecUtil.requestSpecWithAuth(role))
			.when()
			.get(COUNT_ENDPOINT);
		}
		public Response count() {
			return RestAssured.given()
					.spec(SpecUtil.requestSpec())
					.when()
					.get(COUNT_ENDPOINT);
		}
}
