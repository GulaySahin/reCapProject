package com.etiya.reCapProject.api.controllers;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.reCapProject.business.abstracts.InvoiceService;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.entities.concretes.Invoice;
import com.etiya.reCapProject.entities.requests.invoiceRequest.AddInvoiceRequest;
import com.etiya.reCapProject.entities.requests.invoiceRequest.DeleteInvoiceRequest;
import com.etiya.reCapProject.entities.requests.invoiceRequest.UpdateInvoiceRequest;

@RestController
@RequestMapping("/api/invoices")
public class InvoicesControllers {
	
	private InvoiceService invoiceService;
	
	@Autowired
	public InvoicesControllers(InvoiceService invoiceService) {
		super();
		this.invoiceService = invoiceService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<Invoice>> getAll() {
		return this.invoiceService.getAll();
	}
	
	@GetMapping("/getbycustomerid")
	public DataResult<List<Invoice>> getByCustomerId(int customerId) {
		return this.invoiceService.getByCustomerId(customerId);
	}
	
	@GetMapping("/getinvoicesbetweendates")
	public DataResult<List<Invoice>> getInvoicesBetweenTwoDate(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
		
		System.out.println(startDate.toString()+endDate.toString());
		return this.invoiceService.getInvoicesBetweenTwoDate(endDate,startDate);
	}


	
	@PostMapping("/add")
	public Result add( @Valid @RequestBody AddInvoiceRequest addInvoiceRequest) {
		return this.invoiceService.add(addInvoiceRequest);
	}
	
	@PostMapping("/update")
	public Result update( @Valid @RequestBody  UpdateInvoiceRequest updateInvoiceRequest) {
		return this.invoiceService.update(updateInvoiceRequest);
	}
	
	@DeleteMapping("/delete")
	public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) {
		return this.invoiceService.delete(deleteInvoiceRequest);
	}

	
	
	
}
