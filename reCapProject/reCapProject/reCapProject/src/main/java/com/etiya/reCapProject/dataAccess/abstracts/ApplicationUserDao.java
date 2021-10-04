package com.etiya.reCapProject.dataAccess.abstracts;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etiya.reCapProject.entities.abstracts.ApplicationUser;

@Repository
public interface ApplicationUserDao extends JpaRepository<ApplicationUser, Integer> {
	
	boolean existsByeMail(String eMail);
	
	ApplicationUser getByeMail(String eMail);
}
