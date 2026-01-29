package com.aoi.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class SensitiveDataFilter implements Filter{
		private static final Logger LOGGER=LogManager.getLogger(SensitiveDataFilter.class);

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		System.out.println("_____________________________-Fileter_______________________________");
		redactPayLoad(requestSpec);
		Response response = ctx.next(requestSpec, responseSpec);
		redactResponseBody(response);
		return response;
	}

	private void redactPayLoad(FilterableRequestSpecification requestSpec) {
		String requestPayload = requestSpec.getBody().toString();
		requestPayload=requestPayload.replaceAll("\"password\"\s*:\s*\"[^\"]+\"", "\"password\" : \"[REDACTED]\"");
		LOGGER.info("Request Payload {}",requestPayload);
	}
	private void redactResponseBody(Response response) {
		String responsePayload = response.asPrettyString();
		responsePayload=responsePayload.replaceAll("\"token\"\s*:\s*\"[^\"]+\"", "\"token\" : \"[REDACTED]\"");
		LOGGER.info("Request Payload {}",responsePayload);
	}

	
}
