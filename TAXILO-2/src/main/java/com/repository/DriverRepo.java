package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.Driver;

@Repository
public interface DriverRepo extends JpaRepository<Driver, Integer> {

	@Query("From Driver d where d.email = ?1")
	Optional<Driver> findByUsername(String email);

	@Query("From Driver d where d.cab.cabType = ?1 AND d.available = ?2")
	List<Driver> findByCabType(String cabType, boolean available);
	 
	@Query("From Driver d where d.cab.cabNumber = ?1")
	Optional<Driver> findDriverByCabNumber(String cabNumber);
}
