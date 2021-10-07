package com.etiya.reCapProject.entities.requests.creditCardRequest;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCreditCardRequest {
	
	
	private String cardName;
	

	private String cardNumber;
	
	
	private String expiration;
	
	private String cvc;
	
	private int customerId;
	
	
}
