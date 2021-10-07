package com.etiya.reCapProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.reCapProject.business.abstracts.IndividualCustomerService;
import com.etiya.reCapProject.business.constants.messages.IndividualCustomerMessages;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.reCapProject.core.utilities.results.SuccessResult;
import com.etiya.reCapProject.dataAccess.abstracts.IndividualCustomerDao;
import com.etiya.reCapProject.entities.concretes.IndividualCustomer;
import com.etiya.reCapProject.entities.dtos.IndividualCustomerDto;
import com.etiya.reCapProject.entities.requests.individualCustomerRequest.AddIndividualCustomerRequest;
import com.etiya.reCapProject.entities.requests.individualCustomerRequest.DeleteIndividualCustomerRequest;
import com.etiya.reCapProject.entities.requests.individualCustomerRequest.UpdateIndividualCustomerRequest;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {
	
	private IndividualCustomerDao individualCustomerDao;
	private ModelMapper modelMapper;
	
	@Autowired
	public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao,ModelMapper modelMapper) {
		super();
		this.individualCustomerDao = individualCustomerDao;
		this.modelMapper=modelMapper;
	}

	@Override
	public Result add(AddIndividualCustomerRequest addIndividualCustomerRequest) {
		
		IndividualCustomer individualCustomer = new IndividualCustomer();
		individualCustomer.setFirstName(addIndividualCustomerRequest.getFirstName());
		individualCustomer.setLastName(addIndividualCustomerRequest.getLastName());
		individualCustomer.setEMail(addIndividualCustomerRequest.getEMail());
		individualCustomer.setPassword(addIndividualCustomerRequest.getPassword());
		individualCustomer.setIdentityNumber(addIndividualCustomerRequest.getIdentityNumber());
		this.individualCustomerDao.save(individualCustomer);
		return new SuccessResult(IndividualCustomerMessages.Add);
	}

	@Override
	public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		IndividualCustomer individualCustomer= this.individualCustomerDao.getByIdentityNumber
				(updateIndividualCustomerRequest.getIdentityNumber());
		
		individualCustomer.setId(updateIndividualCustomerRequest.getId());

		individualCustomer.setFirstName(updateIndividualCustomerRequest.getFirstName());
		individualCustomer.setLastName(updateIndividualCustomerRequest.getLastName());
		individualCustomer.setEMail(updateIndividualCustomerRequest.getEMail());
		individualCustomer.setPassword(updateIndividualCustomerRequest.getPassword());
		individualCustomer.setIdentityNumber(updateIndividualCustomerRequest.getIdentityNumber());
		this.individualCustomerDao.save(individualCustomer);
		return new SuccessResult(IndividualCustomerMessages.Update);
	}

	@Override
	public Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
		
		IndividualCustomer individualCustomer= new IndividualCustomer();
		
		individualCustomer.setId(this.individualCustomerDao.getByIdentityNumber
				(deleteIndividualCustomerRequest.getIdentityNumber()).getId());
		
		this.individualCustomerDao.deleteById(individualCustomer.getId());
		return new SuccessResult(IndividualCustomerMessages.Delete);
		
	}

	@Override
	public DataResult<List<IndividualCustomerDto>> getAll() {
		List<IndividualCustomer> individualCustomers= this.individualCustomerDao.findAll();
		List<IndividualCustomerDto> individualCustomerDtos= individualCustomers.stream()
				.map(individualCustomer->modelMapper.map(individualCustomer, IndividualCustomerDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<IndividualCustomerDto>>(individualCustomerDtos,IndividualCustomerMessages.GetAll);
	}

	@Override
	public DataResult<IndividualCustomerDto> getById(int id) {
		IndividualCustomer individualCustomer = this.individualCustomerDao.getById(id);
		IndividualCustomerDto individualCustomerDto=modelMapper.map(individualCustomer, IndividualCustomerDto.class);
		return new SuccessDataResult<IndividualCustomerDto>(individualCustomerDto,IndividualCustomerMessages.GetById);
	}
	
	
}
