package com.etiya.reCapProject.entities.dtos;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

	private String carName;
	
	private double dailyPrice;
	
	private String description;
	
	private int kilometer;
	
	private int findexPoint;
	
	private String city;
	
	private int modelYear;

	private BrandDto brandDto;
	
	private ColorDto colorDto;
	
}
