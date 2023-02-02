package com.example.userservice.dto;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.example.userservice.beans.User;

@XmlType(name = "", propOrder = {
	    "name",
	    "surname",
	    "email",
	    "username",
	    "identificationNumber",
	    "role"
	})
@XmlRootElement(name = "user")
public class UserDTO {
	
	private String username;
	private String name;
	private String surname;
	private String email;
	private String identificationNumber;
	private String role;
	
	public UserDTO() {
		
	}
	
	public UserDTO(User u) {
		this.name = u.getName();
		this.surname = u.getSurname();
		this.email = u.getEmail();
		this.username = u.getUsername();
		this.identificationNumber = u.getIdentificationNumber();
		this.role = u.getRoles().get(0).toString().toLowerCase();
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdentificationNumber() {
		return identificationNumber;
	}
	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
