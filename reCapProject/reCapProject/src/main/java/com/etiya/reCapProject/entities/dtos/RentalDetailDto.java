package com.etiya.reCapProject.entities.dtos;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDetailDto {
	
	private int rentAlId;

	private Date rentDate;
	
	private Date returnDate;
	
	private String takeCity;
	
	private String returnCity;
	
	private int startKilometer;
	
	private int endKilometer;
	
	private Double rentalAmount;
	
	private GetCarDetailDto getCarDetailDto;
	
	private int customerId;
	
	private List<AdditionalServiceDetailDto> additionalServiceDetailDtos;

}
