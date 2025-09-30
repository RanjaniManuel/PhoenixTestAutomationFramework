package com.api.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.pojos.CreateJobPojo;
import com.api.pojos.Customer;
import com.api.pojos.CustomerAddress;
import com.api.pojos.CustomerProduct;
import com.api.pojos.Problem;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {
	@Test
	public void createJobApi() {
		Random random = new Random();
        
        // Minimum 14-digit number
        long min = 10000000000000L; 
        long max = 99999999999999L;        
        long number = min + (long)(random.nextDouble() * (max - min));
        
		Customer customer=new Customer("Ahi", "Rana", "9874563215", "8956231452", "ahi@gmail.com", "rana@gmail.com");
		CustomerAddress customerAddress=new CustomerAddress("789", "Sai parivar", "M N Nmbiyar", "good Area", "Madurai", "987456", "India", "TamilNadu");
		CustomerProduct customerProduct=new CustomerProduct("2025-04-08T04:00:00.000Z", String.valueOf(number),  String.valueOf(number),  String.valueOf(number) , "2025-04-08T04:00:00.000Z", 3, 3);
		
		Problem problem=new Problem(1, "Slow");
		List<Problem> problemArray=new ArrayList<Problem>();
		problemArray.add(problem);
		CreateJobPojo createJobPojo=new CreateJobPojo(0, 2, 1, 2, customer, customerAddress, customerProduct, problemArray);
		 RestAssured.given()
		 		.spec(SpecUtil.requestSpecWithAuth(Role.FD,createJobPojo))
		 		.when()
		 			.post("job/create")
		 		.then()		 		
		 		.spec(SpecUtil.responseSpec_OK())
		 		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/createJobAPIResponseSchema.json"))
		 		.body("message", Matchers.equalTo("Job created successfully. "))
		 		.body("data.mst_service_location_id", Matchers.equalTo(1))
		 		.body("data.job_number", Matchers.startsWith("JOB_"));
		 	
		 		
	}

}
