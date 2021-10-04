package com.etiya.reCapProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etiya.reCapProject.entities.abstracts.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {
	
	boolean existsCustomerByeMail(String eMail);
	
}
