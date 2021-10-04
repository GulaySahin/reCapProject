package com.etiya.reCapProject.entities.concretes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.etiya.reCapProject.entities.abstracts.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="invoices")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","customer"})

public class Invoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="invoice_id")
	private int invoiceId;
	
	@Column(name="invoice_number")
	private int invoiceNumber;
	
	@Column(name="rental_date")
	private Date rentDate;
	
	@Column(name="returnrental_date")
	private Date returnDate;
	
	@Column(name="invoice_date")
	private Date invoiceDate;
	
	@Column(name="totalrental_day")
	private Long totalRentalDay;
	
	@Column(name="rental_amount")
	private Double rentalAmount;
	
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="rental_id")
	private RentAl rentAl;
	
	
	
	
}
