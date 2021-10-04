package com.etiya.reCapProject.dataAccess.abstracts;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etiya.reCapProject.entities.concretes.Care;

@Repository
public interface CareDao  extends JpaRepository<Care, Integer>{
	
	List<Care> getByCar_CarId(int carId);
	

	

}
