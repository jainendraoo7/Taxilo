package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.entity.Customer;
import com.exception.CustomerException;
import com.repository.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService  {

	@Autowired
	private CustomerRepo customerRepo;
	
	
	@Override
	public Customer registerCustomer(Customer customer) {
		
		return customerRepo.save(customer);
		
	}


	@Override
	public List<Customer> allCustomer() {
		return customerRepo.findAll();
	}


	@Override
	public Customer getCustomerByUsername(String email) throws CustomerException {
		
		Optional<Customer> opt = customerRepo.findByUsername(email);
	
		if(opt.isPresent()) {
			
			Customer cus = opt.get();
			
			return cus;
			
		}
		
		else 
			throw new CustomerException("User Not Found By This Email: "+ email);
		
	}


	@Override
	public Customer deleteCustomerById(int customerId) throws CustomerException {
		Optional<Customer> opt = customerRepo.findById(customerId);
		
		if(opt.isPresent()) {
			
			Customer cus = opt.get();
			
			customerRepo.delete(cus);
			
			return cus;
			
		}
		
		else 
			throw new CustomerException("User Not Found By This Id: "+customerId);
	}


	@Override
	public Customer updateCustomer(int customerId, Customer customerDetails) throws CustomerException {
    
		Optional<Customer> opt = customerRepo.findById(customerId);
		
		if(opt.isPresent()) {
			
			Customer cus = opt.get();
			
		     cus.setName(customerDetails.getName());
		     cus.setEmail(customerDetails.getEmail());
		     cus.setMobile(customerDetails.getMobile());
			
		     customerRepo.save(cus);
		     
			return cus;
			
		}
		
		else 
			throw new CustomerException("User Not Found By This Id: "+customerId);
	}
	
	
	
}
