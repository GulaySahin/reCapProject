package com.etiya.reCapProject.business.abstracts;

import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.entities.dtos.UserLoginDto;

public interface LoginService {
	
	Result logIn(UserLoginDto userLoginDto);
}
