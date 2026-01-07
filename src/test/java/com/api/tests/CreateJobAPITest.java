package com.api.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Model;
import com.api.constants.OEM;
import com.api.constants.Platform;
import com.api.constants.Problem;
import com.api.constants.Product;
import com.api.constants.Role;
import com.api.constants.ServiceCenter;
import com.api.constants.WarrentyStatus;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.services.JobService;
import com.api.utils.DateTimeUtil;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;
// created by Ranjani
public class CreateJobAPITest {
	private CreateJobPayload createJobPayload;	
	private JobService jobService ;
	
	

	@BeforeMethod(description = "Creating CreateJob Api request payload")
	public void setUp() {
		Random random = new Random();

		// Minimum 14-digit number
		long min = 10000000000000L;
		long max = 99999999999999L;
		long number = min + (long) (random.nextDouble() * (max - min));

		Customer customer = new Customer("Ahi", "Rana", "9874563215", "8956231452", "ahi@gmail.com", "rana@gmail.com");
		CustomerAddress customerAddress = new CustomerAddress("789", "Sai parivar", "M N Nmbiyar", "good Area",
				"Madurai", "987456", "India", "TamilNadu");
		CustomerProduct customerProduct = new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(10),
				String.valueOf(number), String.valueOf(number), String.valueOf(number),
				DateTimeUtil.getTimeWithDaysAgo(10), Product.NEXUS.getCode(), Model.NEXUS_2_BLUE.getCode());

		Problems problem = new Problems(Problem.CAMERA_ISSUE.getCode(), "Slow");
		List<Problems> problemArray = new ArrayList<Problems>();
		problemArray.add(problem);
		createJobPayload = new CreateJobPayload(ServiceCenter.SERVICE_CENTER_A.getCode(), Platform.FRONT_DESK.getCode(),
				WarrentyStatus.IN_WARRENTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress, customerProduct,
				problemArray);
		jobService=new JobService();
	}

	@Test(description = "verifying the Create Api is able to create a new job", groups = { "api", "regression",
			"smoke" })
	public void createJobApi() {
				 jobService.create(Role.FD, createJobPayload)
				.then()
				.spec(SpecUtil.responseSpec_OK())
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/createJobAPIResponseSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_"));

	}

}
