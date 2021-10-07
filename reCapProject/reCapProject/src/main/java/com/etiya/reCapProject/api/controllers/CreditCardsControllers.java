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

import com.etiya.reCapProject.business.abstracts.CreditCardService;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.entities.concretes.CreditCard;
import com.etiya.reCapProject.entities.dtos.GetCreditCardDetailDto;
import com.etiya.reCapProject.entities.requests.creditCardRequest.AddCreditCardRequest;
import com.etiya.reCapProject.entities.requests.creditCardRequest.DeleteCreditCardRequest;
import com.etiya.reCapProject.entities.requests.creditCardRequest.UpdateCreditCardRequest;

@RestController
@RequestMapping("/api/creditcards")
public class CreditCardsControllers {
	
	private CreditCardService creditCardService;
	
	@Autowired
	public CreditCardsControllers(CreditCardService creditCardService) {
		super();
		this.creditCardService = creditCardService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<GetCreditCardDetailDto>> getAll() {
		return this.creditCardService.getAll();
	}
	
	@GetMapping("/getcreditcardbyıd")
	public DataResult<GetCreditCardDetailDto> getById(int id) {
		return this.creditCardService.getById(id);
	}
	
	@PostMapping("/addcreditcard")
	public Result add(@Valid @RequestBody  AddCreditCardRequest addCreditCardRequest) {
		return this.creditCardService.add(addCreditCardRequest);
	}
	
	@PostMapping("/updatecreditcard")
	public Result update( @Valid @RequestBody UpdateCreditCardRequest updateCreditCardRequest) {
		return this.creditCardService.update(updateCreditCardRequest);
	}
	
	@DeleteMapping("/deletecreditcard")
	public Result delete(DeleteCreditCardRequest deleteCreditCardRequest) {
		return this.creditCardService.delete(deleteCreditCardRequest);
	}
	
	@GetMapping("/getcreditcardbycustomerıd")
	public DataResult<List<CreditCard>> getCreditCardByCustomer_Id(int customerId) {
		return this.creditCardService.getCreditCardByCustomer_Id(customerId);
	}


}
