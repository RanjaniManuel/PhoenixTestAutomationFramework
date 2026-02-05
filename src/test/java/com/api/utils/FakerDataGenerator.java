package com.api.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

import io.qameta.allure.Step;

public class FakerDataGenerator {
	private static final Logger LOGGER=LogManager.getLogger(FakerDataGenerator.class);
	
	private static final String COUNTRY = "India";
	private static Faker faker = new Faker(new Locale("en-IND"));
	private static Random random = new Random();
	private static final int MST_SERVICE_LOCATION_ID = 0;
	private static final int MST_PLATFORM_ID = 2;
	private static final int MST_WARRENTY_STATUS_ID = 1;
	private static final int MST_OEM_ID = 1;
	private static final int PRODUCT_ID = 1;
	private static final int MST_MODEL_ID = 1;

	private static final int[] VALIDPROBLEMID = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 15, 16, 17, 19, 20, 22, 24, 26,
			27, 28, 29 };

	private FakerDataGenerator() {
		// TODO Auto-generated constructor stub
	}
	@Step("Generating Fake data to Create Job")
	public static CreateJobPayload generateFakeCreateJobData() {
		LOGGER.info("Generating Fake payload for Create Job ");

		Customer customer = generateFakeCustomerData();
		CustomerAddress customerAddress = generateFakeCustomerAddressData();
		CustomerProduct customerProduct = generateFakeCustomerProductData();
		List<Problems> problemList = generateFakeListProblems();

		return new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID, MST_WARRENTY_STATUS_ID, MST_OEM_ID,
				customer, customerAddress, customerProduct, problemList);

	}
	@Step("Generating multiple Fake data to Create Jobs")
	public static Iterator<CreateJobPayload> generateFakeCreateJobData(int count) {
		LOGGER.info("Generating Fake {} payloads for Create Job ",count);
		List<CreateJobPayload> payLoadList = new ArrayList<CreateJobPayload>();
		for (int i = 0; i < count; i++) {
			Customer customer = generateFakeCustomerData();
			CustomerAddress customerAddress = generateFakeCustomerAddressData();
			CustomerProduct customerProduct = generateFakeCustomerProductData();
			List<Problems> problemList = generateFakeListProblems();

			CreateJobPayload createJobPayload = new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID,
					MST_WARRENTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemList);
			payLoadList.add(createJobPayload);

		}
		return payLoadList.iterator();
	}
	@Step("Generating Fake list of problems for Create job payload")
	private static List<Problems> generateFakeListProblems() {

		int count = random.nextInt(1, 4);
		String sentence;
		Problems problems;
		int randomIndex;
		List<Problems> list = new ArrayList<Problems>();
		for (int i = 0; i < count; i++) {
			sentence = faker.lorem().sentence(5);
			randomIndex = random.nextInt(23);
			problems = new Problems(VALIDPROBLEMID[randomIndex], sentence);
			list.add(problems);
		}
		return list;
	}
	@Step("Generating Fake Customer Product data for Create job payload")
	private static CustomerProduct generateFakeCustomerProductData() {

		String dop = DateTimeUtil.getTimeWithDaysAgo(10);
		String serial_number = faker.numerify("1#############");
		String popurl = faker.internet().url();

		return new CustomerProduct(dop, serial_number, serial_number, serial_number, popurl, PRODUCT_ID, MST_MODEL_ID);

	}
	@Step("Generating Fake Customer Address data for Create job payload")
	private static CustomerAddress generateFakeCustomerAddressData() {
		String flat_number = faker.numerify("###");
		String apartment_name = faker.address().streetName();
		String street_name = faker.address().streetName();
		String landmark = faker.address().streetName();
		String area = faker.address().streetName();
		String pincode = faker.numerify("#####");

		String state = faker.address().state();
		CustomerAddress customerAddress = new CustomerAddress(flat_number, apartment_name, street_name, landmark, area,
				pincode, COUNTRY, state);
		return customerAddress;
	}
	@Step("Generating Fake Customer data for Create job payload")
	private static Customer generateFakeCustomerData() {
		String first_name = faker.name().firstName();
		String last_name = faker.name().lastName();
		String mobile_number = faker.numerify("70########");
		String mobile_number_alt = faker.numerify("98########");
		String email_id = faker.internet().emailAddress();
		String email_id_alt = faker.internet().emailAddress();
		Customer customer = new Customer(first_name, last_name, mobile_number, mobile_number_alt, email_id,
				email_id_alt);
		return customer;
	}

}
