package com.etiya.reCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.etiya.reCapProject.core.utilities.results.ErrorResult;

import com.etiya.reCapProject.business.abstracts.BrandService;
import com.etiya.reCapProject.business.constants.Messages;
import com.etiya.reCapProject.core.utilities.businnes.BusinnesRules;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.reCapProject.core.utilities.results.SuccessResult;
import com.etiya.reCapProject.dataAccess.abstracts.BrandDao;
import com.etiya.reCapProject.entities.concretes.Brand;
import com.etiya.reCapProject.entities.requests.brandRequest.AddBrandRequest;
import com.etiya.reCapProject.entities.requests.brandRequest.DeleteBrandRequest;
import com.etiya.reCapProject.entities.requests.brandRequest.UpdateBrandRequest;

@Service
public class BrandManager implements BrandService {
	
	private BrandDao brandDao;
	
	@Autowired
	public BrandManager(BrandDao brandDao) {
		super();
		this.brandDao = brandDao;
	}

	@Override
	public DataResult<List<Brand>> getByBrandId(int brandId) {
		List<Brand> brands= this.brandDao.getByBrandId(brandId);
		return new SuccessDataResult<List<Brand>>(brands,Messages.Listed);
	}

	@Override
	public DataResult<List<Brand>> getAll() {
		List<Brand> brands= this.brandDao.findAll();
		return new SuccessDataResult<List<Brand>>(brands,Messages.List);
		
	}

	@Override
	public Result add(AddBrandRequest addBrandRequest) {
		
		var result = BusinnesRules.run(checkBrandName(addBrandRequest.getBrandName()));
		if (result != null) {
			return result;
		}
		Brand brand= new Brand();
		brand.setBrandName(addBrandRequest.getBrandName());
		
		this.brandDao.save(brand);
		return new SuccessResult(Messages.Add);
	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
		
		Brand brand = this.brandDao.getById(updateBrandRequest.getBrandId());
		brand.setBrandName(updateBrandRequest.getBrandName());
		brand.setBrandId(updateBrandRequest.getBrandId());
		
		this.brandDao.save(brand);
		return new SuccessResult(Messages.Update);
		
	}

	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {
		Brand brand = new Brand();
		brand.setBrandId(deleteBrandRequest.getBrandId());
		
		this.brandDao.delete(brand);
		return new SuccessResult(Messages.Delete);
		
	}
	

	public Result checkBrandName(String brandName) {

		if (this.brandDao.existsBrandByBrandName(brandName)) {
			return new ErrorResult(Messages.brandError);
		}
		return new SuccessResult();
	}
}
