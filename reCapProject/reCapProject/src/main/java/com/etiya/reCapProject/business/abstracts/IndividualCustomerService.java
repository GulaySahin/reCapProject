package com.etiya.reCapProject.business.abstracts;

import java.util.List;

import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.entities.dtos.IndividualCustomerDto;
import com.etiya.reCapProject.entities.requests.individualCustomerRequest.AddIndividualCustomerRequest;
import com.etiya.reCapProject.entities.requests.individualCustomerRequest.DeleteIndividualCustomerRequest;
import com.etiya.reCapProject.entities.requests.individualCustomerRequest.UpdateIndividualCustomerRequest;

public interface IndividualCustomerService {
	
	Result add(AddIndividualCustomerRequest addIndividualCustomerRequest);
	
	Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest);
	
	Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest);
	
	DataResult<List<IndividualCustomerDto>> getAll();
	
	DataResult<IndividualCustomerDto> getById(int id);
}
