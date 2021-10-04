package com.etiya.reCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.reCapProject.business.abstracts.IndividualCustomerService;
import com.etiya.reCapProject.business.constants.Messages;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.reCapProject.core.utilities.results.SuccessResult;
import com.etiya.reCapProject.dataAccess.abstracts.IndividualCustomerDao;
import com.etiya.reCapProject.entities.concretes.IndividualCustomer;
import com.etiya.reCapProject.entities.requests.individualCustomerRequest.AddIndividualCustomerRequest;
import com.etiya.reCapProject.entities.requests.individualCustomerRequest.DeleteIndividualCustomerRequest;
import com.etiya.reCapProject.entities.requests.individualCustomerRequest.UpdateIndividualCustomerRequest;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {
	
	private IndividualCustomerDao individualCustomerDao;
	
	@Autowired
	public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao) {
		super();
		this.individualCustomerDao = individualCustomerDao;
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
		return new SuccessResult(Messages.Add);
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
		return new SuccessResult(Messages.Update);
	}

	@Override
	public Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
		
		IndividualCustomer individualCustomer= new IndividualCustomer();
		
		individualCustomer.setId(this.individualCustomerDao.getByIdentityNumber
				(deleteIndividualCustomerRequest.getIdentityNumber()).getId());
		
		this.individualCustomerDao.deleteById(individualCustomer.getId());
		return new SuccessResult(Messages.Delete);
		
	}

	@Override
	public DataResult<List<IndividualCustomer>> getAll() {
		return new SuccessDataResult<List<IndividualCustomer>>(this.individualCustomerDao.findAll(),Messages.List);
	}
	
	
}
