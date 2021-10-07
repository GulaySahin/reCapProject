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

import com.etiya.reCapProject.business.abstracts.CorporateCustomerService;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.entities.dtos.CorporateCustomerDto;
import com.etiya.reCapProject.entities.requests.corporateRequest.AddCorporateCustomerRequest;
import com.etiya.reCapProject.entities.requests.corporateRequest.DeleteCorporateCustomerRequest;
import com.etiya.reCapProject.entities.requests.corporateRequest.UpdateCorporateCustomerRequest;

@RestController
@RequestMapping("api/corporatecustomers")
public class CorporateCustomersControllers {
	
	private CorporateCustomerService corporateCustomerService;
	
	@Autowired
	public CorporateCustomersControllers(CorporateCustomerService corporateCustomerService) {
		super();
		this.corporateCustomerService = corporateCustomerService;
	}
	
	@PostMapping("/add")
	public Result add(@Valid @RequestBody  AddCorporateCustomerRequest addCorporateCustomerRequest) {
		
		return this.corporateCustomerService.add(addCorporateCustomerRequest);
	}
	
	@PostMapping("/update")
	public Result update(@Valid @RequestBody  UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		
		return this.corporateCustomerService.update(updateCorporateCustomerRequest);
		
	}
	
	@DeleteMapping("/delete")
	public Result delete( DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
		return this.corporateCustomerService.delete(deleteCorporateCustomerRequest);
		
	}
	
	@GetMapping("/getall")
	public DataResult<List<CorporateCustomerDto>> getAll() {
		return this.corporateCustomerService.getAll();
	}
	
	@GetMapping("/getbyid")
	public DataResult<CorporateCustomerDto> getById(int id) {
		return this.corporateCustomerService.getById(id);
	}

}
