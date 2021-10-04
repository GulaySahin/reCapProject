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
public class AddCareRequest {
	
	
	private int careId;
	
	@NotNull(message = "Boş Geçilemez!")
	private String location;
	
	@NotNull(message = "Boş Geçilemez!")
	private String startDate;
	
	@NotNull(message = "Boş Geçilemez!")
	private String finishDate;
	
	@NotNull(message = "Boş Geçilemez!")
	private int carId;
	
	

}
