package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.SearchPayload;
import com.api.services.JobService;
import com.api.utils.SpecUtil;

public class SearchAPITest {
	
	private JobService jobService;
	private SearchPayload searchPayload;
	private static final String JOB_NUMBER="JOB_107742";
	
	@BeforeMethod(description = "Instantiating Job Service and Creating object for Search job payload")
	public void setup() {
		jobService =new JobService();
		searchPayload=new SearchPayload(JOB_NUMBER);
		
	}
	
	@Test(description = "verifying if SearchApi is working ",groups = { "api", "regression",
			"smoke" })
	public void searchAPITest() {
		jobService.search(Role.FD, searchPayload)
		.then()
		.log().ifValidationFails()
		.spec(SpecUtil.responseSpec_OK())
		.body("data.size", Matchers.greaterThan(0));
		
	}

}
