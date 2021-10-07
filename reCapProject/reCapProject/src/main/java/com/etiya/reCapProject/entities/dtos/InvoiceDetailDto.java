package com.etiya.reCapProject.entities.dtos;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetailDto {
	
	private int invoiceId;
	
	private int invoiceNumber;
	
	@JsonFormat(pattern = "yyyy-MM-dd hh:MM:ss")
	private Date invoiceDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date rentDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date returnDate;
		
	private Long totalRentalDay;
	
	private Double rentalAmount;
	
	
	
	private List<AdditionalServiceDetailDto> additionalServiceDetailDtos;

}
