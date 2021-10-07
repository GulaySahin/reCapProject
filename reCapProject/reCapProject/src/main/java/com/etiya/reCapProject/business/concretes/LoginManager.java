package com.etiya.reCapProject.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.reCapProject.core.utilities.businnes.BusinnesRules;
import com.etiya.reCapProject.core.utilities.results.ErrorResult;

import com.etiya.reCapProject.business.abstracts.LoginService;
import com.etiya.reCapProject.business.constants.messages.UserMessages;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.core.utilities.results.SuccessResult;
import com.etiya.reCapProject.dataAccess.abstracts.ApplicationUserDao;
import com.etiya.reCapProject.entities.dtos.UserLoginDto;

@Service
public class LoginManager implements LoginService{
	
	ApplicationUserDao applicationUserDao;
	
	@Autowired
	public LoginManager(ApplicationUserDao applicationUserDao) {
		super();
		this.applicationUserDao = applicationUserDao;
	}

	@Override
	public Result logIn(UserLoginDto userLoginDto) {
		var result = BusinnesRules.run(checkEmailAndPassword(userLoginDto));

		if (result != null) {
			return result;
		}

		return new SuccessResult(UserMessages.SuccessLogin);
	}
	
	
	
	// Giriş yaparken email ve şifre databasede var mı
	private Result checkEmailAndPassword(UserLoginDto userLoginDto) {
		if(!this.applicationUserDao.existsByeMail(userLoginDto.getEMail())) {
			return new ErrorResult(UserMessages.ErrorMailIsAlreadyExist);
		}
		
		if (!this.applicationUserDao.getByeMail(userLoginDto.getEMail()).getPassword().equals(userLoginDto.getPassword())) {
				return new ErrorResult(UserMessages.ErrorMailIsAlreadyExist);
		}
		
		return new SuccessResult();
	}

	 

}
