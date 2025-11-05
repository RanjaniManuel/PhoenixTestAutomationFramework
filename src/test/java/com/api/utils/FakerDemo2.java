package com.api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

public class FakerDemo2 {
	public static final String COUNTRY = "India";

	public static void main(String[] args) {
		// we will be using faker library for out fake test data creation
		//
		Faker faker = new Faker(new Locale("en-IND"));
		String first_name = faker.name().firstName();
		String last_name = faker.name().lastName();
		String mobile_number = faker.numerify("70########");
		String mobile_number_alt = faker.numerify("98########");
		String email_id = faker.internet().emailAddress();
		String email_id_alt = faker.internet().emailAddress();
		Customer customer = new Customer(first_name, last_name, mobile_number, mobile_number_alt, email_id,
				email_id_alt);

		System.out.println(customer);

		String flat_number = faker.numerify("###");
		String apartment_name = faker.address().streetName();
		String street_name = faker.address().streetName();
		String landmark = faker.address().streetName();
		String area = faker.address().streetName();
		String pincode = faker.numerify("######");

		String state = faker.address().state();
		CustomerAddress customerAddress = new CustomerAddress(flat_number, apartment_name, street_name, landmark, area,
				pincode, COUNTRY, state);
		System.out.println(customerAddress);
		
		String dop=DateTimeUtil.getTimeWithDaysAgo(10) ;
		String serial_number=faker.numerify("1#############"); 
		
		String popurl=faker.internet().url(); 
		
		
		CustomerProduct customerProduct=new CustomerProduct(dop, serial_number, serial_number, serial_number, popurl, 1, 1);
		System.out.println(customerProduct);
		
		
		String sentence = faker.lorem().sentence(5);
		Random random=new Random();
		int problemId = random.nextInt(1, 28);
		
		
		Problems problems=new Problems(problemId, sentence);
		List<Problems> problemList=new ArrayList<Problems>();
		problemList.add(problems);
		
		CreateJobPayload createJobPayload=new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);
		System.out.println(createJobPayload);
		
		

	}
}
