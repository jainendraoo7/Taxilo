package com.service;

import java.util.List;

import com.entity.Cab;
import com.entity.Driver;
import com.exception.CabException;

public interface CabService {

	public Cab registerCab(Cab cab);
	
	public List<Cab> getAllCabs();
	
	public Cab deleteCab(int cabId) throws CabException;
	
	public Cab updateCab(int cabId, Cab cabDetails) throws CabException;

//	public Driver getDriverCabDetails(int cabId);
	
	public Driver getDriverCabDetails(String cabNumber) throws CabException;
}
