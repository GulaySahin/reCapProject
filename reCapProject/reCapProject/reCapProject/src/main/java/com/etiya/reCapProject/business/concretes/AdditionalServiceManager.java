package com.etiya.reCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.reCapProject.business.abstracts.AdditionalServicesService;
import com.etiya.reCapProject.business.constants.Messages;
import com.etiya.reCapProject.core.utilities.businnes.BusinnesRules;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.ErrorResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.reCapProject.core.utilities.results.SuccessResult;
import com.etiya.reCapProject.dataAccess.abstracts.AdditionalServiceDao;
import com.etiya.reCapProject.entities.concretes.AdditionalService;
import com.etiya.reCapProject.entities.requests.additionalRequest.AddAdditionalServiceRequest;
import com.etiya.reCapProject.entities.requests.additionalRequest.DeleteAdditionalServiceRequest;
import com.etiya.reCapProject.entities.requests.additionalRequest.UpdateAdditionalServiceRequest;

@Service
public class AdditionalServiceManager implements AdditionalServicesService {
	
	private AdditionalServiceDao additionalServiceDao;
	
	@Autowired
	public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao) {
		super();
		this.additionalServiceDao = additionalServiceDao;
	}

	@Override
	public DataResult<List<AdditionalService>> getAll() {
		return new SuccessDataResult<List<AdditionalService>>(this.additionalServiceDao.findAll(),Messages.List);
	}

	@Override
	public DataResult<AdditionalService> getById(int id) {
		return new SuccessDataResult<AdditionalService>(this.additionalServiceDao.getById(id),Messages.Listed);
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
		return new SuccessResult(Messages.Add);
	}

	@Override
	public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
		
				
		AdditionalService additionalService=this.additionalServiceDao.getById(updateAdditionalServiceRequest.getAdditionalId());
		additionalService.setAdditionalId(updateAdditionalServiceRequest.getAdditionalId());
		additionalService.setAdditionalName(updateAdditionalServiceRequest.getAdditionalName());
		additionalService.setAdditionalPrice(updateAdditionalServiceRequest.getAdditionalPrice());
		
		this.additionalServiceDao.save(additionalService);
		return new SuccessResult(Messages.Update);
	}

	@Override
	public Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) {
		AdditionalService additionalService= new AdditionalService();
		additionalService.setAdditionalId(deleteAdditionalServiceRequest.getAdditionalId());
		
		this.additionalServiceDao.delete(additionalService);
		return new SuccessResult(Messages.Delete);
	}
	
	private Result  checkAddionalServiceNameDuplication(String additionalServiceName) {
		if (this.additionalServiceDao.existsAdditionalServiceByadditionalName(additionalServiceName)) {
			return new ErrorResult(Messages.additionalError);
		}
		return new SuccessResult();
	}

	
}
