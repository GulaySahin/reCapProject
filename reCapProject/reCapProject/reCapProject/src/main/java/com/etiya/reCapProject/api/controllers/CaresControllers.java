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

import com.etiya.reCapProject.business.abstracts.CareService;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.entities.concretes.Care;
import com.etiya.reCapProject.entities.requests.careRequest.AddCareRequest;
import com.etiya.reCapProject.entities.requests.careRequest.DeleteCareRequest;
import com.etiya.reCapProject.entities.requests.careRequest.UpdateCareRequest;

@RestController
@RequestMapping("/api/cares")
public class CaresControllers {
	
	private CareService careService;
	
	@Autowired
	public CaresControllers(CareService careService) {
		super();
		this.careService = careService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<Care>> getAll() {
		return this.careService.getAll();
	}
	
	@PostMapping("/careadd")
	public Result add(@Valid @RequestBody   AddCareRequest addCareRequest) {
		return this.careService.add(addCareRequest);
	}
	
	@PostMapping("/careupdate")
	public Result update(@Valid @RequestBody   UpdateCareRequest updateCareRequest) {
		return this.careService.update(updateCareRequest);
	}
	
	@DeleteMapping("/caredelete")
	public Result delete(DeleteCareRequest deleteCareRequest) { 
		return this.careService.delete(deleteCareRequest);
	}



}
