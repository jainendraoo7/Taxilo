package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Customer;
import com.exception.CustomerException;
import com.service.CustomerService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class MyController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private PasswordEncoder passwordEncoder; 
	
	@PostMapping("/register")
	public ResponseEntity<Customer> customerRegisterHandller(@RequestBody Customer customer){
		
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		
//		customer.setRole("ROLE_"+customer.getRole());
		
		Customer c1 =  customerService.registerCustomer(customer);
		
		return new ResponseEntity<>(c1,HttpStatus.CREATED); 
		
	}
	
	@GetMapping("/username/{email}")
	public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) throws CustomerException {
		
		Customer c1 =  customerService.getCustomerByUsername(email);
		
		return new ResponseEntity<>(c1, HttpStatus.ACCEPTED); 
		
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Customer>> getAllCustomer(){
		
		List<Customer> c1 = customerService.allCustomer();
		
		return new ResponseEntity<>(c1, HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/delete/{customerId}")
	public ResponseEntity<Customer> driverDelete(@PathVariable Integer customerId) throws CustomerException{
		
	 Customer d1 = customerService.deleteCustomerById(customerId);
	 
	 return new ResponseEntity<>(d1, HttpStatus.ACCEPTED);
		
	}
	
	
	
	@PutMapping("/update/{customerId}")
	public ResponseEntity<Customer> customerUpdateHandller(@PathVariable Integer customerId, @RequestBody Customer customerDetails) throws CustomerException{
		
		
		Customer c1 =  customerService.updateCustomer(customerId, customerDetails);
		
		return new ResponseEntity<>(c1,HttpStatus.CREATED); 
		
	}
	

	
}
