package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Query {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int queryId;
	
	private String subject;
	
	private String queryMessage;
	
	private boolean resolved;
	
	@ManyToOne
//	@JsonIgnore
	@JoinColumn(name="driverId")
	private Driver driver;
}
