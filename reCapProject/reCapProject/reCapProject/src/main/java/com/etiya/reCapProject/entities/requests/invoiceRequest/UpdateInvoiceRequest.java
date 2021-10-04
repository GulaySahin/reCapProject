package com.etiya.reCapProject.entities.requests.invoiceRequest;

import java.util.Date;

import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {
	@NotNull
	private int invoiceId;

	@NotNull
	private int invoiceNumber;
	
	@NotNull
	private Date rentalDate;
	
	@NotNull
	private Date returnRentalDate;
	
	@NotNull
	private Date invoiceDate;
	
	
	@NotNull
	private Long totalRentalDay;
	
	@NotNull
	private Double rentalAmount;
	
	@NotNull
	private int customerId;
	
	@NotNull
	private int rentAlId;
}
