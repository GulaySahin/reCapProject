package com.etiya.reCapProject.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.etiya.reCapProject.entities.abstracts.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="corporate_customers")
public class CorporateCustomer extends Customer{
	
	@Column(name="tax_number")
	private String taxNumber;
	
	@Column(name="company_name")
	private String companyName;
}
