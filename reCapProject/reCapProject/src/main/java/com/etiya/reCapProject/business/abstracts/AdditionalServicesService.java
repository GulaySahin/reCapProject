package com.etiya.reCapProject.business.abstracts;

import java.util.List;

import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.entities.dtos.AdditionalServiceDetailDto;
import com.etiya.reCapProject.entities.requests.additionalRequest.AddAdditionalServiceRequest;
import com.etiya.reCapProject.entities.requests.additionalRequest.DeleteAdditionalServiceRequest;
import com.etiya.reCapProject.entities.requests.additionalRequest.UpdateAdditionalServiceRequest;

public interface AdditionalServicesService {
	
	DataResult<List<AdditionalServiceDetailDto>> getAll();
	
	DataResult<AdditionalServiceDetailDto> getById(int id);
	
	Result add(AddAdditionalServiceRequest addAdditionalServiceRequest);
	
	Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest);

	Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest);
	
	DataResult<List<AdditionalServiceDetailDto>> getByRentalId(int rentalId);
	


	
	
	
}
