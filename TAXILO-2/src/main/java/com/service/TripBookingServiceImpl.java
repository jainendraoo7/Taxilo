package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.entity.Customer;
import com.entity.Driver;
import com.entity.TripBooking;
import com.repository.CustomerRepo;
import com.repository.DriverRepo;
import com.repository.TripBookingRepo;

@Service
public class TripBookingServiceImpl implements TripBookingService{

	@Autowired
	private TripBookingRepo tripBookingRepo;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private DriverRepo driverRepo;
	
	@Override
	public TripBooking BookACabByCustomer(int customerId, TripBooking tripBooking) {
		
		
		Optional<Customer> opt = customerRepo.findById(customerId);
		
		if(opt.isPresent()) {
			
			Customer customer = opt.get();
			
			List<TripBooking> tripList = customer.getTripBookings();
			
			tripList.add(tripBooking);
			
			tripBooking.setCustomer(customer);
			
			String cabType = tripBooking.getCabType();
			
			
			List<Driver> opt1 = driverRepo.findByCabType(cabType,true);				
			

			
			  if(opt1!=null) {
				  
				  Driver driver=  new Driver();
				  
				  for(Driver d: opt1) {
					  driver = d;
				  }
				  
					List<TripBooking> tripLists = driver.getTripBookings(); 
					
					tripLists.add(tripBooking);
					
					driver.setAvailable(false);				
					
					tripBooking.setDriver(driver);
					
					
					if(cabType.equalsIgnoreCase("Mini")) {
						tripBooking.setBill(tripBooking.getDistanceInKm()*9);
			         }
					else if(cabType.equalsIgnoreCase("Sedan")) {
						tripBooking.setBill(tripBooking.getDistanceInKm()*12);
					}
					else if(cabType.equalsIgnoreCase("Suv")) {
						tripBooking.setBill(tripBooking.getDistanceInKm()*16);
					}
					
					tripBooking.setStatus(true);
					
					
					tripBookingRepo.save(tripBooking);
					
					return tripBooking;
			
				 
			  }
			  
				else 
					throw new BadCredentialsException("Driver Not Found");
			
			
		}
		
		else
			
		    throw new BadCredentialsException("Trip Not Booked");
	}

//	@Override
//	public TripBooking assigneDriverToTrip(int tripId) {
//		
//
//		
//		Optional<TripBooking> opt2 = tripBookingRepo.findById(tripId);
//		
//			
//			if(opt2.isPresent()) {
//				
//				TripBooking tb = opt2.get();
//				
//				String cabType = tb.getCabType();
//				
//				Optional<Driver> opt1 = driverRepo.findByCabType(cabType, true);				
//				
//				  if(opt1.isPresent()) {
//				
//					  Driver driver = opt1.get();
//					  
//						List<TripBooking> tripList = driver.getTripBookings(); 
//						
//						tripList.add(tb);
//						
//						tb.setDriver(driver);
//						
//						tripBookingRepo.save(tb);
//						
//						return tb;
//					  
//				  }
//				  
//					else 
//						throw new BadCredentialsException("Driver Not Found");
//				
//			}
//			
//			else 
//				throw new BadCredentialsException("Trip Not Found");
//	}
//	
	

	@Override
	public TripBooking cancelBooking(int tripId) {
	
		Optional<TripBooking> opt =  tripBookingRepo.findById(tripId);
		
		if(opt.isPresent()) {
			
			TripBooking tb = opt.get();
			
			tripBookingRepo.delete(tb);			
			
			return tb;
		}
		
		else 
			throw new BadCredentialsException("Trip Not Found");
		
	}

	@Override
	public TripBooking getTripById(int tripId) {
		
		Optional<TripBooking> opt =  tripBookingRepo.findById(tripId);
		
		if(opt.isPresent()) {
			
			TripBooking tb = opt.get();
			
			return tb;
		}
		
		else 
			throw new BadCredentialsException("Trip Not Found");
		
	}

	@Override
	public TripBooking updateTrip(int tripId, TripBooking tripBooking) {
	
        Optional<TripBooking> opt =  tripBookingRepo.findById(tripId);
		
		if(opt.isPresent()) {
			
			TripBooking tb = opt.get();
			
			tb.setCabType(tripBooking.getCabType());
			tb.setFromDateTime(tripBooking.getFromDateTime());
			tb.setToDateTime(tripBooking.getToDateTime());
			
			String cabType = tripBooking.getCabType();
			
			if(cabType.equalsIgnoreCase("Mini")) {
				tb.setBill(tb.getDistanceInKm()*9);
	  }
			else if(cabType.equalsIgnoreCase("Sedan")) {
				tb.setBill(tb.getDistanceInKm()*12);
			}
			else if(cabType.equalsIgnoreCase("Suv")) {
				tb.setBill(tb.getDistanceInKm()*16);
			}
			
			tripBookingRepo.save(tb);
			
			return tb;
		}
		
		else 
			throw new BadCredentialsException("Trip Not Found");
		
	
	}

	@Override
	public List<TripBooking> getAllTrips() {
		// TODO Auto-generated method stub
		return tripBookingRepo.findAll();
	}

	@Override
	public List<TripBooking> getTripByDriver(int driverId) {
		
		Optional<Driver> opt1 = driverRepo.findById(driverId);
		
		if(opt1.isPresent()) {
			
			Driver driver = opt1.get();
			
			List<TripBooking> all = driver.getTripBookings();
			
			return all;
		}

		else 
			throw new BadCredentialsException("Driver Not Found");
	}

	
	
	@Override
	public List<TripBooking> getTripByCustomer(int customerId) {
	
       Optional<Customer> opt1 = customerRepo.findById(customerId);
		
		if(opt1.isPresent()) {
			
			Customer customer  = opt1.get();
			
			List<TripBooking> all = customer.getTripBookings();
			
			return all;
		}

		else 
			throw new BadCredentialsException("Driver Not Found");
		
	}

	@Override
	public TripBooking billPayed(int tripId) {
		
	       Optional<TripBooking> opt =  tripBookingRepo.findById(tripId);
			
			if(opt.isPresent()) {
				
				TripBooking tb = opt.get();
				
				tb.setBillStatus(true);	
				
				tripBookingRepo.save(tb);
				
				return tb;
			}
			else 
				throw new BadCredentialsException("Trip Not Found");
	}

	@Override
	public TripBooking cancelTripBooking(int tripId) {
		
		   Optional<TripBooking> opt =  tripBookingRepo.findById(tripId);
			
					if(opt.isPresent()) {
						
						TripBooking tb = opt.get();
					
					   tb.setStatus(false);
					   
					   tripBookingRepo.save(tb);
					   
					   return tb;
					}
					else 
						throw new BadCredentialsException("Trip Not Found");
	}

	
	
}
