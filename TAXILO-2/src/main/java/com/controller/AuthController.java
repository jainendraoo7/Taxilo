package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.config.JwtTokenUtil;
import com.entity.Customer;
import com.entity.Driver;
import com.entity.JwtAuthenticationResponse;
import com.entity.SignInRequest;
import com.exception.CustomerException;
import com.exception.DriverException;
import com.service.CustomUserDetailsService;
import com.service.CustomerService;
import com.service.DriverService;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @Autowired
    private CustomerService customerService; 

    @Autowired
    private DriverService driverService;
    
    public String jwtToken;
    
    @PostMapping("/signIn")
    public ResponseEntity<?> authenticateUser(@RequestBody SignInRequest signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(signInRequest.getEmail());
         jwtToken = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwtToken));
        
        
    }
    
    @GetMapping("/current-user")
    public Customer getCustomer() throws CustomerException {
    	
    	
       
            String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            Customer customer = customerService.getCustomerByUsername(username);
            
            return customer;
           
         
    }
    
    @GetMapping("/current-users")
    public Driver getDriver() throws DriverException {
    	
        String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
      Driver driver = driverService.getDriverByUsername(username);
      
      return driver;
        
    	
    }


    
//    @GetMapping("/current-user")
//    public User getCustomers(Principal principal) {
//    	
//    	
//    	
//    	return (User) this.userDetailsService.loadUserByUsername(principal.getName());
//    	
//    	
//    }
   
}
