package com.api.tests.datadriven;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
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
import com.database.dao.CustomerAddressDao;
import com.database.dao.CustomerDao;
import com.database.dao.CustomerProductDao;
import com.db.model.CustomerAddressDBModel;
import com.db.model.CustomerDBModel;
import com.db.model.CustomerProductDBModel;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
@Listeners(com.listeners.APITestListener.class)
// created by Ranjani
public class CreateJobAPIWithDBValidationTest {
	private CreateJobPayload createJobPayload;
	private Customer customer ;
	private CustomerAddress customerAddress;
	private CustomerProduct customerProduct;
	private JobService jobService ;

	@BeforeMethod(description = "Creating CreateJob Api request payload")
	public void setUp() {
		Random random = new Random();

		// Minimum 14-digit number
		long min = 10000000000000L;
		long max = 99999999999999L;
		long number = min + (long) (random.nextDouble() * (max - min));

		 customer = new Customer("Ahi", "Rana", "9874563215", "8956231452", "ahi@gmail.com", "rana@gmail.com");
		 customerAddress = new CustomerAddress("789", "Sai parivar", "M N Nmbiyar", "good Area",
				"Madurai", "987456", "India", "TamilNadu");
		 customerProduct = new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(10),
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
		
		Response response=jobService.create(Role.FD, createJobPayload)
				.then()
				.spec(SpecUtil.responseSpec_OK())
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/createJobAPIResponseSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_"))
				.extract().response();
		//.extract().body().jsonPath().getInt("data.tr_customer_id");
		
		
		System.out.println("___________________________________________________________________");
		
		int customerId=response.body().jsonPath().getInt("data.tr_customer_id");
		System.out.println(customerId);
		
		
		int productId=response.body().jsonPath().getInt("data.tr_customer_product_id");
		
		CustomerDBModel customerDB = CustomerDao.getCustomerInfo(customerId);
		Assert.assertEquals( customerDB.getFirst_name(),customer.first_name());
		Assert.assertEquals(customerDB.getLast_name(),customer.last_name());
		Assert.assertEquals( customerDB.getEmail_id(),customer.email_id());
		Assert.assertEquals( customerDB.getEmail_id_alt(),customer.email_id_alt());
		Assert.assertEquals( customerDB.getMobile_number(),customer.mobile_number());
		Assert.assertEquals( customerDB.getMobile_number_alt(),customer.mobile_number_alt());
		
		CustomerAddressDBModel addressFromDB = CustomerAddressDao.getAddress(customerDB.getTr_customer_address_id());
		
		Assert.assertEquals(addressFromDB.getApartment_name(), customerAddress.apartment_name());
		Assert.assertEquals(addressFromDB.getArea(), customerAddress.area());
		Assert.assertEquals(addressFromDB.getStreet_name(), customerAddress.street_name());
		Assert.assertEquals(addressFromDB.getLandmark(), customerAddress.landmark());
		Assert.assertEquals(addressFromDB.getPincode(), customerAddress.pincode());
		Assert.assertEquals(addressFromDB.getState(), customerAddress.state());
		Assert.assertEquals(addressFromDB.getCountry(), customerAddress.country());
		Assert.assertEquals(addressFromDB.getFlat_number(), customerAddress.flat_number());
		
		CustomerProductDBModel customerProductFromDB = CustomerProductDao.getCustomerProductDetail(productId);
		System.out.println(customerProductFromDB);
		
		
		Assert.assertEquals(customerProductFromDB.getDop(), customerProduct.dop());
		Assert.assertEquals(customerProductFromDB.getSerial_number(), customerProduct.serial_number());
		Assert.assertEquals(customerProductFromDB.getImei1(), customerProduct.imei1());
		Assert.assertEquals(customerProductFromDB.getImei2(), customerProduct.imei2());
		Assert.assertEquals(customerProductFromDB.getPopurl(), customerProduct.popurl());
		
		Assert.assertEquals(customerProductFromDB.getMst_model_id(), customerProduct.mst_model_id());
		
		
		
		
		
	}

}
