package com.etiya.reCapProject.entities.requests.carImageRequest;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCarImagesRequest {
	
	@NotNull(message = "Boş Geçilemez!")
	private int id;
	
	
	@NotNull(message = "Boş Geçilemez!")
	private int carId;

}
