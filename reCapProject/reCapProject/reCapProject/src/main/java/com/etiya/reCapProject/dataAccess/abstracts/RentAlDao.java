package com.etiya.reCapProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etiya.reCapProject.entities.concretes.RentAl;

@Repository
public interface RentAlDao extends JpaRepository<RentAl, Integer>{
	
	List<RentAl> getByCar_CarId(int carId);
	
	RentAl getByRentAlId(int id);
	
}
