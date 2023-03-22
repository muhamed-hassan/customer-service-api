package com.poc.interfaces.rest.models;

public class UserInfoCreateModel extends UserInfoModel {

	private String name;
	
	private String dateOfBirth; // dd-MM-yyyy

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}	
	
}
