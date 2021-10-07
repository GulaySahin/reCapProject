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

import com.etiya.reCapProject.business.abstracts.IndividualCustomerService;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.entities.dtos.IndividualCustomerDto;
import com.etiya.reCapProject.entities.requests.individualCustomerRequest.AddIndividualCustomerRequest;
import com.etiya.reCapProject.entities.requests.individualCustomerRequest.DeleteIndividualCustomerRequest;
import com.etiya.reCapProject.entities.requests.individualCustomerRequest.UpdateIndividualCustomerRequest;

@RestController
@RequestMapping("/api/individualcustomers")
public class IndividualCustomersControllers {
	
	private IndividualCustomerService individualCustomerService;
	
	@Autowired
	public IndividualCustomersControllers(IndividualCustomerService individualCustomerService) {
		super();
		this.individualCustomerService = individualCustomerService;
	}
	
	@PostMapping("/add")
	public Result add(@Valid @RequestBody AddIndividualCustomerRequest addIndividualCustomerRequest) {
		return this.individualCustomerService.add(addIndividualCustomerRequest);
	}
	
	@PostMapping("/update")
	public Result update(@Valid @RequestBody UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		return this.individualCustomerService.update(updateIndividualCustomerRequest);
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestBody DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
		return this.individualCustomerService.delete(deleteIndividualCustomerRequest);
	}
	
	@GetMapping("/getall")
	public DataResult<List<IndividualCustomerDto>> getAll() {
		return this.individualCustomerService.getAll();
	}
	
	@GetMapping("/getbyid")
	public DataResult<IndividualCustomerDto> getById(int id) {
		return this.individualCustomerService.getById(id);
	}

	
}
