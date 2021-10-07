package com.etiya.reCapProject.entities.requests.individualCustomerRequest;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteIndividualCustomerRequest {
	
	@NotNull(message = "Boş Geçilemez!")
	private String identityNumber;
}
