package com.etiya.reCapProject.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cares")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})

public class Care {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="care_id")
	private int careId;
	
	@Column(name="location")
	private String location;	
	
	
	@Column(name="start_date")
	private String startDate;
	
	@Column(name="finish_date")
	private String finishDate;
	
	
	@ManyToOne
	@JoinColumn(name="car_id")
	private Car car;
}
