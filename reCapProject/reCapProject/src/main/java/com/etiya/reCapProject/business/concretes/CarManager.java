package com.etiya.reCapProject.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.reCapProject.business.abstracts.BrandService;
import com.etiya.reCapProject.business.abstracts.CarImagesService;
import com.etiya.reCapProject.business.abstracts.CarService;
import com.etiya.reCapProject.business.abstracts.ColorService;
import com.etiya.reCapProject.business.constants.messages.CarMessages;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.reCapProject.core.utilities.results.SuccessResult;
import com.etiya.reCapProject.dataAccess.abstracts.CarDao;
import com.etiya.reCapProject.entities.concretes.Brand;
import com.etiya.reCapProject.entities.concretes.Car;
import com.etiya.reCapProject.entities.concretes.Color;
import com.etiya.reCapProject.entities.dtos.CarDetailsDto;
import com.etiya.reCapProject.entities.dtos.CarDto;
import com.etiya.reCapProject.entities.dtos.GetCarDetailDto;
import com.etiya.reCapProject.entities.requests.carRequest.AddCarRequest;
import com.etiya.reCapProject.entities.requests.carRequest.DeleteCarRequest;
import com.etiya.reCapProject.entities.requests.carRequest.UpdateCarRequest;


@Service
public class CarManager implements CarService {
	
	private CarDao carDao;
	private ModelMapper modelMapper;
	private BrandService brandService;
	private ColorService colorService;
	private CarImagesService carImagesService;
	
	@Autowired
	public CarManager(CarDao carDao,ModelMapper modelMapper,BrandService brandService,
			ColorService colorService,
			CarImagesService carImagesService) {
		super();
		this.carDao = carDao;
		this.modelMapper=modelMapper;
		this.brandService=brandService;
		this.colorService=colorService;
		this.carImagesService=carImagesService;
	}
	

	@Override
	public DataResult<List<CarDetailsDto>> getCarWithDetails() {
		return new SuccessDataResult<List<CarDetailsDto>>(this.carDao.getCarWithDetails()+ CarMessages.DetailsList);
	}

	
	@Override
	public DataResult<List<CarDto>> getAllCarsDetails() {
		
		List<Car> cars = this.carDao.findAll();
		
		List<CarDto> carDtos=new ArrayList<CarDto>();
		
		for (Car car : cars) {
			
			CarDto carDto= modelMapper.map(car, CarDto.class);
			carDto.setBrandDto(this.brandService.getByBrandId(car.getBrand().getBrandId()).getData());
			carDto.setColorDto(this.colorService.getByColorId(car.getColor().getColorId()).getData());
			
			carDtos.add(carDto);
			
			
		}
		return new SuccessDataResult<List<CarDto>>(carDtos,CarMessages.GetAll);
		
	}

	@Override
	public DataResult<GetCarDetailDto> getById(int carId) {
		
		Car car = this.carDao.getById(carId);
		
		GetCarDetailDto getCarDetailDto = modelMapper.map(car, GetCarDetailDto.class);
		getCarDetailDto.setBrandName(car.getBrand().getBrandName());
		getCarDetailDto.setColorName(car.getColor().getColorName());
		
		return new SuccessDataResult<GetCarDetailDto>(getCarDetailDto, CarMessages.GetById);
		
	}
	

	@Override
	public DataResult<List<Car>> getByCarCity(String city) {
		List<Car> cars=this.carDao.getByCity(city);
		return new SuccessDataResult<List<Car>>(cars, CarMessages.GetCarsByCityId);
	}
	
	
	
	@Override
	public DataResult<List<Car>> getByBrand_brandId(int brandId) {
		List<Car> cars= this.carDao.getByBrand_brandId(brandId);
		return new SuccessDataResult<List<Car>>(cars, CarMessages.GetCarsByBrandId);
	}


	@Override
	public DataResult<List<Car>> getByColor_colorId(int colorId) {
		List<Car> cars= this.carDao.getByColor_colorId(colorId);
		return new SuccessDataResult<List<Car>>(cars, CarMessages.GetCarsByColorId);
	}

	@Override
	public Result add(AddCarRequest addCarRequest) {
		
		Brand brand = new Brand();
		brand.setBrandId(addCarRequest.getBrandId());
		
		Color color= new Color();
		color.setColorId(addCarRequest.getColorId());
		
		Car car= new Car();
		car.setCarName(addCarRequest.getCarName());
		car.setCarCare(addCarRequest.isCarCare());
		car.setModelYear(addCarRequest.getModelYear());
		car.setDailyPrice(addCarRequest.getDailyPrice());
		car.setDescription(addCarRequest.getDescription());
		car.setFindexPoint(addCarRequest.getFindexPoint());
		car.setCity(addCarRequest.getCity());
		

		car.setBrand(brand);
		car.setColor(color);
		
		this.carDao.save(car);
		return new SuccessResult(CarMessages.Add);
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		
		Brand brand = new Brand();
		brand.setBrandId(updateCarRequest.getBrandId());
		
		Color color= new Color();
		color.setColorId(updateCarRequest.getColorId());
		
		Car car= this.carDao.getById(updateCarRequest.getCarId());
		car.setCarId(updateCarRequest.getCarId());
		car.setCarName(updateCarRequest.getCarName());
		car.setCarCare(updateCarRequest.isCarCare());
		car.setModelYear(updateCarRequest.getModelYear());
		car.setDailyPrice(updateCarRequest.getDailyPrice());
		car.setDescription(updateCarRequest.getDescription());
		car.setFindexPoint(updateCarRequest.getFindexPoint());
		car.setCity(updateCarRequest.getCity());


		

		car.setBrand(brand);
		car.setColor(color);
		
		this.carDao.save(car);
		return new SuccessResult(CarMessages.Update);

		
	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {
		
		Brand brand = new Brand();
		brand.setBrandId(deleteCarRequest.getBrandId());
		
		Color color= new Color();
		color.setColorId(deleteCarRequest.getColorId());
		

		Car car= new Car();
		car.setCarId(deleteCarRequest.getCarId());

		car.setBrand(brand);
		car.setColor(color);
		
		this.carDao.delete(car);
		return new SuccessResult(CarMessages.Delete);

		
	}


	@Override
	public DataResult<List<Car>> IsCarCareIsTrue() {
		return new SuccessDataResult<List<Car>>(this.carDao.IsCarCareIsTrue(),CarMessages.ChangeCarSituation);
	}


	@Override
	public DataResult<List<Car>> IsCarCareIsFalse() {
		return new SuccessDataResult<List<Car>>(this.carDao.IsCarCareIsFalse(),CarMessages.GetCarsAreNotInCare);
	}






	
	
	

	




	




	
}
