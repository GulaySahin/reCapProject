package com.etiya.reCapProject.business.abstracts;

import java.util.List;

import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.entities.concretes.Care;
import com.etiya.reCapProject.entities.requests.careRequest.AddCareRequest;
import com.etiya.reCapProject.entities.requests.careRequest.DeleteCareRequest;
import com.etiya.reCapProject.entities.requests.careRequest.UpdateCareRequest;

public interface CareService {
	
	DataResult<List<Care>> getAll();
	
 	Result add(AddCareRequest addCareRequest);
	
	Result update(UpdateCareRequest updateCareRequest);

	Result delete(DeleteCareRequest deleteCareRequest);


}
