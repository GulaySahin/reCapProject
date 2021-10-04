package com.etiya.reCapProject.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.reCapProject.business.abstracts.LoginService;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.entities.dtos.UserLoginDto;

@RestController
@RequestMapping("/api/login")
public class LoginControllers {
	
	private LoginService loginService;
	
	@Autowired
	public LoginControllers(LoginService loginService) {
		super();
		this.loginService = loginService;
	}
	
	@PostMapping("/login")
	public Result logIn(UserLoginDto userLoginDto) {
		return this.loginService.logIn(userLoginDto);
	}
	

}
