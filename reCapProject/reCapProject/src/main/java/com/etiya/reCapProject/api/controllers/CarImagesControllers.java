package com.etiya.reCapProject.api.controllers;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.etiya.reCapProject.business.abstracts.CarImagesService;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.entities.concretes.CarImages;
import com.etiya.reCapProject.entities.dtos.CarImageDto;
import com.etiya.reCapProject.entities.requests.carImageRequest.AddCarImagesRequest;
import com.etiya.reCapProject.entities.requests.carImageRequest.DeleteCarImagesRequest;
import com.etiya.reCapProject.entities.requests.carImageRequest.UpdateCarImagesRequest;

@RestController
@RequestMapping("/api/images")
public class CarImagesControllers {
	
	private CarImagesService carImagesService;
	
	@Autowired
	public CarImagesControllers(CarImagesService carImagesService) {
		super();
		this.carImagesService = carImagesService;
	}
	
	
	@GetMapping("/getall")
	public DataResult<List<CarImages>> getAll() {
		return this.carImagesService.getAll();
	}
	
	@GetMapping("/getbycarid")
	public DataResult<List<CarImages>> getByCar_CarId(int carId) {
		return this.carImagesService.getCarImagesByCarId(carId);
	}
	
	@GetMapping("/getbycarımagesdetail")
	public DataResult<List<CarImageDto>> getCarImagesDetail() {
		return this.carImagesService.getCarImagesDetail();
	}

	
	
	
	@PostMapping("/addcarimages")
	public Result  add(@RequestParam("carId")   int carId, MultipartFile file) throws IOException {
		AddCarImagesRequest addCarImagesRequest= new AddCarImagesRequest();
		addCarImagesRequest.setCarId(carId);
		addCarImagesRequest.setFile(file);
		return this.carImagesService.add(addCarImagesRequest);
	}
	
	@PostMapping("/updatecarimages")
	public Result update(@Valid    UpdateCarImagesRequest updateCarImagesRequest, MultipartFile file)throws IOException {
		updateCarImagesRequest.setFile(file);
		return this.carImagesService.update(updateCarImagesRequest);
	}
	
	
	@DeleteMapping("/deletecarimages")
	public Result delete( @Valid  DeleteCarImagesRequest deleteCarImagesRequest) {
		return this.carImagesService.delete(deleteCarImagesRequest);
	}
	
	
}
