package com.etiya.reCapProject.business.concretes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.etiya.reCapProject.business.abstracts.CarImagesService;
import com.etiya.reCapProject.business.constants.Messages;
import com.etiya.reCapProject.business.constants.messages.CarImagesMessages;
import com.etiya.reCapProject.business.paths.Paths;
import com.etiya.reCapProject.core.fileHelper.FileHelperService;
import com.etiya.reCapProject.core.utilities.businnes.BusinnesRules;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.ErrorDataResult;
import com.etiya.reCapProject.core.utilities.results.ErrorResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.reCapProject.core.utilities.results.SuccessResult;
import com.etiya.reCapProject.dataAccess.abstracts.CarDao;
import com.etiya.reCapProject.dataAccess.abstracts.CarImageDao;
import com.etiya.reCapProject.entities.concretes.Car;
import com.etiya.reCapProject.entities.concretes.CarImages;
import com.etiya.reCapProject.entities.dtos.CarImageDto;
import com.etiya.reCapProject.entities.requests.carImageRequest.AddCarImagesRequest;
import com.etiya.reCapProject.entities.requests.carImageRequest.DeleteCarImagesRequest;
import com.etiya.reCapProject.entities.requests.carImageRequest.UpdateCarImagesRequest;


@Service
public class CarImagesManager implements CarImagesService{
	
	private CarImageDao carImageDao;
	private CarDao carDao;
	private FileHelperService fileHelperService;
	private ModelMapper modelMapper;
	
	@Autowired
	public CarImagesManager(CarImageDao carImageDao,CarDao carDao
			,FileHelperService fileHelperService,
			 ModelMapper modelMapper) {
		super();
		this.carImageDao = carImageDao;
		this.carDao=carDao;
		this.fileHelperService=fileHelperService;
		this.modelMapper=modelMapper;
	}
	
	
	
	@Override
	public DataResult<List<CarImages>> getAll() {
		return new SuccessDataResult<List<CarImages>>(this.carImageDao.findAll(),CarImagesMessages.GetAll);
	}

	@Override
	public DataResult<List<CarImageDto>> getCarImagesDetail() {
		List<CarImages> carImagess = this.carImageDao.findAll();

		List<CarImageDto> carImageDto = carImagess.stream()
				.map(carImages -> modelMapper.map(carImages, CarImageDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<CarImageDto>>(carImageDto,CarImagesMessages.GetAll);
	}

	@Override
	public Result add(AddCarImagesRequest addCarImagesRequest) throws IOException {
		
		var result= BusinnesRules.run(checkImageIsNull(addCarImagesRequest.getFile())
				,checkIfCarImagesAddController(addCarImagesRequest.getCarId(), 5)
				,checkImageType(addCarImagesRequest.getFile()));
		if (result!=null) {
			return result;
		}
		
		CarImages carImages = new CarImages();

		Date dateNow= new java.sql.Date(new java.util.Date().getTime());
		String randomImageName=UUID.randomUUID().toString();

		fileHelperService.addCarImagePathName(addCarImagesRequest, randomImageName );
	
		
		Car car = this.carDao.getById(addCarImagesRequest.getCarId());
		car.setCarId(addCarImagesRequest.getCarId());
		
		carImages.setDate(dateNow);
		carImages.setCar(car);
		carImages.setImagePath(randomImageName);
		
		this.carImageDao.save(carImages);
		return new SuccessResult( CarImagesMessages.Add);
	}

	@Override
	public Result update(UpdateCarImagesRequest updateCarImagesRequest) throws IOException{
		var result= BusinnesRules.run(checkImageIsNull(updateCarImagesRequest.getFile()),checkIfCarImagesAddController(updateCarImagesRequest.getCarId(), 5),checkImageType(updateCarImagesRequest.getFile()));
		if (result!=null) {
			return result;
		}
		
		
		CarImages carImages = this.carImageDao.getById(updateCarImagesRequest.getId());
		@SuppressWarnings("unused")
		Date dateNow= new java.sql.Date(new java.util.Date().getTime());
		String randomImageName=UUID.randomUUID().toString();
		
		fileHelperService.updateCarImagePathName(updateCarImagesRequest, randomImageName);
		
		Car car = this.carDao.getById(updateCarImagesRequest.getCarId());
		car.setCarId(updateCarImagesRequest.getCarId());
		
		carImages.setId(updateCarImagesRequest.getId());		
		carImages.setCar(car);
		carImages.setDate(dateNow);
		carImages.setImagePath(randomImageName);
		
		this.carImageDao.save(carImages);
		return new SuccessResult(CarImagesMessages.Update);
	}
	
	

	@Override
	public Result delete(DeleteCarImagesRequest deleteCarImagesRequest) {
		
		
		
		CarImages carImages = new CarImages();
		carImages.setId(deleteCarImagesRequest.getId());
		

		
		this.carImageDao.delete(carImages);
		return new SuccessResult(CarImagesMessages.Delete);
	}
	
	
	//Arabanın 5'ten fazla resmi var mı kontrolü
	private Result checkIfCarImagesAddController(int carId, int limit) {
		
		if (this.carImageDao.countByCar_CarId(carId)>limit) {
			return new ErrorResult(CarImagesMessages.ErrorIfCarHasMoreImages);
		}
		
		return new SuccessResult();
	}
	
	
	private Result checkImageIsNull(MultipartFile file) {
		if (file == null) {
			return new ErrorResult(CarImagesMessages.ErrorCarImageNulls);
		}
		return new SuccessResult();
	}



	@Override
	public DataResult<List<CarImages>> getCarImagesByCarId(int carId) {
		return new SuccessDataResult<List<CarImages>>(returnCarImageWithDefaultImageIfCarImageIsNull(carId).getData(),
				Messages.Listed);
	}
	
	
	//Eğer arabanın resmi yoksa default resim atama
	private DataResult<List<CarImages>> returnCarImageWithDefaultImageIfCarImageIsNull(int carId) {

		if (this.carImageDao.existsByCar_CarId(carId)) {
			return new ErrorDataResult<List<CarImages>>(this.carImageDao.getByCar_CarId(carId));
		}

		List<CarImages> carImages = new ArrayList<CarImages>();
		CarImages carImage = new CarImages();
		carImage.setImagePath(Paths.CAR_IMAGE_DEFAULT_PATH);

		carImages.add(carImage);

		return new SuccessDataResult<List<CarImages>>(carImages, CarImagesMessages.ErrorCarImageNull);

	}
	
	//Arabanın resmi boş mu yada format yanlış mı kontrolü
	private Result checkImageType(MultipartFile file) {
		if(checkImageIsNull(file).isSuccess()) {
			if(!file.getContentType().substring(file.getContentType().indexOf("/")+1).equals("jpeg")
				&& !file.getContentType().toString().substring(file.getContentType().indexOf("/") + 1).equals("jpg")
				&& !file.getContentType().toString().substring(file.getContentType().indexOf("/") + 1).equals("png")){
				System.out.println(file.getContentType());
				return new ErrorResult(CarImagesMessages.ErrorCarImageType);

				}
	}
		return new SuccessResult();

}



	
	
}



