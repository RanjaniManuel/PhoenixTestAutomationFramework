package com.aoi.filters;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class SensitiveDataFilter implements Filter{
		private static final Logger LOGGER=LogManager.getLogger(SensitiveDataFilter.class);

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		System.out.println("_____________________________-Fileter_______________________________");
		
		LOGGER.info("*********************************REQUEST DETAILS*********************************");
		LOGGER.info("BASE URI : {}",requestSpec.getURI());
		LOGGER.info("HTTP METHOD: {}",requestSpec.getMethod());
		redactPayLoad(requestSpec);
		redactHeader(requestSpec);
		Response response = ctx.next(requestSpec, responseSpec);
		
		LOGGER.info("*********************************RESPONDS DETAILS*********************************");
		LOGGER.info("STATUS : {}",response.getStatusLine());
		LOGGER.info("RESPONSE TIME ms: {}",response.timeIn(TimeUnit.MILLISECONDS));
		LOGGER.info("RESPONSE HEADERS: \n{}",response.getHeaders());
		redactResponseBody(response);
		return response;
	}

	private void redactHeader(FilterableRequestSpecification requestSpec) {
		List<Header> headerList = requestSpec.getHeaders().asList();
		for(Header header: headerList) {
			if(header.getName().equalsIgnoreCase("Authorization"))
				LOGGER.info("REQUEST HEADER {} : {}",header.getName().toUpperCase(),"\"[REDACTED]\"");
			else
				LOGGER.info("REQUEST HEADER {} : {}",header.getName().toUpperCase(),header.getValue());
								
		}
		
	}

	private void redactPayLoad(FilterableRequestSpecification requestSpec) {
		if( requestSpec.getBody()!=null) {
		String requestPayload = requestSpec.getBody().toString();
		requestPayload=requestPayload.replaceAll("\"password\"\s*:\s*\"[^\"]+\"", "\"password\" : \"[REDACTED]\"");
		LOGGER.info("Request Payload \n{}",requestPayload);
		}
	}
	private void redactResponseBody(Response response) {
		String responsePayload = response.asPrettyString();
		responsePayload=responsePayload.replaceAll("\"token\"\s*:\s*\"[^\"]+\"", "\"token\" : \"[REDACTED]\"");
		LOGGER.info("Request Payload \n{}",responsePayload);
	}

	
}
