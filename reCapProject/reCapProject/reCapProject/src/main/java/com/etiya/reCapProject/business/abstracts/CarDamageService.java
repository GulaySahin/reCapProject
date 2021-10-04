package com.etiya.reCapProject.business.abstracts;

import java.util.List;

import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.entities.concretes.CarDamage;
import com.etiya.reCapProject.entities.requests.carDamageRequest.AddCarDamageRequest;
import com.etiya.reCapProject.entities.requests.carDamageRequest.DeleteCarDamageRequest;
import com.etiya.reCapProject.entities.requests.carDamageRequest.UpdateCarDamageRequest;

public interface CarDamageService {
	
	DataResult<List<CarDamage>> getAll();
	
	Result add(AddCarDamageRequest addCarDamageRequest);
	
	Result update(UpdateCarDamageRequest updateCarDamageRequest);

	Result delete(DeleteCarDamageRequest deleteCarDamageRequest);

	
	
	
}
