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

import com.entity.Query;
import com.service.QueryService;

@RestController
@RequestMapping("/query")
@CrossOrigin
public class QueryController {

	@Autowired
	private QueryService queryService;
	
	@PostMapping("/create/{driverId}")
	public ResponseEntity<Query> createQueryHandller(@RequestBody Query query,@PathVariable Integer driverId){
		
		Query q1 = queryService.createQuery(driverId, query);
		
		return new ResponseEntity<>(q1, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/delete/{queryId}")
	public ResponseEntity<Query> deleteQueryHandller(@PathVariable Integer queryId){
		
		Query q1 = queryService.deleteQuery(queryId);
		
		return new ResponseEntity<>(q1, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Query>> getAllQueriesHandller(){
		
		List<Query> q1 = queryService.getAllQueries();
		
		return new ResponseEntity<>(q1, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/getQuery/{queryId}")
	public ResponseEntity<Query> getQueryById(@PathVariable Integer queryId){
		
		Query q1 = queryService.getQueryById(queryId);
		
		return new ResponseEntity<>(q1, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/getAllByDriver/{driverId}")
	public ResponseEntity<List<Query>> getAllQueriesByDriverHandller(@PathVariable Integer driverId){
		
		List<Query> q1 = queryService.getAllQueriesByDriver(driverId);
		
		return new ResponseEntity<>(q1, HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/changeStatus/{queryId}")
	public ResponseEntity<Query> changeQueryStatus(@PathVariable Integer queryId){
		
		Query q1 =  queryService.queryChangeStatus(queryId);
		
		return new ResponseEntity<>(q1, HttpStatus.ACCEPTED);
		
	}
	
}
