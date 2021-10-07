package com.etiya.reCapProject.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.reCapProject.business.abstracts.CarDamageService;
import com.etiya.reCapProject.business.abstracts.CarService;
import com.etiya.reCapProject.business.constants.messages.CarDamageInfoMessages;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.reCapProject.core.utilities.results.SuccessResult;
import com.etiya.reCapProject.dataAccess.abstracts.CarDamageDao;
import com.etiya.reCapProject.entities.concretes.Car;
import com.etiya.reCapProject.entities.concretes.CarDamage;
import com.etiya.reCapProject.entities.dtos.CarDamageDto;
import com.etiya.reCapProject.entities.requests.carDamageRequest.AddCarDamageRequest;
import com.etiya.reCapProject.entities.requests.carDamageRequest.DeleteCarDamageRequest;
import com.etiya.reCapProject.entities.requests.carDamageRequest.UpdateCarDamageRequest;

@Service
public class CarDamageManager implements CarDamageService {

	private CarDamageDao carDamageDao;
	private ModelMapper modelMapper;
	private CarService carService;

	@Autowired
	public CarDamageManager(CarDamageDao carDamageDao, ModelMapper modelMapper,CarService carService) {
		super();
		this.carDamageDao = carDamageDao;
		this.modelMapper = modelMapper;
		this.carService=carService;
	}

	@Override
	public DataResult<List<CarDamageDto>> getAll() {
	
		
		List<CarDamage> carDamages = this.carDamageDao.findAll();

		List<CarDamageDto> carDamageDtos = new ArrayList<CarDamageDto>();

		for (CarDamage carDamagess : carDamages) {
			
			CarDamageDto carDamageDto=modelMapper.map(carDamagess, CarDamageDto.class);
			carDamageDto.setCarName(this.carService.getById(carDamagess.getCar().getCarId()).getData().getCarName());
			carDamageDtos.add(carDamageDto);
		}

		return new SuccessDataResult<List<CarDamageDto>>(carDamageDtos, CarDamageInfoMessages.GetAll);

	}
	
	@Override
	public DataResult<CarDamageDto> getById(int id) {
		
		CarDamage carDamages= this.carDamageDao.getById(id);
		
		CarDamageDto carDamagesDto=modelMapper.map(carDamages, CarDamageDto.class);
		carDamagesDto.setCarName(this.carService.getById(carDamages.getCar().getCarId()).getData().getCarName());


        return new SuccessDataResult<CarDamageDto>(carDamagesDto,CarDamageInfoMessages.GetById);
    }		
	

	@Override
	public Result add(AddCarDamageRequest addCarDamageRequest) {
		Car car = new Car();
		car.setCarId(addCarDamageRequest.getCarId());

		CarDamage carDamage = new CarDamage();
		carDamage.setDamageId(addCarDamageRequest.getDamageId());
		carDamage.setDamageInfo(addCarDamageRequest.getDamageInfo());

		carDamage.setCar(car);

		this.carDamageDao.save(carDamage);
		return new SuccessResult(CarDamageInfoMessages.Add);
	}

	@Override
	public Result update(UpdateCarDamageRequest updateCarDamageRequest) {
		Car car = new Car();
		car.setCarId(updateCarDamageRequest.getCarId());

		CarDamage carDamage = this.carDamageDao.getById(updateCarDamageRequest.getDamageId());
		carDamage.setDamageId(updateCarDamageRequest.getDamageId());
		carDamage.setDamageInfo(updateCarDamageRequest.getDamageInfo());

		carDamage.setCar(car);

		this.carDamageDao.save(carDamage);
		return new SuccessResult(CarDamageInfoMessages.Update);
	}

	@Override
	public Result delete(DeleteCarDamageRequest deleteCarDamageRequest) {
		Car car = new Car();
		car.setCarId(deleteCarDamageRequest.getCarId());

		CarDamage carDamage = this.carDamageDao.getById(deleteCarDamageRequest.getDamageId());
		carDamage.setDamageId(deleteCarDamageRequest.getDamageId());

		this.carDamageDao.delete(carDamage);
		return new SuccessResult(CarDamageInfoMessages.Delete);
	}

	

}
