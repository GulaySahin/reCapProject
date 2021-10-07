package com.etiya.reCapProject.entities.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDamageDto {
	
	private int damageId;
	
	private String damageInfo;

	private String carName;

}
