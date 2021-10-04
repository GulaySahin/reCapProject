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
public class DeleteColorRequest {
	
	@NotNull(message = "Boş Geçilemez!")
	private int colorId;
}
