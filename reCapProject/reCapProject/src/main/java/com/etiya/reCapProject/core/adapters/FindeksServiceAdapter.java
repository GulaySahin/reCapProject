package com.etiya.reCapProject.core.adapters;

import org.springframework.stereotype.Service;

import com.etiya.reCapProject.business.abstracts.CustomerFindeksPointCheckService;
import com.etiya.reCapProject.core.services.FindeksService;
import com.etiya.reCapProject.entities.concretes.CorporateCustomer;
import com.etiya.reCapProject.entities.concretes.IndividualCustomer;

@Service
public class FindeksServiceAdapter implements CustomerFindeksPointCheckService{

	@Override
	public int checkIndividualCustomerFindexPoint(IndividualCustomer individualCustomer) {
		FindeksService findeksService= new FindeksService();
		return findeksService.individualCustomerFindexPoint(individualCustomer.getIdentityNumber());
	}

	@Override
	public int checkCorporateCustomerFindexPoint(CorporateCustomer corporateCustomer) {
		FindeksService findeksService= new FindeksService();
		return findeksService.corporateCustomerFindexPoint(corporateCustomer.getTaxNumber());
	}

}
