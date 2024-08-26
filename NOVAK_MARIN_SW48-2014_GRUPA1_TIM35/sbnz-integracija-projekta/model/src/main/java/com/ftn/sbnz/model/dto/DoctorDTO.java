package com.ftn.sbnz.model.dto;

import com.ftn.sbnz.model.models.Doctor;

public class DoctorDTO {

	private Long id;
	private String username;
	private String firstName;
	private String lastName;

	public DoctorDTO() {
		// Default constructor
	}

	public DoctorDTO(Long id, String username, String firstName, String lastName) {
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public DoctorDTO(Doctor doctor) {
		if (doctor.getId() != null)
			this.id = doctor.getId();
		if (doctor.getUsername() != null)
			this.username = doctor.getUsername();
		if (doctor.getFirstName() != null)
			this.firstName = doctor.getFirstName();
		if (doctor.getLastName() != null)
			this.lastName = doctor.getLastName();
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "DoctorDTO [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ "]";
	}
}
