package com.api.utils;

import java.util.Locale;

import com.github.javafaker.Faker;

public class FakerDemo {
	public static void main(String[] args) {
		//we will be using faker library for out fake test data creation
		//
		Faker faker=new Faker(new Locale("en-IND"));
		String firstName = faker.name().firstName();
		System.out.println(firstName);
		
		String lastName = faker.name().lastName();
		System.out.println(lastName);
		
		//building name
		
		String buildingNumber = faker.address().buildingNumber();
		System.out.println(buildingNumber);
		System.out.println(faker.address().streetAddress());
		System.out.println(faker.address().city());
		System.out.println(faker.address().cityName());
		
		
		//digits
		
		System.out.println(faker.number().digit());
		System.out.println(faker.number().digits(10));
		
		//numerity
		
		System.out.println(faker.numerify("678-###-####"));
		System.out.println(faker.numerify("678 ### ####"));
		
		//email
		System.out.println(faker.internet().emailAddress());
		
		//phone number
		System.out.println(faker.phoneNumber().cellPhone());
		System.out.println(faker.phoneNumber().phoneNumber());
	}
}
