package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.entity.Cab;
import com.entity.Driver;
import com.exception.CabException;
import com.exception.DriverException;
import com.repository.CabRepo;
import com.repository.DriverRepo;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired	
	private DriverRepo driverRepo;
	
	@Autowired
	private CabRepo cabRepo;

	@Override
	public Driver registerDriver(Driver driver) {

		driver.setAvailable(true);
		
		return driverRepo.save(driver);

	}

	@Override
	public List<Driver> getAllDriver() {
		// TODO Auto-generated method stub
		return driverRepo.findAll();
	}

	@Override
	public Driver deleteDriverById(int driverId) throws DriverException {
		// TODO Auto-generated method stub
		
		Optional<Driver> opt = driverRepo.findById(driverId); 
		
		if(opt.isPresent()) {
			
			Driver driver = opt.get();
			
			 driverRepo.delete(driver);
			
			 return driver;
		}
		
		else 
			throw new DriverException("Driver Not Found By This Id: "+driverId);
	}

	@Override
	public Driver assignCab(int driverId, int cabId) throws DriverException, CabException {
		
		
		Optional<Driver> opt1 = driverRepo.findById(driverId); 
		
		Optional<Cab> opt2 = cabRepo.findById(cabId);
		
		if(opt1.isPresent()) {
			
			Driver driver = opt1.get();
			
			if(opt2.isPresent()) {
				
				Cab cab = opt2.get(); 
				
				cab.setDriver(driver);
				
				driver.setCab(cab);
				
				return driverRepo.save(driver);
				
			}
			else 
				
				 throw new CabException("Cab Not Found By This Id: "+cabId);
				
			
		}
		
		else 
			
			throw new DriverException("Driver Not Found By This Id: "+driverId);
		
	}

	@Override
	public Driver getDriverById(int driverId) throws DriverException{
		
        Optional<Driver> opt = driverRepo.findById(driverId); 
		
		if(opt.isPresent()) {
			
			Driver driver = opt.get();
			
			 return driver;
		}
		
		else 
			throw new DriverException("Driver Not Found By This Id: "+driverId);
		
	}

	@Override
	public Driver getDriverByUsername(String email) throws DriverException {
		
		Optional<Driver> opt = driverRepo.findByUsername(email);
		
		if(opt.isPresent()) {
			
			Driver driver  = opt.get();
			
			return driver;
			
		}
		else 
			throw new DriverException("Driver Not Found By This email: "+email);
	}

	@Override
	public Driver updateEmailAndPassword(int driverId, Driver driverDetails) throws DriverException {
		
		Optional<Driver> opt = driverRepo.findById(driverId); 
		
		if(opt.isPresent()) {
			
			Driver driver = opt.get();
			
			driver.setEmail(driverDetails.getEmail());
			driver.setPassword(driverDetails.getPassword());
			
		    driverRepo.save(driver);
			
		    return driver;
		}
		else 
			throw new DriverException("Driver Not Found By This Id: "+driverId);
	}

	@Override
	public Driver setAvailability(int driverId) throws DriverException {
		
        Optional<Driver> opt = driverRepo.findById(driverId); 
		
		if(opt.isPresent()) {
			
			Driver driver = opt.get();
			
            driver.setAvailable(true);
			
		    driverRepo.save(driver);
			
		    return driver;
		}
		else 
			throw new DriverException("Driver Not Found By This Id: "+driverId);
		
		
	}

	@Override
	public Driver setDisAvailability(int driverId) throws DriverException {
		
        Optional<Driver> opt = driverRepo.findById(driverId); 
		
		if(opt.isPresent()) {
			
			Driver driver = opt.get();
			
            driver.setAvailable(false);
			
		    driverRepo.save(driver);
			
		    return driver;
		}
		else 
			throw new DriverException("Driver Not Found By This Id: "+driverId);
		
		
	}

	@Override
	public Driver find(String cabType, boolean available) {
		
		List<Driver> opt = driverRepo.findByCabType(cabType, available);
		
		if(opt!=null) {
			
			Driver driver = new Driver();
			
			for(Driver d: opt) {
				driver = d;
			}
			
			return driver;
			
		}
		
		throw new BadCredentialsException("not found");
		
	}
	

}
