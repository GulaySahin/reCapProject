package com.etiya.reCapProject.entities.abstracts;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.etiya.reCapProject.core.entities.concretes.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)

public class ApplicationUser extends User{
	
	
	
	
}
