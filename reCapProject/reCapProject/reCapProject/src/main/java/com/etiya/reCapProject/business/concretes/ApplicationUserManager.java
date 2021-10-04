package com.etiya.reCapProject.business.concretes;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.reCapProject.business.abstracts.ApplicationUserService;
import com.etiya.reCapProject.business.constants.Messages;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.reCapProject.dataAccess.abstracts.ApplicationUserDao;
import com.etiya.reCapProject.entities.abstracts.ApplicationUser;

@Service
public class ApplicationUserManager implements ApplicationUserService {
	
	private ApplicationUserDao applicationUserDao;
	
	@Autowired
	public ApplicationUserManager(ApplicationUserDao applicationUserDao) {
		super();
		this.applicationUserDao = applicationUserDao;
	}

	@Override
	public DataResult<List<ApplicationUser>> getAll() {
		return new SuccessDataResult<List<ApplicationUser>>(this.applicationUserDao.findAll(),Messages.List);
	}



	
	

}
