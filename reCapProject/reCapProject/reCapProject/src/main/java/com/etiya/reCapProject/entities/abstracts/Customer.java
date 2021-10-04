package com.etiya.reCapProject.entities.abstracts;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.etiya.reCapProject.entities.concretes.CreditCard;
import com.etiya.reCapProject.entities.concretes.Invoice;
import com.etiya.reCapProject.entities.concretes.RentAl;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="customers")
public class Customer extends ApplicationUser{
	


	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<CreditCard> creditCards;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customers")
	private List<RentAl> rentAls;
	
	@OneToMany(mappedBy = "customer")
	private List<Invoice> invoices;
}
