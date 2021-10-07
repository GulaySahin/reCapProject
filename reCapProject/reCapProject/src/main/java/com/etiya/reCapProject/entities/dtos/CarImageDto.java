package com.etiya.reCapProject.entities.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarImageDto {
	
	private String imagePath;
	
	@JsonFormat(pattern = "yyyy-MM--dd")
	private Date date;

}
