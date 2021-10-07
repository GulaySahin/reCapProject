package com.etiya.reCapProject.dataAccess.abstracts;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.reCapProject.entities.concretes.AdditionalService;

public interface AdditionalServiceDao extends JpaRepository<AdditionalService, Integer> {
	
	boolean existsAdditionalServiceByadditionalName(String additionalName);

	List<AdditionalService> getByRentAls_rentAlId(int rentalId);
	
}
