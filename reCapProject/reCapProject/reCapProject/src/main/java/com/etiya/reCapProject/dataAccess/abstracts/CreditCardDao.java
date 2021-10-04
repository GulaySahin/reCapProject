package com.etiya.reCapProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etiya.reCapProject.entities.concretes.CreditCard;

@Repository
public interface CreditCardDao extends JpaRepository<CreditCard, Integer> {
	

	List<CreditCard> getCreditCardByCustomer_Id(int customerId);
}
