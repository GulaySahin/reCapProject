package com.etiya.reCapProject.entities.requests.rentalRequest;


import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentAlRequest {
	
	@NotNull(message = "Boş Geçilemez!")
	private int rentAlId;

	@NotNull(message = "Boş Geçilemez!")	
	private int carId;
	
	@NotNull(message = "Boş Geçilemez!")
	private int customerId;
	
	private Date rentDate;
	
	private Date returnDate;
	
	@JsonIgnore
	private String takeCity;
	
	private String returnCity;
	
	private int startKilometer;
	
	private int endKilometer;
	
	@JsonIgnore
	private double amount;

	
	
}
