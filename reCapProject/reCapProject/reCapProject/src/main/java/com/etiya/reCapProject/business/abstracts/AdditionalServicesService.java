package com.etiya.reCapProject.business.abstracts;

import java.util.List;

import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.entities.concretes.AdditionalService;
import com.etiya.reCapProject.entities.requests.additionalRequest.AddAdditionalServiceRequest;
import com.etiya.reCapProject.entities.requests.additionalRequest.DeleteAdditionalServiceRequest;
import com.etiya.reCapProject.entities.requests.additionalRequest.UpdateAdditionalServiceRequest;

public interface AdditionalServicesService {
	
	DataResult<List<AdditionalService>> getAll();
	
	DataResult<AdditionalService> getById(int id);
	
	Result add(AddAdditionalServiceRequest addAdditionalServiceRequest);
	
	Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest);

	Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest);

	
	
	
}
