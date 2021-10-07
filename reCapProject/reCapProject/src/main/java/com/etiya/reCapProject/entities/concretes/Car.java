package com.etiya.reCapProject.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cars")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","cares"})

public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int carId;
	
	/*
	 * @Column(name="brand_id") private int brandId;
	 * 
	 * @Column(name="color_id") private int colorId;
	 */
	
	@Column(name="car_name")
	private String carName;
	

	
	@Column(name="model_year")
	private int modelYear;
	
	@Column(name="daily_price")
	private double dailyPrice;
	
	@Column(name="description")
	private String description;
	
	@Column(name="kilometer")
	private int kilometer;
	
	@Column(name="city")
	private String city;
	
	@Column(name="findex_point")
	private int findexPoint;
	
	@Column(name="iscar_care", columnDefinition = "boolean default true")
	private boolean isCarCare;
	
	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name="color_id")
	private Color color;
	
	@JsonIgnore
	@OneToMany(mappedBy = "car")
	private List<RentAl> rentAls;
	
	@JsonIgnore
	@OneToMany(mappedBy="car")
	private List<CarImages> carImages;
	
	@JsonIgnore
	@OneToMany(mappedBy = "car")
	private List<CarDamage> carDamages;
	
	@JsonIgnore
	@OneToMany(mappedBy = "car")
	private List<Care> cares;
}
