package com.etiya.reCapProject.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.reCapProject.business.abstracts.CareService;
import com.etiya.reCapProject.business.constants.messages.CarCareMessages;
import com.etiya.reCapProject.core.utilities.businnes.BusinnesRules;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.ErrorResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.reCapProject.core.utilities.results.SuccessResult;
import com.etiya.reCapProject.dataAccess.abstracts.CarDao;
import com.etiya.reCapProject.dataAccess.abstracts.CareDao;
import com.etiya.reCapProject.dataAccess.abstracts.RentAlDao;
import com.etiya.reCapProject.entities.concretes.Car;
import com.etiya.reCapProject.entities.concretes.Care;
import com.etiya.reCapProject.entities.dtos.CarCareDto;
import com.etiya.reCapProject.entities.requests.careRequest.AddCareRequest;
import com.etiya.reCapProject.entities.requests.careRequest.DeleteCareRequest;
import com.etiya.reCapProject.entities.requests.careRequest.UpdateCareRequest;

@Service
public class CareManager implements CareService {
	
	private CareDao careDao;
	private RentAlDao rentAlDao;
	private CarDao carDao;
	private ModelMapper modelMapper;
	
	@Autowired
	public CareManager(CareDao careDao,RentAlDao rentAlDao,CarDao carDao, ModelMapper modelMapper) {
		super();
		this.careDao = careDao;
		this.rentAlDao=rentAlDao;
		this.carDao=carDao;
		this.modelMapper=modelMapper;
	}

	@Override
	public DataResult<List<CarCareDto>> getAll() {
		
		List<Care> cares=this.careDao.findAll();
		
		List<CarCareDto> carCareDtos= new ArrayList<CarCareDto>();
		
		for (Care care : cares) {
			CarCareDto carCareDto=modelMapper.map(care, CarCareDto.class);
			carCareDto.setCarName(this.carDao.getById(care.getCar().getCarId()).getCarName());
			carCareDtos.add(carCareDto);
		}
		return new SuccessDataResult<List<CarCareDto>>(carCareDtos,CarCareMessages.GetAllCarsInCare);
	}
	
	
	@Override
	public DataResult<CarCareDto> getById(int id) {
		
		Care care=this.careDao.getById(id);
		
		CarCareDto carCareDto=modelMapper.map(care, CarCareDto.class);
		carCareDto.setCarName(this.carDao.getById(care.getCar().getCarId()).getCarName());
		return new SuccessDataResult<CarCareDto>(carCareDto,CarCareMessages.GetById);
	}
	
	
	
	@Override
	public Result add(AddCareRequest addCareRequest) {
		
		var result = BusinnesRules.run(checkIsCarRental(addCareRequest.getCarId()));
		

		if (result != null) {
			return result;
		}
	
		Car car = this.carDao.getById(addCareRequest.getCarId());
		car.setCarCare(false);
		
		Care care= new Care();
		care.setCareId(addCareRequest.getCareId());
		care.setLocation(addCareRequest.getLocation());
		care.setStartDate(addCareRequest.getStartDate());
		care.setFinishDate(addCareRequest.getFinishDate());
		
		care.setCar(car);
		
		this.careDao.save(care);
		return new SuccessResult(CarCareMessages.SendCarToCare);
	}

	@Override
	public Result update(UpdateCareRequest updateCareRequest) {
		
		var result = BusinnesRules.run(checkIsCarRental(updateCareRequest.getCarId()));
		

		if (result != null) {
			return result;
		}

		Car car = new Car();
		car.setCarId(updateCareRequest.getCarId());
		
		Care care= this.careDao.getById(updateCareRequest.getCareId());
		care.setCareId(updateCareRequest.getCareId());
		care.setLocation(updateCareRequest.getLocation());
		care.setStartDate(updateCareRequest.getStartDate());
		care.setFinishDate(updateCareRequest.getFinishDate());
		
		care.setCar(car);
		
		this.careDao.save(care);
		return new SuccessResult(CarCareMessages.Update);
	}

	@Override
	public Result delete(DeleteCareRequest deleteCareRequest) {
		
		Car car = new Car();
		car.setCarId(deleteCareRequest.getCarId());
		
		Care care = new Care();
		care.setCareId(deleteCareRequest.getCareId());
		
		care.setCar(car);
		
		this.careDao.delete(care);
		return new SuccessResult(CarCareMessages.Delete);

	}
	
	//Araç müşteride ise bakıma gönderilemez methodu
	private Result checkIsCarRental(int carId) {
		
		if (!this.rentAlDao.getByCar_CarId(carId).isEmpty()) {
			return new ErrorResult(CarCareMessages.ErrorIfCarIsNotAvailableToGoToCare);
		}
		return new SuccessResult();
	}

	
	
	
	

	
}
