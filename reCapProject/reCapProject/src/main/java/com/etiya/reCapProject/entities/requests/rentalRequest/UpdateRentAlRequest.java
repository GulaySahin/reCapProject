package com.etiya.reCapProject.entities.requests.rentalRequest;


import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.etiya.reCapProject.entities.dtos.AdditionalServiceDto;
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
	

	
	private String returnCity;
	
	
	private int endKilometer;
	
	private List<AdditionalServiceDto> additionalServiceDtos;

	
	@JsonIgnore
	private double amount;

	
	
}
