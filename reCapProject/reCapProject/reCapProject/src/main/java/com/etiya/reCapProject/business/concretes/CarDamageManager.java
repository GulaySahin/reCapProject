package com.etiya.reCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.reCapProject.business.abstracts.CarDamageService;
import com.etiya.reCapProject.business.constants.Messages;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.reCapProject.core.utilities.results.SuccessResult;
import com.etiya.reCapProject.dataAccess.abstracts.CarDamageDao;
import com.etiya.reCapProject.entities.concretes.Car;
import com.etiya.reCapProject.entities.concretes.CarDamage;
import com.etiya.reCapProject.entities.requests.carDamageRequest.AddCarDamageRequest;
import com.etiya.reCapProject.entities.requests.carDamageRequest.DeleteCarDamageRequest;
import com.etiya.reCapProject.entities.requests.carDamageRequest.UpdateCarDamageRequest;

@Service
public class CarDamageManager implements CarDamageService{
	
	private CarDamageDao carDamageDao;
	
	@Autowired
	public CarDamageManager(CarDamageDao carDamageDao) {
		super();
		this.carDamageDao = carDamageDao;
	}

	@Override
	public DataResult<List<CarDamage>> getAll() {
		return new SuccessDataResult<List<CarDamage>>(this.carDamageDao.findAll());
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
		return new SuccessResult(Messages.Add);
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
		return new SuccessResult(Messages.Update);
	}

	@Override
	public Result delete(DeleteCarDamageRequest deleteCarDamageRequest) {
		Car car = new Car();
		car.setCarId(deleteCarDamageRequest.getCarId());
		
		CarDamage carDamage = this.carDamageDao.getById(deleteCarDamageRequest.getDamageId());
		carDamage.setDamageId(deleteCarDamageRequest.getDamageId());

		this.carDamageDao.delete(carDamage);
		return new SuccessResult(Messages.Delete);
	}
}
