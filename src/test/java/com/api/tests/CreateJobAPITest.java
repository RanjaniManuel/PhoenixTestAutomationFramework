package com.api.tests;

import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.pojos.CreateJobPojo;
import com.api.pojos.Customer;
import com.api.pojos.CustomerAddress;
import com.api.pojos.CustomerProduct;
import com.api.pojos.Problem;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;

public class CreateJobAPITest {
	@Test
	public void createJobApi() {
		Customer customer=new Customer("Ahi", "Rana", "9874563215", "8956231452", "ahi@gmail.com", "rana@gmail.com");
		CustomerAddress customerAddress=new CustomerAddress("789", "Sai parivar", "M N Nmbiyar", "good Area", "Madurai", "987456", "India", "TamilNadu");
		CustomerProduct customerProduct=new CustomerProduct("2025-04-08T04:00:00.000Z", "102499984280289", "102499984280280", "102499984280289", "2025-04-08T04:00:00.000Z", 3, 3);
		
		Problem problem=new Problem(1, "Slow");
		Problem[] problemArray=new Problem[1];
		problemArray[0]=problem;
		CreateJobPojo createJobPojo=new CreateJobPojo(0, 2, 1, 2, customer, customerAddress, customerProduct, problemArray);
		 RestAssured.given()
		 		.spec(SpecUtil.requestSpecWithAuth(Role.FD,createJobPojo))
		 		.when()
		 			.post("job/create")
		 		.then()
		 		.spec(SpecUtil.responseSpec_OK());
		 		
	}

}
