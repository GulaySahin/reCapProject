package com.etiya.reCapProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.reCapProject.business.abstracts.CorporateCustomerService;
import com.etiya.reCapProject.business.constants.Messages;
import com.etiya.reCapProject.business.constants.messages.CorporateCustomerMessages;
import com.etiya.reCapProject.business.constants.messages.UserMessages;
import com.etiya.reCapProject.core.utilities.businnes.BusinnesRules;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.ErrorResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.reCapProject.core.utilities.results.SuccessResult;
import com.etiya.reCapProject.dataAccess.abstracts.CorporateCustomerDao;
import com.etiya.reCapProject.dataAccess.abstracts.CustomerDao;
import com.etiya.reCapProject.entities.concretes.CorporateCustomer;
import com.etiya.reCapProject.entities.dtos.CorporateCustomerDto;
import com.etiya.reCapProject.entities.requests.corporateRequest.AddCorporateCustomerRequest;
import com.etiya.reCapProject.entities.requests.corporateRequest.DeleteCorporateCustomerRequest;
import com.etiya.reCapProject.entities.requests.corporateRequest.UpdateCorporateCustomerRequest;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {
	
	private CorporateCustomerDao corporateCustomerDao;
	private CustomerDao customerDao;
	private ModelMapper modelMapper;
	
	@Autowired
	public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao,CustomerDao customerDao,ModelMapper modelMapper) {
		super();
		this.corporateCustomerDao=corporateCustomerDao;
		this.customerDao = customerDao;
		this.modelMapper= modelMapper;
	}

	@Override
	public Result add(AddCorporateCustomerRequest addCorporateCustomerRequest) {
		
		var result = BusinnesRules.run(checkEmail(addCorporateCustomerRequest.getEMail()));
				
		if (result != null) {
			return result;
		}
		
		
		CorporateCustomer corporateCustomer= new CorporateCustomer();
		corporateCustomer.setCompanyName(addCorporateCustomerRequest.getCompanyName());
		corporateCustomer.setTaxNumber(addCorporateCustomerRequest.getTaxNumber());
		corporateCustomer.setEMail(addCorporateCustomerRequest.getEMail());
		corporateCustomer.setPassword(addCorporateCustomerRequest.getPassword());
		
		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult(CorporateCustomerMessages.Add);
	}

	@Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		
		CorporateCustomer corporateCustomer= this.corporateCustomerDao.getById(updateCorporateCustomerRequest.getId());
		
		corporateCustomer.setId(updateCorporateCustomerRequest.getId());
		
		corporateCustomer.setCompanyName(updateCorporateCustomerRequest.getCompanyName());
		corporateCustomer.setTaxNumber(updateCorporateCustomerRequest.getTaxNumber());
		corporateCustomer.setEMail(updateCorporateCustomerRequest.getEMail());
		corporateCustomer.setPassword(updateCorporateCustomerRequest.getPassword());
		
		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult(CorporateCustomerMessages.Update);
		
	}

	@Override
	public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
		CorporateCustomer corporateCustomer= this.corporateCustomerDao.getById(deleteCorporateCustomerRequest.getId());
		
		this.corporateCustomerDao.delete(corporateCustomer);
		return new SuccessResult(CorporateCustomerMessages.Delete);
	}

	@Override
	public DataResult<List<CorporateCustomerDto>> getAll() {
		List<CorporateCustomer> corporateCustomers= this.corporateCustomerDao.findAll();
		List<CorporateCustomerDto> corporateCustomerDtos= 
				corporateCustomers.stream().map(corporateCustomer->modelMapper.map(corporateCustomer, CorporateCustomerDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<CorporateCustomerDto>>(corporateCustomerDtos,Messages.List);
	}
	
	
	@Override
	public DataResult<CorporateCustomerDto> getById(int id) {
		
		CorporateCustomer corporateCustomer= this.corporateCustomerDao.getById(id);
		CorporateCustomerDto corporateCustomerDto=modelMapper.map(corporateCustomer, CorporateCustomerDto.class);
		return new SuccessDataResult<CorporateCustomerDto>(corporateCustomerDto,Messages.Listed);
	}
	
	
	//aynı e-mail var mı yok mu kontrol methodu
	private Result checkEmail(String eMail) {
		if(this.customerDao.existsCustomerByeMail(eMail)) {
			return new ErrorResult(UserMessages.ErrorMailIsAlreadyExist);
		}
		return new SuccessResult();
	}

	
}
