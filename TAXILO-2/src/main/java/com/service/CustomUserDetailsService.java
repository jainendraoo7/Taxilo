package com.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.entity.Customer;
import com.entity.Driver;
import com.repository.CustomerRepo;
import com.repository.DriverRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepo customerRepository;
    
    @Autowired
    private DriverRepo driverRepo;
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
    	Optional<Customer> opt = customerRepository.findByUsername(username);
        
    	Optional<Driver> opt2 = driverRepo.findByUsername(username);
    	
        
        if(opt.isPresent()) {
        
            Customer customer =  opt.get();
    
            return User.builder()
                    .username(customer.getEmail())
                    .password(customer.getPassword())
                    .roles(customer.getRole())
                    .build();
            
        }
        
        else if (opt2.isPresent()) {

        	   Driver driver  = opt2.get();
        	   
        	   return User.builder()
                       .username(driver.getEmail())
                       .password(driver.getPassword())
                       .roles(driver.getRole())
                       .build();
        	   
        }
        else 
        	
        throw new UsernameNotFoundException("Invalid username or password.");
    
    }
}

