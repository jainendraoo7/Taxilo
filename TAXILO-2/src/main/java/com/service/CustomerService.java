package com.service;

import java.util.List;

import com.entity.Customer;
import com.exception.CustomerException;

public interface CustomerService {

	public Customer registerCustomer(Customer customer);
	
	public List<Customer> allCustomer();
	
	public Customer getCustomerByUsername(String email) throws CustomerException; 

	public Customer deleteCustomerById(int customerId) throws CustomerException;
	
	public Customer updateCustomer(int customerId, Customer customerDetails) throws CustomerException;
}
