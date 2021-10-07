package com.etiya.reCapProject.entities.requests.colorRequest;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateColorRequest {
	
	@NotNull(message = "Boş Geçilemez!")
	private int colorId;

	
	@NotNull(message = "Boş Geçilemez!")
	private String colorName;

}
