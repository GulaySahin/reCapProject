package com.etiya.reCapProject.business.concretes;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.reCapProject.business.abstracts.AdditionalServicesService;
import com.etiya.reCapProject.business.abstracts.CarService;
import com.etiya.reCapProject.business.abstracts.InvoiceService;
import com.etiya.reCapProject.business.constants.messages.InvoiceMessages;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.reCapProject.core.utilities.results.SuccessResult;
import com.etiya.reCapProject.dataAccess.abstracts.AdditionalServiceDao;
import com.etiya.reCapProject.dataAccess.abstracts.CustomerDao;
import com.etiya.reCapProject.dataAccess.abstracts.InvoiceDao;
import com.etiya.reCapProject.dataAccess.abstracts.RentAlDao;
import com.etiya.reCapProject.entities.abstracts.Customer;
import com.etiya.reCapProject.entities.concretes.Invoice;
import com.etiya.reCapProject.entities.concretes.RentAl;
import com.etiya.reCapProject.entities.dtos.InvoiceDetailDto;
import com.etiya.reCapProject.entities.requests.invoiceRequest.AddInvoiceRequest;
import com.etiya.reCapProject.entities.requests.invoiceRequest.DeleteInvoiceRequest;
import com.etiya.reCapProject.entities.requests.invoiceRequest.UpdateInvoiceRequest;


@Service
public class InvoiceManager implements InvoiceService{
	
	private InvoiceDao invoiceDao;
	private RentAlDao rentAlDao;
	private CustomerDao customerDao;
	private ModelMapper modelMapper;
	private CarService carService;
	private AdditionalServicesService additionalServicesService;
	private AdditionalServiceDao additionalServiceDao;
	
	@Autowired
	public InvoiceManager(InvoiceDao invoiceDao, RentAlDao rentAlDao,CustomerDao customerDao,
			ModelMapper modelMapper,
			AdditionalServicesService additionalServicesService,
			AdditionalServiceDao additionalServiceDao,
			CarService carService) {
		super();
		this.invoiceDao = invoiceDao;
		this.customerDao=customerDao;
		this.rentAlDao=rentAlDao;
		this.modelMapper=modelMapper;
		this.additionalServicesService=additionalServicesService;
		this.additionalServiceDao=additionalServiceDao;
		this.carService=carService;
	}

	@Override
	public DataResult<List<InvoiceDetailDto>> getAll() {
		
		List<Invoice> invoices= this.invoiceDao.findAll();
		
		List<InvoiceDetailDto> invoiceDetailDtos= new ArrayList<InvoiceDetailDto>();
		
		for (Invoice invoic : invoices) {
			
			InvoiceDetailDto invoiceDetailDto=modelMapper.map(invoic, InvoiceDetailDto.class);
			invoiceDetailDto.setAdditionalServiceDetailDtos(this.additionalServicesService.getByRentalId(invoic.getRentAls().getRentAlId()).getData());
			
			invoiceDetailDtos.add(invoiceDetailDto);
			
		}
		return new SuccessDataResult<List<InvoiceDetailDto>>(invoiceDetailDtos,InvoiceMessages.GetAll);
	}


	@Override
	public DataResult<List<Invoice>> getInvoicesBetweenTwoDate(Date startDate, Date endDate ) {
		return new SuccessDataResult<List<Invoice>>(this.invoiceDao.getAllByInvoiceDateLessThanEqualAndInvoiceDateGreaterThanEqual
				(endDate, startDate),InvoiceMessages.DateList);
	}

	@Override
	public Result add(AddInvoiceRequest addInvoiceRequest) {
		
		long totalRentDay= (ChronoUnit.DAYS.between(
						this.rentAlDao.getById(addInvoiceRequest.getRentAlId()).getRentDate().toInstant(),
						this.rentAlDao.getById(addInvoiceRequest.getRentAlId()).getReturnDate().toInstant()));
			

		
		Customer customer = new Customer();
		customer.setId(addInvoiceRequest.getCustomerId());
		
		RentAl rentAl = this.rentAlDao.getById(addInvoiceRequest.getRentAlId());
		rentAl.setRentAlId(addInvoiceRequest.getRentAlId());
		
		Invoice invoice= new Invoice();
		invoice.setInvoiceNumber(addInvoiceRequest.getInvoiceNumber());
		invoice.setInvoiceDate(addInvoiceRequest.getInvoiceDate());
		invoice.setRentalAmount(this.rentAlDao.getById(addInvoiceRequest.getRentAlId()).getAmount());
		
		invoice.setRentDate(this.rentAlDao.getById(addInvoiceRequest.getRentAlId()).getRentDate());
		invoice.setReturnDate(this.rentAlDao.getById(addInvoiceRequest.getRentAlId()).getReturnDate());

		
		invoice.setTotalRentalDay(totalRentDay);
		invoice.setCustomer(customer);
		invoice.setRentAls(rentAl);

		this.invoiceDao.save(invoice);
		return new SuccessResult(InvoiceMessages.Add);
	}

	@Override
	public Result update(UpdateInvoiceRequest updateInvoiceRequest) {
		
		long totalRentDay= (ChronoUnit.DAYS.between(
				this.rentAlDao.getById(updateInvoiceRequest.getRentAlId()).getRentDate().toInstant(),
				this.rentAlDao.getById(updateInvoiceRequest.getRentAlId()).getReturnDate().toInstant()));
		
		
		Customer customer = new Customer();
		customer.setId(updateInvoiceRequest.getCustomerId());
		
		RentAl rentAl = new RentAl();
		rentAl.setRentAlId(updateInvoiceRequest.getRentAlId());
		
		Invoice invoice= this.invoiceDao.getById(updateInvoiceRequest.getInvoiceId());
		invoice.setInvoiceNumber(updateInvoiceRequest.getInvoiceNumber());
		invoice.setInvoiceDate(updateInvoiceRequest.getInvoiceDate());
		invoice.setRentalAmount(totalRentDay*this.rentAlDao.getById(updateInvoiceRequest.getRentAlId()).getCar().getDailyPrice());

		
		invoice.setRentDate(this.rentAlDao.getById(updateInvoiceRequest.getRentAlId()).getRentDate());
		invoice.setReturnDate(this.rentAlDao.getById(updateInvoiceRequest.getRentAlId()).getReturnDate());

		invoice.setTotalRentalDay(totalRentDay);

		invoice.setCustomer(customer);
		invoice.setRentAls(rentAl);

		this.invoiceDao.save(invoice);
		return new SuccessResult(InvoiceMessages.Update);
	}

	@Override
	public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) {
		Invoice invoice= this.invoiceDao.getById(deleteInvoiceRequest.getInvoiceId());
		
		this.invoiceDao.delete(invoice);
		return new SuccessResult(InvoiceMessages.Delete);
	}

	

	
}
