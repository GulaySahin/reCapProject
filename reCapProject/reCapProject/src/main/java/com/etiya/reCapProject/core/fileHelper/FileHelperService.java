package com.etiya.reCapProject.core.fileHelper;

import java.io.IOException;

import com.etiya.reCapProject.entities.requests.carImageRequest.AddCarImagesRequest;
import com.etiya.reCapProject.entities.requests.carImageRequest.UpdateCarImagesRequest;

public interface FileHelperService {
	
	void addCarImagePathName(AddCarImagesRequest addCarImagesRequest,String imagePathName)throws IOException;
	
	void updateCarImagePathName(UpdateCarImagesRequest updateCarImagesRequest,String imagePathName)throws IOException;
	
}
