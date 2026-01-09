package com.api.services;

import com.api.constants.Role;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DashboardService {
		public static final String COUNT_ENDPOINT="/dashboard/count";
		public static final String DETAIL_ENDPOINT="/dashboard/details";
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
		public Response details(Role	role,Object payload) {
			return RestAssured.given().spec(SpecUtil.requestSpecWithAuth(role, payload)).post(DETAIL_ENDPOINT);
		}
}
