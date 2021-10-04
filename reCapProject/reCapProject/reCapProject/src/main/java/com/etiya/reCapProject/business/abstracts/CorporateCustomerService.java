package com.etiya.reCapProject.business.abstracts;

import java.util.List;

import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.entities.concretes.CorporateCustomer;
import com.etiya.reCapProject.entities.requests.corporateRequest.AddCorporateCustomerRequest;
import com.etiya.reCapProject.entities.requests.corporateRequest.DeleteCorporateCustomerRequest;
import com.etiya.reCapProject.entities.requests.corporateRequest.UpdateCorporateCustomerRequest;

public interface CorporateCustomerService {
	
	Result add(AddCorporateCustomerRequest addCorporateCustomerRequest);
	
	Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest);
	
	Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest);
	
	DataResult<List<CorporateCustomer>> getAll();
}
