package com.etiya.reCapProject.business.concretes;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.reCapProject.business.abstracts.InvoiceService;
import com.etiya.reCapProject.business.constants.Messages;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.reCapProject.core.utilities.results.SuccessResult;
import com.etiya.reCapProject.dataAccess.abstracts.CustomerDao;
import com.etiya.reCapProject.dataAccess.abstracts.InvoiceDao;
import com.etiya.reCapProject.dataAccess.abstracts.RentAlDao;
import com.etiya.reCapProject.entities.abstracts.Customer;
import com.etiya.reCapProject.entities.concretes.Invoice;
import com.etiya.reCapProject.entities.concretes.RentAl;
import com.etiya.reCapProject.entities.requests.invoiceRequest.AddInvoiceRequest;
import com.etiya.reCapProject.entities.requests.invoiceRequest.DeleteInvoiceRequest;
import com.etiya.reCapProject.entities.requests.invoiceRequest.UpdateInvoiceRequest;


@Service
public class InvoiceManager implements InvoiceService{
	
	private InvoiceDao invoiceDao;
	private RentAlDao rentAlDao;
	@SuppressWarnings("unused")
	private CustomerDao customerDao;
	
	@Autowired
	public InvoiceManager(InvoiceDao invoiceDao, RentAlDao rentAlDao,CustomerDao customerDao) {
		super();
		this.invoiceDao = invoiceDao;
		this.customerDao=customerDao;
		this.rentAlDao=rentAlDao;
	}

	@Override
	public DataResult<List<Invoice>> getAll() {
		return new SuccessDataResult<List<Invoice>>(this.invoiceDao.findAll(),Messages.List);
	}
	
	@Override
	public DataResult<List<Invoice>> getByCustomerId(int customerId) {
		return new SuccessDataResult<List<Invoice>>(this.invoiceDao.getByCustomer_id(customerId),Messages.Listed);
	}

	@Override
	public DataResult<List<Invoice>> getInvoicesBetweenTwoDate(Date startDate, Date endDate ) {
		return new SuccessDataResult<List<Invoice>>(this.invoiceDao.getAllByInvoiceDateLessThanEqualAndInvoiceDateGreaterThanEqual
				(endDate, startDate),Messages.List);
	}

	@Override
	public Result add(AddInvoiceRequest addInvoiceRequest) {
		


		
		Customer customer = new Customer();
		customer.setId(addInvoiceRequest.getCustomerId());
		
		RentAl rentAl = new RentAl();
		rentAl.setRentAlId(addInvoiceRequest.getRentAlId());
		
		Invoice invoice= new Invoice();
		invoice.setInvoiceNumber(addInvoiceRequest.getInvoiceNumber());
		invoice.setInvoiceDate(addInvoiceRequest.getInvoiceDate());
		invoice.setRentalAmount(addInvoiceRequest.getRentalAmount());
		
		invoice.setRentDate(this.rentAlDao.getById(addInvoiceRequest.getRentAlId()).getRentDate());
		invoice.setReturnDate(this.rentAlDao.getById(addInvoiceRequest.getRentAlId()).getReturnDate());

		invoice.setTotalRentalDay(ChronoUnit.DAYS.between(
				this.rentAlDao.getById(addInvoiceRequest.getRentAlId()).getRentDate().toInstant(),
				this.rentAlDao.getById(addInvoiceRequest.getRentAlId()).getReturnDate().toInstant()));
			
		

		invoice.setCustomer(customer);
		invoice.setRentAl(rentAl);

		this.invoiceDao.save(invoice);
		return new SuccessResult(Messages.Add);
	}

	@Override
	public Result update(UpdateInvoiceRequest updateInvoiceRequest) {
		Customer customer = new Customer();
		customer.setId(updateInvoiceRequest.getCustomerId());
		
		RentAl rentAl = new RentAl();
		rentAl.setRentAlId(updateInvoiceRequest.getRentAlId());
		
		Invoice invoice= this.invoiceDao.getById(updateInvoiceRequest.getInvoiceId());
		invoice.setInvoiceNumber(updateInvoiceRequest.getInvoiceNumber());
		invoice.setInvoiceDate(updateInvoiceRequest.getInvoiceDate());
		invoice.setRentalAmount(updateInvoiceRequest.getRentalAmount());
		
		invoice.setRentDate(this.rentAlDao.getById(updateInvoiceRequest.getRentAlId()).getRentDate());
		invoice.setReturnDate(this.rentAlDao.getById(updateInvoiceRequest.getRentAlId()).getReturnDate());

		invoice.setTotalRentalDay(ChronoUnit.DAYS.between(
				this.rentAlDao.getById(updateInvoiceRequest.getRentAlId()).getRentDate().toInstant(),
				this.rentAlDao.getById(updateInvoiceRequest.getRentAlId()).getReturnDate().toInstant()));
		

		invoice.setCustomer(customer);
		invoice.setRentAl(rentAl);

		this.invoiceDao.save(invoice);
		return new SuccessResult(Messages.Update);
	}

	@Override
	public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) {
		Invoice invoice= this.invoiceDao.getById(deleteInvoiceRequest.getInvoiceId());
		
		this.invoiceDao.delete(invoice);
		return new SuccessResult(Messages.Delete);
	}

	
}
