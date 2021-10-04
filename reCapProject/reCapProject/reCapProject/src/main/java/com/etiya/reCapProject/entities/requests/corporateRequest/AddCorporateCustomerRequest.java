package com.etiya.reCapProject.entities.requests.corporateRequest;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCorporateCustomerRequest {
	
	@NotNull(message = "Boş Geçilemez!")
	@NotBlank
	@Size(min=2,max=40)
	private String companyName;
	
	@NotNull(message = "Boş Geçilemez!")
	private String taxNumber;
	
	@NotNull(message = "Boş Geçilemez!")
	@Email
	private String eMail;

	@NotNull(message = "Boş Geçilemez!")
	private String password;
	
	
	
	

}
