package com.etiya.reCapProject.entities.requests.carRequest;


import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCarRequest {
	
	
	@NotNull(message = "Boş Geçilemez!")
	private int brandId;
	
	
	@NotNull(message = "Boş Geçilemez!")
	private int colorId;
	
	
	@NotNull(message = "Boş Geçilemez!")
	private int carId;
}
