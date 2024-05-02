package com.ftn.sbnz.model.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Doctor")
public class Doctor extends User {

	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Doctor(Long id, String username, String password, String firstName, String lastName) {
		super(id, username, password, firstName, lastName);
		// TODO Auto-generated constructor stub
	}

}
