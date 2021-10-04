package com.etiya.reCapProject.entities.requests.carDamageRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarDamageRequest {
	
	private int damageId;
	
	private String damageInfo;
	
	private int carId;
}
