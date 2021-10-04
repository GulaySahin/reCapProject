package com.etiya.reCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.reCapProject.business.abstracts.CorporateCustomerService;
import com.etiya.reCapProject.business.constants.Messages;
import com.etiya.reCapProject.core.utilities.businnes.BusinnesRules;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.ErrorResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.reCapProject.core.utilities.results.SuccessResult;
import com.etiya.reCapProject.dataAccess.abstracts.CorporateCustomerDao;
import com.etiya.reCapProject.dataAccess.abstracts.CustomerDao;
import com.etiya.reCapProject.entities.concretes.CorporateCustomer;
import com.etiya.reCapProject.entities.requests.corporateRequest.AddCorporateCustomerRequest;
import com.etiya.reCapProject.entities.requests.corporateRequest.DeleteCorporateCustomerRequest;
import com.etiya.reCapProject.entities.requests.corporateRequest.UpdateCorporateCustomerRequest;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {
	
	private CorporateCustomerDao corporateCustomerDao;
	private CustomerDao customerDao;
	
	@Autowired
	public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao,CustomerDao customerDao) {
		super();
		this.corporateCustomerDao=corporateCustomerDao;
		this.customerDao = customerDao;
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
		return new SuccessResult(Messages.Add);
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
		return new SuccessResult(Messages.Update);
		
	}

	@Override
	public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
		CorporateCustomer corporateCustomer= this.corporateCustomerDao.getById(deleteCorporateCustomerRequest.getId());
		
		this.corporateCustomerDao.delete(corporateCustomer);
		return new SuccessResult(Messages.Delete);
	}

	@Override
	public DataResult<List<CorporateCustomer>> getAll() {
		return new SuccessDataResult<List<CorporateCustomer>>(this.corporateCustomerDao.findAll(),Messages.List);
	}
	
	
	private Result checkEmail(String eMail) {
		if(this.customerDao.existsCustomerByeMail(eMail)) {
			return new ErrorResult(Messages.EmailError);
		}
		return new SuccessResult();
	}
}
