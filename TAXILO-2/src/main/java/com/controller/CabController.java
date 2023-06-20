package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Cab;
import com.entity.Driver;
import com.exception.CabException;
import com.service.CabService;

@RestController
@RequestMapping("/cab")
@CrossOrigin
public class CabController {

	@Autowired
	private CabService cabService;
	
	@PostMapping("/register")
	public ResponseEntity<Cab> registerCab(@RequestBody Cab cab){
		
		Cab c1 = cabService.registerCab(cab);
		
		return new ResponseEntity<>(c1, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Cab>> getAllCabs(){

		List<Cab> c1 = cabService.getAllCabs();
		
		return new ResponseEntity<>(c1, HttpStatus.ACCEPTED);	
		
	}
	
	@DeleteMapping("/delete/{cabId}")
	public ResponseEntity<Cab> deleteCab(@PathVariable Integer cabId) throws CabException{

		Cab c1 = cabService.deleteCab(cabId);
		
		return new ResponseEntity<>(c1, HttpStatus.ACCEPTED);	
		
	}
	
	@PutMapping("/update/{cabId}")
	public ResponseEntity<Cab> deleteCab(@PathVariable Integer cabId, @RequestBody Cab cabDetails) throws CabException{

		Cab c1 = cabService.updateCab(cabId, cabDetails);
		
		return new ResponseEntity<>(c1, HttpStatus.ACCEPTED);	
		
	}
	
	@GetMapping("/getCab/{cabNumber}")
     public ResponseEntity<Driver> getDriverByCabId(@PathVariable String cabNumber) throws CabException{
    	 
    	 Driver d1 = cabService.getDriverCabDetails(cabNumber);
    	 
    	 return new ResponseEntity<>(d1, HttpStatus.ACCEPTED);
    	 
     }
	
	
	
}

