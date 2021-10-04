package com.etiya.reCapProject.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.reCapProject.business.abstracts.ColorService;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.entities.concretes.Color;
import com.etiya.reCapProject.entities.requests.colorRequest.AddColorRequest;
import com.etiya.reCapProject.entities.requests.colorRequest.DeleteColorRequest;
import com.etiya.reCapProject.entities.requests.colorRequest.UpdateColorRequest;

@RestController
@RequestMapping("/api/colors")
public class ColorsControllers {
	
	private ColorService colorService;
	
	@Autowired
	public ColorsControllers(ColorService colorService) {
		super();
		this.colorService = colorService;
	}
	
	@GetMapping("/getcolorıd")
	public DataResult<List<Color>> getByColorId(int colorId) {
		return this.colorService.getByColorId(colorId);
	}
	
	@GetMapping("/getallcolor")
	public DataResult<List<Color>> getAll() {
		return this.colorService.getAll();
	}
	
	@PostMapping("/addcolor")
	public Result add(@RequestBody @Valid AddColorRequest addColorRequest) {
		return this.colorService.add(addColorRequest);
	}
	
	@PostMapping("/updatecolor")
	public Result update(@RequestBody @Valid  UpdateColorRequest updateColorRequest) {
		return this.colorService.update(updateColorRequest);
	}
	
	
	@DeleteMapping("/deletecolor")
	public Result delete(@Valid  DeleteColorRequest deleteColorRequest)  {
		return this.colorService.delete(deleteColorRequest);
	}


}
