package com.service;

import java.util.List;

import com.entity.TripBooking;

public interface TripBookingService {

	public TripBooking BookACabByCustomer(int customerId, TripBooking tripBooking);
	
//	public TripBooking assigneDriverToTrip(int tripId);
	
	public TripBooking cancelBooking(int tripId);
	
	public TripBooking getTripById(int tripId);
	
	public TripBooking updateTrip(int tripId, TripBooking tripBooking);
	
	public List<TripBooking> getAllTrips();
	
	public List<TripBooking> getTripByDriver(int driverId);
	
	public List<TripBooking> getTripByCustomer(int customerId);
	
	public TripBooking billPayed(int tripId);
	
	public TripBooking cancelTripBooking(int tripId);
}
