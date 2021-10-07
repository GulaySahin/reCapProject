package com.etiya.reCapProject.entities.requests.careRequest;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCareRequest {
	
	@NotNull(message = "Boş Geçilemez!")
	private int carId;

	@NotNull(message = "Boş Geçilemez!")
	private int careId;
	
}
