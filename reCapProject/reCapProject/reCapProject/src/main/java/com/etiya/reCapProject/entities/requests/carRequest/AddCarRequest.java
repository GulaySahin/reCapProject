package com.etiya.reCapProject.entities.requests.carRequest;



import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCarRequest {
	
	@NotNull(message = "Boş Geçilemez")
	private int brandId;
	
	@NotNull(message = "Boş Geçilemez")
	private int colorId;
	
	@NotNull(message = "Boş Geçilemez")
	private String carName;
	
	@NotNull(message = "Boş Geçilemez")
	private int kilometer;

	
	@NotNull(message = "Boş Geçilemez")
	private boolean isCarCare;
	
	private String city;


	
	@NotNull(message = "Boş Geçilemez")
	private int modelYear;
	
	@NotNull(message = "Boş Geçilemez")
	@Min(0)
	private double dailyPrice;
	
	@NotNull(message = "Boş Geçilemez")
	private String description;
	
	@Min(0)
	@Max(1900)
	@NotNull
	private int findexPoint;

	
	


	

}
