package com.service;

import java.util.List;

import com.entity.Driver;
import com.exception.CabException;
import com.exception.DriverException;

public interface DriverService {

	public Driver registerDriver(Driver driver);
	
	public List<Driver> getAllDriver();
	
	public Driver deleteDriverById(int driverId) throws DriverException;
	
	public Driver getDriverById(int driverId) throws DriverException;
	
	public Driver getDriverByUsername(String email) throws DriverException;
	
	public Driver assignCab(int driverId, int cabId)throws DriverException, CabException ;
	
	public Driver updateEmailAndPassword(int driverId, Driver driverDetails)throws DriverException;
	
	public Driver setAvailability(int driverId) throws DriverException;
	
	public Driver setDisAvailability(int driverId) throws DriverException;
	
	public Driver find(String cabType, boolean available); 
}
