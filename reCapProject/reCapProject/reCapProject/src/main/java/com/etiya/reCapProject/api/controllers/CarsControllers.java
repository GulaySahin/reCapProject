package com.etiya.reCapProject.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.reCapProject.business.abstracts.CarService;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.entities.concretes.Car;
import com.etiya.reCapProject.entities.dtos.CarDetailsDto;
import com.etiya.reCapProject.entities.requests.carRequest.AddCarRequest;
import com.etiya.reCapProject.entities.requests.carRequest.DeleteCarRequest;
import com.etiya.reCapProject.entities.requests.carRequest.UpdateCarRequest;

@RestController
@RequestMapping("/api/cars")
public class CarsControllers {
	
	private CarService carService;
	
	@Autowired
	public CarsControllers(CarService carService) {
		super();
		this.carService = carService;
	}
	
	@GetMapping("/getcarıd")
	public DataResult<Car> getById(int carId) {
		return this.carService.getById(carId);
		
	}
	
	@GetMapping("/getallcar")
	public DataResult<List<Car>> getAll() {
		return this.carService.getAll();
		
	}
	
	@GetMapping("/getallcity")
	public DataResult<List<Car>> getByCarCity(String city) {
		return this.carService.getByCarCity(city);
	}
	
	@GetMapping("/caristrue")
	public DataResult<List<Car>> IsCarCareIsTrue() {
		return this.carService.IsCarCareIsTrue();
	}

	
	@GetMapping("/carisfalse")
	public DataResult<List<Car>> IsCarCareIsFalse() {
		return this.carService.IsCarCareIsFalse();
	}



	
	
	
	@GetMapping("/getcarwithdetail")
	public DataResult<List<CarDetailsDto>> getCarWithDetails() {
		return this.carService.getCarWithDetails();
	}
	
	@GetMapping("/getbybrandıd")
	public DataResult<List<Car>> getByBrand_brandId(int brandId) {
		return this.carService.getByBrand_brandId(brandId);
			
	}
	
	@GetMapping("/getbycolorıd")
	public DataResult<List<Car>> getByColor_colorId(int colorId) {
		return this.carService.getByColor_colorId(colorId);
	}

	
	@PostMapping("/addcar")
	public Result add(@RequestBody @Valid AddCarRequest addCarRequest) {
		return this.carService.add(addCarRequest);
	}

	@PostMapping("/updatecar")
	public Result update(@RequestBody @Valid UpdateCarRequest updateCarRequest) {
		return this.carService.update(updateCarRequest);
	}
	
	@DeleteMapping("/deletecar")
	public Result delete( @Valid  DeleteCarRequest deleteCarRequest) {
		return this.carService.delete(deleteCarRequest);
	}

	
}
