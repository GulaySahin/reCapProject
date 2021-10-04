package com.etiya.reCapProject.business.abstracts;

import com.etiya.reCapProject.entities.concretes.CorporateCustomer;
import com.etiya.reCapProject.entities.concretes.IndividualCustomer;

public interface CustomerFindeksPointCheckService {
	
	int checkIndividualCustomerFindexPoint(IndividualCustomer individualCustomer);
	
	int checkCorporateCustomerFindexPoint(CorporateCustomer corporateCustomer);
	
	
}
