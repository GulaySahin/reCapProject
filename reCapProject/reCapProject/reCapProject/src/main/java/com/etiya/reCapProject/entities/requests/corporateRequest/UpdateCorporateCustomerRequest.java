package com.etiya.reCapProject.entities.requests.corporateRequest;




import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCorporateCustomerRequest {
	
	private int id;
	
	@NotNull(message = "Boş Geçilemez!")
	private String companyName;
	
	@NotNull(message = "Boş Geçilemez!")
	private String taxNumber;
	
	@NotNull(message = "Boş Geçilemez!")
	private String eMail;

	@NotNull(message = "Boş Geçilemez!")
	private String password;


}
