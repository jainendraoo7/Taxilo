package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.entity.Driver;
import com.entity.Query;
import com.repository.DriverRepo;
import com.repository.QueryRepo;

@Service
public class QueryServiceImpl implements QueryService {

	@Autowired
	private QueryRepo queryRepo;
	
	@Autowired
	private DriverRepo driverRepo;
	
	@Override
	public Query createQuery(int driverId, Query query) {
		// TODO Auto-generated method stub
		
		Optional<Driver> opt = driverRepo.findById(driverId);
		
		if(opt.isPresent()) {
			
		    Driver driver = opt.get();
		    
		    List<Query> list = driver.getQuery();
		    
		    list.add(query);
 	
		       driver.setQuery(list);  	
		        
		    query.setDriver(driver);
		    
			return queryRepo.save(query);
			
		}
		
		else
		
			throw new BadCredentialsException("Driver Not Found"); 
	}

	@Override
	public Query deleteQuery(int queryId) {
		
		Optional<Query> opt = queryRepo.findById(queryId);
		
		if(opt.isPresent()) {
			
			Query query = opt.get();
			
			queryRepo.delete(query);
			
			queryRepo.save(query);
			
			return query;
			
		}
		else 
			throw new BadCredentialsException("Not Found"); 
		
		
	}

	@Override
	public List<Query> getAllQueries() {
		// TODO Auto-generated method stub
		return queryRepo.findAll();
	}

	@Override
	public List<Query> getAllQueriesByDriver(int driverId) {
		
		Optional<Driver> opt = driverRepo.findById(driverId);
		
		if(opt.isPresent()) {
			
			Driver driver = opt.get();
			
			List<Query> list = driver.getQuery();
			
			return list;
			
		}
		
		else 
			throw new BadCredentialsException("Not Found"); 
		
	}

	@Override
	public Query getQueryById(int queryId) {
		
		Optional<Query> opt = queryRepo.findById(queryId);
		
		if(opt.isPresent()) {
			
			Query query = opt.get();
			
			return query;
			
		}
		else
			 throw new BadCredentialsException("Not Found");
	}

	
	@Override
	public Query queryChangeStatus(int queryId) {
     
		Optional<Query> opt = queryRepo.findById(queryId);
		
		if(opt.isPresent()) {
			
			Query query = opt.get();
			
			query.setResolved(true);
			
			queryRepo.save(query);
			
			return query;
			
		}
		else
			 throw new BadCredentialsException("Not Found");
	}

}
