package com.etiya.reCapProject.entities.requests.carDamageRequest;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCarDamageRequest {
	
	@NotNull(message = "Boş Geçilemez!")
	private int damageId;
	
	private String damageInfo;
	
	@NotNull(message = "Boş Geçilemez!")
	private int carId;

}
