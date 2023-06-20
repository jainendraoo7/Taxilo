package com.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int driverId;
	
    private String name;
	
    @Column(unique = true)
	private String email;
	
	@NotNull
 	@Size(min = 8,message="password should be minimum 8 characters")
	private String password;
	
	 @NotNull
	 @Column(unique = true)
	 @Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Enter valid 10 digit mobile number")
	private String mobile;
	
	private String license;
	
	private boolean available; 
	
	private String role = "DRIVER";
	
	@OneToOne(cascade = CascadeType.ALL)
	private Cab cab;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "driver", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Query> query;
	
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "driver", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<TripBooking> tripBookings;
	
}
