package com.etiya.reCapProject.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerDto {
	
	private int id;

	private String identityNumber;
	
	private String firstName;
	
	private String lastName;


}
