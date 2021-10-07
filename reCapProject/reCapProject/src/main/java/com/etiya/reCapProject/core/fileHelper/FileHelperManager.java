package com.etiya.reCapProject.core.fileHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.etiya.reCapProject.business.paths.Paths;
import com.etiya.reCapProject.entities.requests.carImageRequest.AddCarImagesRequest;
import com.etiya.reCapProject.entities.requests.carImageRequest.UpdateCarImagesRequest;

@Service
public class FileHelperManager implements FileHelperService{

	@Override
	public void addCarImagePathName(AddCarImagesRequest addCarImagesRequest, String imagePathName) throws IOException {
		
		File myFiles= new File(Paths.CAR_IMAGES_PATH+"."+addCarImagesRequest.getFile().getContentType().substring(addCarImagesRequest.getFile().getContentType().indexOf("/")+1));
		myFiles.createNewFile();
		FileOutputStream fileoutputstream=new FileOutputStream(myFiles);
		fileoutputstream.write(addCarImagesRequest.getFile().getBytes());
		fileoutputstream.close();
		
	}

	@Override
	public void updateCarImagePathName(UpdateCarImagesRequest updateCarImagesRequest, String imagePathName)
			throws IOException {
		
		File myFiles= new File(Paths.CAR_IMAGES_PATH+"."+updateCarImagesRequest.getFile().getContentType().substring(updateCarImagesRequest.getFile().getContentType().indexOf("/")+1));
		myFiles.createNewFile();
		FileOutputStream fileoutputstream=new FileOutputStream(myFiles);
		fileoutputstream.write(updateCarImagesRequest.getFile().getBytes());
		fileoutputstream.close();
		
	}

}
