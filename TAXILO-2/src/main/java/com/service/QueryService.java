package com.service;

import java.util.List;

import com.entity.Query;

public interface QueryService {

	public Query createQuery(int driverId, Query query);

	public Query deleteQuery(int queryId); 
	
	public List<Query> getAllQueries(); 
	
	public List<Query> getAllQueriesByDriver(int drievrId);
	
	public Query getQueryById(int queryId);
	
	public Query queryChangeStatus(int queryId);
	
}
