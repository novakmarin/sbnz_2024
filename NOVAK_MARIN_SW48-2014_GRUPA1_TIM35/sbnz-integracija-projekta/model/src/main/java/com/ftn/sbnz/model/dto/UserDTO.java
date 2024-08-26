package com.ftn.sbnz.model.dto;

import com.ftn.sbnz.model.models.User;

public class UserDTO {
	private Long id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;

	public UserDTO() {
		super();
	}

	public UserDTO(Long id, String username, String password, String firstName, String lastName) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public UserDTO(User user) {
		if (user.getId() != null)
			this.id = user.getId();
		if (user.getUsername() != null)
			this.username = user.getUsername();
		if (user.getPassword() != null)
			this.password = user.getPassword();
		if (user.getFirstName() != null)
			this.firstName = user.getFirstName();
		if (user.getLastName() != null)
			this.lastName = user.getLastName();
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstName;
	}

	public void setFirstname(String firstName) {
		this.firstName = firstName;
	}

	public String getLastname() {
		return lastName;
	}

	public void setLastname(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "UserDTO{" + "id=" + id + ", username='" + username + '\'' + ", password='" + password + '\''
				+ ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + '}';
	}
}
