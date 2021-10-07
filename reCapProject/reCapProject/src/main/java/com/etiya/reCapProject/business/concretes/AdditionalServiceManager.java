package com.etiya.reCapProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.reCapProject.business.abstracts.AdditionalServicesService;
import com.etiya.reCapProject.business.constants.Messages;
import com.etiya.reCapProject.business.constants.messages.AdditionalServiceMessages;
import com.etiya.reCapProject.core.utilities.businnes.BusinnesRules;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.ErrorResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.reCapProject.core.utilities.results.SuccessResult;
import com.etiya.reCapProject.dataAccess.abstracts.AdditionalServiceDao;
import com.etiya.reCapProject.entities.concretes.AdditionalService;
import com.etiya.reCapProject.entities.dtos.AdditionalServiceDetailDto;
import com.etiya.reCapProject.entities.requests.additionalRequest.AddAdditionalServiceRequest;
import com.etiya.reCapProject.entities.requests.additionalRequest.DeleteAdditionalServiceRequest;
import com.etiya.reCapProject.entities.requests.additionalRequest.UpdateAdditionalServiceRequest;

@Service
public class AdditionalServiceManager implements AdditionalServicesService {
	
	private AdditionalServiceDao additionalServiceDao;
	private ModelMapper modelMapper;
	
	@Autowired
	public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao, ModelMapper modelMapper) {
		super();
		this.additionalServiceDao = additionalServiceDao;
		this.modelMapper=modelMapper;
	}

	@Override
	public DataResult<List<AdditionalServiceDetailDto>> getAll() {
		
		List<AdditionalService> additionalServices=this.additionalServiceDao.findAll();
		
		List<AdditionalServiceDetailDto>additionalServiceDetailDtos=additionalServices.stream()
				.map(additionalService->modelMapper
						.map(additionalService, AdditionalServiceDetailDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<AdditionalServiceDetailDto>>(additionalServiceDetailDtos,AdditionalServiceMessages.GetAll);
		
	}
	
	@Override
	public DataResult<List<AdditionalServiceDetailDto>> getByRentalId(int rentalId) {
		
		List<AdditionalService> additionalServices=this.additionalServiceDao.getByRentAls_rentAlId(rentalId);
		
		List<AdditionalServiceDetailDto>additionalServiceDetailDtos=additionalServices.stream()
				.map(additionalService-> modelMapper.map(additionalService, AdditionalServiceDetailDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<AdditionalServiceDetailDto>>(additionalServiceDetailDtos,AdditionalServiceMessages.GetById);
	}
	
	


	@Override
	public DataResult<AdditionalServiceDetailDto> getById(int id) {
		
		AdditionalService additionalService=this.additionalServiceDao.getById(id);
		
		AdditionalServiceDetailDto additionalServiceDetailDto=modelMapper.map(additionalService, AdditionalServiceDetailDto.class);
		
		return new SuccessDataResult<AdditionalServiceDetailDto>(additionalServiceDetailDto,AdditionalServiceMessages.GetById);
		
		
		
	}

	@Override
	public Result add(AddAdditionalServiceRequest addAdditionalServiceRequest) {
		
		var result = BusinnesRules.run(checkAddionalServiceNameDuplication(addAdditionalServiceRequest.getAdditionalName()));
		
		if (result != null) {
			return result;
		}
		
		AdditionalService additionalService= new AdditionalService();
		additionalService.setAdditionalName(addAdditionalServiceRequest.getAdditionalName());
		additionalService.setAdditionalPrice(addAdditionalServiceRequest.getAdditionalPrice());
		
		this.additionalServiceDao.save(additionalService);
		return new SuccessResult(AdditionalServiceMessages.Add);
	}

	@Override
	public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
		
				
		AdditionalService additionalService=this.additionalServiceDao.getById(updateAdditionalServiceRequest.getAdditionalId());
		additionalService.setAdditionalId(updateAdditionalServiceRequest.getAdditionalId());
		additionalService.setAdditionalName(updateAdditionalServiceRequest.getAdditionalName());
		additionalService.setAdditionalPrice(updateAdditionalServiceRequest.getAdditionalPrice());
		
		this.additionalServiceDao.save(additionalService);
		return new SuccessResult(AdditionalServiceMessages.Update);
	}

	@Override
	public Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) {
		AdditionalService additionalService= new AdditionalService();
		additionalService.setAdditionalId(deleteAdditionalServiceRequest.getAdditionalId());
		
		this.additionalServiceDao.delete(additionalService);
		return new SuccessResult(AdditionalServiceMessages.Delete);
	}
	
	//Aynı isimde hizmet var mı yok mu kontrol methodu
	private Result  checkAddionalServiceNameDuplication(String additionalServiceName) {
		if (this.additionalServiceDao.existsAdditionalServiceByadditionalName(additionalServiceName)) {
			return new ErrorResult(Messages.additionalError);
		}
		return new SuccessResult();
	}

	
	

	
}
