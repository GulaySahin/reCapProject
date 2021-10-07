package com.etiya.reCapProject.entities.requests.invoiceRequest;



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
public class AddInvoiceRequest {
	
	@NotNull
	private int invoiceNumber;
	
	@JsonIgnore
	private Date rentDate;
	
	@JsonIgnore
	private Date returnDate;
	
	@NotNull
	private Date invoiceDate;
	
	
	@JsonIgnore
	private Long totalRentalDay;
	
	@NotNull
	private Double rentalAmount;
	
	@NotNull
	private int customerId;
	
	@NotNull
	private int rentAlId;
}
