package com.etiya.reCapProject.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.reCapProject.business.abstracts.ApplicationUserService;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.entities.abstracts.ApplicationUser;

@RestController
@RequestMapping("/api/users")
public class ApplicationUsersControllers {
	
	private ApplicationUserService applicationUserService;
	
	@Autowired
	public ApplicationUsersControllers(ApplicationUserService applicationUserService) {
		super();
		this.applicationUserService = applicationUserService;
	}
	
	
	@GetMapping("/getall")
	public DataResult<List<ApplicationUser>> getAll() {
		return this.applicationUserService.getAll();
	}

}
