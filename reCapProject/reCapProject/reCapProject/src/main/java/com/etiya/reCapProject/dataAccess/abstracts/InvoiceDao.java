package com.etiya.reCapProject.dataAccess.abstracts;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.reCapProject.entities.concretes.Invoice;


public interface InvoiceDao extends JpaRepository<Invoice, Integer>{
	
	List<Invoice> getByCustomer_id(int customerId);
	
	List<Invoice> getAllByInvoiceDateLessThanEqualAndInvoiceDateGreaterThanEqual(Date startDate,Date endDate );}
