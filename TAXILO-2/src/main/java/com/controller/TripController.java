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

import com.entity.TripBooking;
import com.service.TripBookingService;

@RestController
@RequestMapping("/trip")
@CrossOrigin
public class TripController {

	@Autowired
	private TripBookingService tripService;
	
	
	@PostMapping("/book/{customerId}")
	public ResponseEntity<TripBooking> tripBookingHandller(@PathVariable Integer customerId,  @RequestBody TripBooking tripBooking){
		
		TripBooking tb = tripService.BookACabByCustomer(customerId, tripBooking);
		
		return new ResponseEntity<>(tb, HttpStatus.ACCEPTED);
		
	}
	
//	@PutMapping("/assign/{tripBookingId}")
//	public ResponseEntity<TripBooking> tripBookingHandller(@PathVariable Integer tripBookingId){
//		
//        TripBooking tb = tripService.assigneDriverToTrip(tripBookingId);
//		
//		return new ResponseEntity<>(tb, HttpStatus.ACCEPTED);
//		
//	}
	
	@PutMapping("/update/{tripBookingId}")
	public ResponseEntity<TripBooking> updateTripBookingHandller(@PathVariable Integer tripBookingId, @RequestBody TripBooking tripBooking){
		
        TripBooking tb = tripService.updateTrip(tripBookingId, tripBooking);
		
		return new ResponseEntity<>(tb, HttpStatus.ACCEPTED);
		
	}
	
	
	@DeleteMapping("/delete/{tripBookingId}")
	public ResponseEntity<TripBooking> deleteTripBookingHandller(@PathVariable Integer tripBookingId){
		
        TripBooking tb = tripService.cancelBooking(tripBookingId);
		
		return new ResponseEntity<>(tb, HttpStatus.ACCEPTED);
		
	}
	
	
	@GetMapping("/getTripByBookingId/{tripBookingId}")
	public ResponseEntity<TripBooking> getTripByIdHandller(@PathVariable Integer tripBookingId){
		
        TripBooking tb = tripService.getTripById(tripBookingId);
		
		return new ResponseEntity<>(tb, HttpStatus.ACCEPTED);
		
	}
	
	
	@GetMapping("/getTripByDriverId/{driverId}")
	public ResponseEntity<List<TripBooking>> getAllTripByDriverIdHandller(@PathVariable Integer driverId){
		
        List<TripBooking> tb = tripService.getTripByDriver(driverId);
		
		return new ResponseEntity<>(tb, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/getTripByCustomerId/{customerId}")
	public ResponseEntity<List<TripBooking>> getAllTripByCustomerIdHandller(@PathVariable Integer customerId){
		
        List<TripBooking> tb = tripService.getTripByCustomer(customerId);
		
		return new ResponseEntity<>(tb, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/getAllTrips")
	public ResponseEntity<List<TripBooking>> getAllTripsHandller(){
		
        List<TripBooking> tb = tripService.getAllTrips();
		
		return new ResponseEntity<>(tb, HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/payBill/{tripBookingId}")
	public ResponseEntity<TripBooking> payBillHandller(@PathVariable Integer tripBookingId){
		
        TripBooking tb = tripService.billPayed(tripBookingId);
		
		return new ResponseEntity<>(tb, HttpStatus.ACCEPTED);
		
	}
	
	
	@PutMapping("/cancel/{tripBookingId}")
	public ResponseEntity<TripBooking> cancelTripBooking(@PathVariable Integer tripBookingId){
		
	    TripBooking tb = tripService.cancelTripBooking(tripBookingId);
		
			return new ResponseEntity<>(tb, HttpStatus.ACCEPTED);
		
	}
	
	
	
}
