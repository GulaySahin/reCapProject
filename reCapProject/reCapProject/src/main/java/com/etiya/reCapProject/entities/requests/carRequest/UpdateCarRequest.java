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
public class UpdateCarRequest {
	
	@NotNull(message = "Boş Geçilemez!")
	
	private int carId;

	@NotNull(message = "Boş Geçilemez!")
	
	private int brandId;
	
	@NotNull(message = "Boş Geçilemez")
	private boolean isCarCare;
	
	@NotNull(message = "Boş Geçilemez")
	private String city;
	
	@NotNull(message = "Boş Geçilemez")
	private int kilometer;

	
	@NotNull(message = "Boş Geçilemez!")
	private int colorId;
	
	@Min(0)
	@Max(1900)
	@NotNull
	private int findexPoint;

	
	private String carName;

	private int modelYear;

	private double dailyPrice;
	
	private String description;
}
