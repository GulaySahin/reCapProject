package com.etiya.reCapProject.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDetailsDto {
	
	private String carName;
	private String brandName;
	private String colorName;
	private double dailyPrice;
	
}
