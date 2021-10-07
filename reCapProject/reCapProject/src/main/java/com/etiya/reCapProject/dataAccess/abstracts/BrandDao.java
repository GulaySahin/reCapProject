package com.etiya.reCapProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etiya.reCapProject.entities.concretes.Brand;

@Repository
public interface BrandDao extends JpaRepository<Brand, Integer>{
	
	List<Brand> getByBrandId(int brandId);
	
	boolean existsBrandByBrandName(String brandName);
	
	

}
