package com.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Cab {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cabId;
	
	private String cabName;
	
	private String cabNumber;
	
	private String cabType;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Driver driver;
	
}
