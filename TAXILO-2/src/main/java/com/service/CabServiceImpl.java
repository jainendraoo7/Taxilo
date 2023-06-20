package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.entity.Cab;
import com.entity.Driver;
import com.exception.CabException;
import com.repository.CabRepo;
import com.repository.DriverRepo;

@Service
public class CabServiceImpl implements CabService {

	@Autowired
	private CabRepo cabRepo;
	
	@Autowired
	private DriverRepo driverRepo;
	
	@Override
	public Cab registerCab(Cab cab) {
		
		return cabRepo.save(cab); 
		
	}

	@Override
	public List<Cab> getAllCabs() {
		
		return cabRepo.findAll();
		
	}

	@Override
	public Cab deleteCab(int cabId) throws CabException{
		
		Optional<Cab> opt = cabRepo.findById(cabId);
		
		if(opt.isPresent()) {
			
			Cab cab = opt.get();
			
			cabRepo.delete(cab);
			
			return cab;
		}
		
		else
		    
			throw new CabException("Cab Not Found By This Id: "+cabId);
	}

	@Override
	public Cab updateCab(int cabId, Cab cabDetails) throws CabException{
		
	Optional<Cab> opt = cabRepo.findById(cabId);
		
		if(opt.isPresent()) {
			
			Cab cab = opt.get();
			
			cab.setDriver(cabDetails.getDriver());
			
			return cab;
		}
		
		else
		    
			throw new CabException("Cab Not Found By This Id: "+cabId);
	}

//	@Override
//	public Driver getDriverCabDetails(int cabId) {
//		
//		Optional<Driver> opt = driverRepo.findDriverByCabId(cabId);
//		
//		if(opt.isPresent()) {
//			
//			Driver driver  = opt.get();			
//		
//			return driver;
//		  
//		}
//	      else
//			throw new BadCredentialsException("Driver Not Found");
//		
//	
//	}

	@Override
	public Driver getDriverCabDetails(String cabNumber) throws CabException{
		
		Optional<Driver> opt = driverRepo.findDriverByCabNumber(cabNumber);
		
		if(opt.isPresent()) {
			
			Driver driver  = opt.get();			
		
			return driver;
		  
		}
	      else
			throw new CabException("Driver Not Found By This Number: "+cabNumber);
		
	
	}

	
	


}
