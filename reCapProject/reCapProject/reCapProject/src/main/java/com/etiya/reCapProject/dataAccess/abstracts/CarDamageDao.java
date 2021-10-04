package com.etiya.reCapProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.reCapProject.entities.concretes.CarDamage;

public interface CarDamageDao extends JpaRepository<CarDamage, Integer> {

}
