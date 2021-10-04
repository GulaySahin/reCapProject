package com.etiya.reCapProject.entities.requests.carImageRequest;


import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarImagesRequest {
	
	@NotNull(message = "Boş Geçilemez!")
	private int id;
	
	
	
	@NotNull(message = "Boş Geçilemez!")
	MultipartFile file;

	
	@NotNull(message = "Boş Geçilemez!")
	private int carId;
}
