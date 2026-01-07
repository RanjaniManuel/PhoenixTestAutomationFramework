package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.services.JobService;
import com.api.utils.FakerDataGenerator;
import com.api.utils.SpecUtil;
import com.database.dao.CustomerAddressDao;
import com.database.dao.CustomerDao;
import com.db.model.CustomerAddressDBModel;
import com.db.model.CustomerDBModel;

import io.restassured.module.jsv.JsonSchemaValidator;
// created by Ranjani
public class CreateJobAPITestWithFakeData {
	private CreateJobPayload createJobPayload;
	private JobService jobService ;
	

	@BeforeMethod(description = "Instantiating Job Service class")
	public void setUp() {
				
		 createJobPayload=FakerDataGenerator.generateFakeCreateJobData();
		 jobService=new JobService();
		}

	@Test(description = "verifying the Create Api is able to create a new job", groups = { "api", "regression",
			"smoke" })
	public void createJobApi() {

		int customerId = jobService.create(Role.FD, createJobPayload)
				.then()
				.spec(SpecUtil.responseSpec_OK())
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/createJobAPIResponseSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_"))
				.extract().body().jsonPath().getInt("data.tr_customer_id");
		
		
		Customer expectedCustomer = createJobPayload.customer();//record
		CustomerDBModel actualCustomer = CustomerDao.getCustomerInfo(customerId);
		Assert.assertEquals(actualCustomer.getFirst_name(), expectedCustomer.first_name());
		Assert.assertEquals(actualCustomer.getLast_name(), expectedCustomer.last_name());
		Assert.assertEquals(actualCustomer.getEmail_id(), expectedCustomer.email_id());
		Assert.assertEquals(actualCustomer.getEmail_id_alt(), expectedCustomer.email_id_alt());
		Assert.assertEquals(actualCustomer.getMobile_number(), expectedCustomer.mobile_number());
		Assert.assertEquals(actualCustomer.getMobile_number_alt(), expectedCustomer.mobile_number_alt());
		
		
		CustomerAddress customerAddress=createJobPayload.customer_address();
		CustomerAddressDBModel addressFromDB=CustomerAddressDao.getAddress(actualCustomer.getTr_customer_address_id());
		
		Assert.assertEquals(addressFromDB.getApartment_name(), customerAddress.apartment_name(),"APARTMENT_NAME IS WORNG");
		Assert.assertEquals(addressFromDB.getArea(), customerAddress.area(),"AREA IS WORNG");
		Assert.assertEquals(addressFromDB.getStreet_name(), customerAddress.street_name(),"STREET NAME IS WORNG");
		Assert.assertEquals(addressFromDB.getLandmark(), customerAddress.landmark(),"LANDMARK IS WORNG");
		Assert.assertEquals(addressFromDB.getPincode(), customerAddress.pincode(),"PIN CODE IS WORNG");
		Assert.assertEquals(addressFromDB.getState(), customerAddress.state(),"STATE IS WORNG");
		Assert.assertEquals(addressFromDB.getCountry(), customerAddress.country(),"COUNTRY IS WORNG");
		Assert.assertEquals(addressFromDB.getFlat_number(), customerAddress.flat_number(),"FLAT NUMBER IS WORNG");
		
		
		

	}

}
