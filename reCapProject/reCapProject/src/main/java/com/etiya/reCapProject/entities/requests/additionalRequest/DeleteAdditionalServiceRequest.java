package com.etiya.reCapProject.entities.requests.additionalRequest;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteAdditionalServiceRequest {
	
	@NotNull(message = "Boş Geçilemez!")
	private int additionalId;
}
