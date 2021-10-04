package com.etiya.reCapProject.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.reCapProject.business.abstracts.BrandService;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.entities.concretes.Brand;
import com.etiya.reCapProject.entities.requests.brandRequest.AddBrandRequest;
import com.etiya.reCapProject.entities.requests.brandRequest.DeleteBrandRequest;
import com.etiya.reCapProject.entities.requests.brandRequest.UpdateBrandRequest;

@RestController
@RequestMapping("/api/brands")
public class BrandsControllers {
	
	private BrandService brandService;
	
	@Autowired
	public BrandsControllers(BrandService brandService) {
		super();
		this.brandService = brandService;
	}
	
	@GetMapping("/getbrandıd")
	public DataResult<List<Brand>> getByBrandId(int brandId) {
		return this.brandService.getByBrandId(brandId);
		
	}
	
	@GetMapping("/getallbrand")
	public DataResult<List<Brand>> getAll() {
		return this.brandService.getAll();
		
	}
	
	@PostMapping("/addbrand")
	public Result add( @Valid AddBrandRequest addBrandRequest) {
		return this.brandService.add(addBrandRequest);
	}
	
	@PostMapping("/updatebrand")
	public Result update(@RequestBody @Valid UpdateBrandRequest updateBrandRequest) {
		return this.brandService.update(updateBrandRequest);
	}
	
	@DeleteMapping("/deletebrand")
	public Result delete(   DeleteBrandRequest deleteBrandRequest) {
		return this.brandService.delete(deleteBrandRequest);
		
	}




}
