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
public class DeleteCarDamageRequest {
	
	@NotNull(message = "Boş Geçilemez!")
	private int damageId;
	
	@NotNull(message = "Boş Geçilemez!")
	private int carId;


}
