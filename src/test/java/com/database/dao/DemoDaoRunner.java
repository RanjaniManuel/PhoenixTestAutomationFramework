package com.database.dao;

import java.sql.SQLException;

import org.testng.Assert;

import com.api.request.model.Customer;
import com.db.model.CustomerDBModel;

public class DemoDaoRunner {
	public static void main(String[] args) throws SQLException {
		CustomerDBModel customer = CustomerDao.getCustomerInfo();
		System.out.println(customer.getFirst_name());
		System.out.println(customer.getLast_name());
		System.out.println(customer.getEmail_id());
		System.out.println(customer.getMobile_number());
		System.out.println(customer.getMobile_number_alt());
		Customer customerRecord=new Customer("first_name", "lastName", "7894561236", "", "rr@gmail.com", "");
		Assert.assertEquals(customer.getFirst_name(), customerRecord.first_name());
	}
}
