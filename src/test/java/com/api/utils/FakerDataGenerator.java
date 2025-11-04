package com.api.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

public class FakerDataGenerator {
	private static final String COUNTRY = "India";
	private static Faker faker = new Faker(new Locale("en-IND"));
	private static Random random = new Random();
	private static final int MST_SERVICE_LOCATION_ID = 0;
	private static final int MST_PLATFORM_ID = 2;
	private static final int MST_WARRENTY_STATUS_ID = 1;
	private static final int MST_OEM_ID = 1;
	private static final int PRODUCT_ID = 1;
	private static final int MST_MODEL_ID = 1;

	private FakerDataGenerator() {
		// TODO Auto-generated constructor stub
	}

	public static CreateJobPayload generateFakeCreateJobData() {

		Customer customer = generateFakeCustomerData();
		CustomerAddress customerAddress = generateFakeCustomerAddressData();
		CustomerProduct customerProduct = generateFakeCustomerProductData();
		List<Problems> problemList = generateFakeListProblems();

		return new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID, MST_WARRENTY_STATUS_ID, MST_OEM_ID,
				customer, customerAddress, customerProduct, problemList);

	}

	public static Iterator<CreateJobPayload> generateFakeCreateJobData(int count) {
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

	private static List<Problems> generateFakeListProblems() {

		String sentence = faker.lorem().sentence(5);
		int problemId = random.nextInt(1, 28);

		Problems problems = new Problems(problemId, sentence);

		List<Problems> list = new ArrayList<Problems>();
		list.add(problems);

		return list;
	}

	private static CustomerProduct generateFakeCustomerProductData() {

		String dop = DateTimeUtil.getTimeWithDaysAgo(10);
		String serial_number = faker.numerify("1#############");
		String popurl = faker.internet().url();

		return new CustomerProduct(dop, serial_number, serial_number, serial_number, popurl, PRODUCT_ID, MST_MODEL_ID);

	}

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
