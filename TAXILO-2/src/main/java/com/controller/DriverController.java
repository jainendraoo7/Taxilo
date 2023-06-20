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

import com.entity.Driver;
import com.exception.CabException;
import com.exception.DriverException;
import com.service.DriverService;

@RestController
@RequestMapping("/driver")
@CrossOrigin
public class DriverController {

	@Autowired
	private DriverService driverService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public ResponseEntity<Driver> registerDriverHandller(@RequestBody Driver driver){
		
		driver.setPassword(passwordEncoder.encode(driver.getPassword()));
		
		Driver d1 = driverService.registerDriver(driver);
		
		return new ResponseEntity<>(d1,HttpStatus.CREATED);
		
	}
	
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Driver>> getAll(){
		
	 List<Driver> d1 = driverService.getAllDriver();
	 
	 return new ResponseEntity<>(d1, HttpStatus.ACCEPTED);
		
	}
	
	
	@DeleteMapping("/delete/{driverId}")
	public ResponseEntity<Driver> driverDelete(@PathVariable Integer driverId) throws DriverException{
		
	 Driver d1 = driverService.deleteDriverById(driverId);
	 
	 return new ResponseEntity<>(d1, HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/assign/{driverId}/{cabId}")
	public ResponseEntity<Driver> assignCab(@PathVariable Integer driverId, @PathVariable Integer cabId) throws DriverException, CabException{

		Driver c1 = driverService.assignCab(driverId, cabId);
		
		return new ResponseEntity<>(c1, HttpStatus.ACCEPTED);	
		
	}
	
	
	@GetMapping("/getDriver/{driverId}")
	public ResponseEntity<Driver> getDriverById(@PathVariable Integer driverId) throws DriverException{
		
	 Driver d1 = driverService.getDriverById(driverId);
	 
	 return new ResponseEntity<>(d1, HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/update/{driverId}")
	public ResponseEntity<Driver> updateDriverById(@PathVariable Integer driverId, @RequestBody Driver driver) throws DriverException{
		
	 Driver d1 = driverService.updateEmailAndPassword(driverId, driver);
	 
	 return new ResponseEntity<>(d1, HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/avail/{driverId}")
	public ResponseEntity<Driver> setAvalabilityOfDriver(@PathVariable Integer driverId) throws DriverException{
		
		 Driver d1 = driverService.setAvailability(driverId);
		 
		 return new ResponseEntity<>(d1, HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/disavail/{driverId}")
	public ResponseEntity<Driver> setDisAvalabilityOfDriver(@PathVariable Integer driverId) throws DriverException{
		
		 Driver d1 = driverService.setDisAvailability(driverId);
		 
		 return new ResponseEntity<>(d1, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/get/{cabType}/{available}")
	public ResponseEntity<Driver> getDriver(@PathVariable String cabType, @PathVariable boolean available){
		
		 Driver d1 = driverService.find(cabType, available);
		 
		 return new ResponseEntity<>(d1, HttpStatus.ACCEPTED);
		
	}
	
	
}


