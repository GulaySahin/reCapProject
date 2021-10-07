package com.etiya.reCapProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.etiya.reCapProject.core.utilities.results.ErrorResult;

import com.etiya.reCapProject.business.abstracts.BrandService;
import com.etiya.reCapProject.business.constants.messages.BrandMessages;
import com.etiya.reCapProject.core.utilities.businnes.BusinnesRules;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.reCapProject.core.utilities.results.SuccessResult;
import com.etiya.reCapProject.dataAccess.abstracts.BrandDao;
import com.etiya.reCapProject.entities.concretes.Brand;
import com.etiya.reCapProject.entities.dtos.BrandDto;
import com.etiya.reCapProject.entities.requests.brandRequest.AddBrandRequest;
import com.etiya.reCapProject.entities.requests.brandRequest.DeleteBrandRequest;
import com.etiya.reCapProject.entities.requests.brandRequest.UpdateBrandRequest;

@Service
public class BrandManager implements BrandService {
	
	private BrandDao brandDao;
	private ModelMapper modelMapper;
	
	@Autowired
	public BrandManager(BrandDao brandDao,ModelMapper modelMapper) {
		super();
		this.brandDao = brandDao;
		this.modelMapper=modelMapper;
	}

	@Override
	public DataResult<BrandDto> getByBrandId(int brandId) {
		Brand brand= this.brandDao.getById(brandId);
		BrandDto brandDto = modelMapper.map(brand, BrandDto.class);
		
		return new SuccessDataResult<BrandDto>(brandDto,BrandMessages.GetById);
	}

	@Override
	public DataResult<List<BrandDto>> getAll() {
		List<Brand> brands= this.brandDao.findAll();
		List<BrandDto> brandDtos= brands.stream().map(brand->modelMapper.map(brand, BrandDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<BrandDto>>(brandDtos,BrandMessages.GetAll);
		
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
		return new SuccessResult(BrandMessages.Add);
	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
		
		Brand brand = this.brandDao.getById(updateBrandRequest.getBrandId());
		brand.setBrandName(updateBrandRequest.getBrandName());
		brand.setBrandId(updateBrandRequest.getBrandId());
		
		this.brandDao.save(brand);
		return new SuccessResult(BrandMessages.Update);
		
	}

	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {
		Brand brand = new Brand();
		brand.setBrandId(deleteBrandRequest.getBrandId());
		
		this.brandDao.delete(brand);
		return new SuccessResult(BrandMessages.Delete);
		
	}
	
	
	//aynı isimde marka adı var mı yok mu kontrol methodu
	public Result checkBrandName(String brandName) {

		if (this.brandDao.existsBrandByBrandName(brandName)) {
			return new ErrorResult(BrandMessages.ErrorCheckBrandName);
		}
		return new SuccessResult();
	}
}
