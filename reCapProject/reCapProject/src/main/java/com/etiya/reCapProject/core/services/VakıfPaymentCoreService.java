package com.etiya.reCapProject.core.services;


public class VakıfPaymentCoreService {
	
	public boolean VakıfPayment(String cardName, String cardNumber,String expiration,
			 String cvc,double price) {
	

		double limit = 5000;

		if (price < limit) {

			return true;
		}

		return false;
	
	
}
}
