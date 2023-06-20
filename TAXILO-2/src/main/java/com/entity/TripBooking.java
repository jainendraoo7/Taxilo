package com.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int tripBookingId;
	private String fromLocation;
	private String toLocation;
	private Date fromDateTime;
	private Date toDateTime;
	private String cabType;
	
	private boolean status;
	private double distanceInKm;
	private double bill;
    private boolean billStatus;
	
	
	@ManyToOne
//	@JsonIgnore
	@JoinColumn(name="customerId")
	private Customer customer;
	
	
	@ManyToOne
//	@JsonIgnore
	@JoinColumn(name="driverId")
	private Driver driver;
	
}
