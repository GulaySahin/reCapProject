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
public class UpdateIndividualCustomerRequest {
	
	private int id;
	
	@NotNull(message = "Boş Geçilemez!")
	private String firstName;
	
	@NotNull(message = "Boş Geçilemez!")
	private String lastName;

	@NotNull(message = "Boş Geçilemez!")
	private String identityNumber;
	
	@NotNull(message = "Boş Geçilemez!")
	private String eMail;
	
	@NotNull(message = "Boş Geçilemez!")
	private String password;
}
