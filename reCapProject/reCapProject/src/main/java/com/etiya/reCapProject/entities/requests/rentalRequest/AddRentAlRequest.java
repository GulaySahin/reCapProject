package com.etiya.reCapProject.entities.requests.rentalRequest;


import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.etiya.reCapProject.entities.dtos.AdditionalServiceDto;
import com.etiya.reCapProject.entities.dtos.CreditCardDto;
import com.etiya.reCapProject.entities.requests.posRequest.PosRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated

public class AddRentAlRequest {
	
	private Date rentDate;

	private Date returnDate;
	
	@NotNull(message = "Boş Geçilemez!")
	private int carId;
	
	@JsonIgnore
	@NotNull(message = "Boş Geçilemez!")
	private String takeCity;
	
	@NotNull(message = "Boş Geçilemez!")
	private String returnCity;
	
	@JsonIgnore
	private int startKilometer;
	
	private int endKilometer;
	
	@JsonIgnore
	private double amount;


	private boolean saveCreditCard;
	
	

	
	@NotNull(message = "Boş Geçilemez!")
	private int customerId;
	
	private CreditCardDto creditCardDto;
	
	@JsonIgnore
	private PosRequest posRequest;
	
	//private List<Integer> additionalId;
	
	private List<AdditionalServiceDto> additionalServiceDtos;

}
