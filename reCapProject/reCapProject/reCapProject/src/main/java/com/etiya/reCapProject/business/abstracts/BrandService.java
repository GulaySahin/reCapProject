package com.etiya.reCapProject.business.abstracts;

import java.util.List;

import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.entities.concretes.Brand;
import com.etiya.reCapProject.entities.requests.brandRequest.AddBrandRequest;
import com.etiya.reCapProject.entities.requests.brandRequest.DeleteBrandRequest;
import com.etiya.reCapProject.entities.requests.brandRequest.UpdateBrandRequest;

public interface BrandService {
	
	DataResult<List<Brand>> getByBrandId(int brandId);

	DataResult<List<Brand>> getAll();

	Result add(AddBrandRequest addBrandRequest);

	Result update(UpdateBrandRequest updateBrandRequest);

	Result delete(DeleteBrandRequest deleteBrandRequest);
}
