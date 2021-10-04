package com.etiya.reCapProject.entities.requests.invoiceRequest;


import javax.validation.constraints.NotNull;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteInvoiceRequest {
	
	@NotNull(message = "Boş Geçilemez!")
	private int invoiceId;

}
