package com.etiya.reCapProject.dataAccess.abstracts;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etiya.reCapProject.entities.concretes.CarImages;

@Repository
public interface CarImageDao extends JpaRepository<CarImages, Integer>{
	
	int countByCar_CarId(int carId);
	
	List<CarImages> getByCar_CarId(int carId);
	
	
	boolean existsByCar_CarId(int carId);


	
}
