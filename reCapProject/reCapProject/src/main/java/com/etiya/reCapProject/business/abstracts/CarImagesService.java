package com.etiya.reCapProject.business.abstracts;

import java.io.IOException;
import java.util.List;


import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.entities.concretes.CarImages;
import com.etiya.reCapProject.entities.dtos.CarImageDto;
import com.etiya.reCapProject.entities.requests.carImageRequest.AddCarImagesRequest;
import com.etiya.reCapProject.entities.requests.carImageRequest.DeleteCarImagesRequest;
import com.etiya.reCapProject.entities.requests.carImageRequest.UpdateCarImagesRequest;

public interface CarImagesService {
	
	DataResult<List<CarImages>> getAll();
	
	DataResult<List<CarImageDto>> getCarImagesDetail();
	
	DataResult<List<CarImages>> getCarImagesByCarId(int carId);

	
	Result add(AddCarImagesRequest addCarImagesRequest) throws IOException;
	
	Result update(UpdateCarImagesRequest updateCarImagesRequest) throws IOException;
	
	Result delete(DeleteCarImagesRequest deleteCarImagesRequest);
}
