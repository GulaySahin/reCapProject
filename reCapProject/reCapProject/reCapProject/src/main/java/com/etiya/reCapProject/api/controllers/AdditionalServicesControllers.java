package com.etiya.reCapProject.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.reCapProject.business.abstracts.AdditionalServicesService;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.entities.concretes.AdditionalService;
import com.etiya.reCapProject.entities.requests.additionalRequest.AddAdditionalServiceRequest;
import com.etiya.reCapProject.entities.requests.additionalRequest.DeleteAdditionalServiceRequest;
import com.etiya.reCapProject.entities.requests.additionalRequest.UpdateAdditionalServiceRequest;


@RestController
@RequestMapping("/api/additionalservices")
public class AdditionalServicesControllers {
	
	private AdditionalServicesService additionalServicesService;
	
	@Autowired
	public AdditionalServicesControllers(AdditionalServicesService additionalServicesService) {
		super();
		this.additionalServicesService = additionalServicesService;
	}
	
	
	@GetMapping("/getall")
	public DataResult<List<AdditionalService>> getAll() {
		return this.additionalServicesService.getAll();
	}
	
	@GetMapping("/getbyıd")
	public DataResult<AdditionalService> getById(int id) {
		return this.additionalServicesService.getById(id);
	}
	
	@PostMapping("/add")
	public Result add(@Valid AddAdditionalServiceRequest addAdditionalServiceRequest) {
		return this.additionalServicesService.add(addAdditionalServiceRequest);
	}
	
	@PostMapping("/update")
	public Result update(@Valid  UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
		return this.additionalServicesService.update(updateAdditionalServiceRequest);
	}
	
	@DeleteMapping("/delete")
	public Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) {
		return this.additionalServicesService.delete(deleteAdditionalServiceRequest);
	}


	

}
