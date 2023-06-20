package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer>{

	@Query("From Customer c where c.email = ?1")
	Optional<Customer> findByUsername(String email);
	
}
