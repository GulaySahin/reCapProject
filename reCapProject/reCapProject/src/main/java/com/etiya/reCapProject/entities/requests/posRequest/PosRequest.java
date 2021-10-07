package com.etiya.reCapProject.entities.requests.posRequest;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PosRequest {
	
	@NotNull(message = "Boş Geçilemez!")
	private String cardName;
	
	@NotNull(message = "Boş Geçilemez!")
	private String cardNumber;
	
	@NotNull(message = "Boş Geçilemez!")
	private String expiration;
	
	@NotNull(message = "Boş Geçilemez!")
	private String cvc;
	
	@NotNull(message = "Boş Geçilemez!")
	private double price;
}
