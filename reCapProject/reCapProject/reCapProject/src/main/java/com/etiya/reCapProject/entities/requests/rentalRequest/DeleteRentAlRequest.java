package com.etiya.reCapProject.entities.requests.rentalRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteRentAlRequest {
	
	@NotBlank
	@NotNull(message = "Boş Geçilemez!")
	private int rentAlId;


	@NotNull(message = "Boş Geçilemez!")
	private int customerId;
	
	@NotBlank
	@NotNull(message = "Boş Geçilemez!")
	private int carId;
}
