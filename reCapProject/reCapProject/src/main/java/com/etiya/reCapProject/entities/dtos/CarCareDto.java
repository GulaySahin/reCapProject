package com.etiya.reCapProject.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarCareDto {
	
	private int careId;
	
	private String location;
	
	private String startDate;
	
	private String finishDate;
	
	private String carName;

}
