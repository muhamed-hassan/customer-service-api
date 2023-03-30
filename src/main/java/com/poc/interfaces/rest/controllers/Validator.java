package com.poc.interfaces.rest.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.poc.interfaces.rest.models.UserInfoCreateModel;
import com.poc.interfaces.rest.models.UserInfoUpdateModel;

//https://en.wikipedia.org/wiki/Fail-fast approach is used to report validation errors
@Component
public class Validator {
	
	public String validate(UserInfoCreateModel userInfoCreateModel) throws ParseException {
		
		String name = userInfoCreateModel.getName();
		if (name == null) {
			return "name is required";
		}
		name = name.trim();
		if (name.length() == 0) {
			return "name is required";
		}		
		if (name.length() > 250) {
			return "name length exceeded 250 characters";
		}		
		if (!name.matches("[a-zA-Z]*")) {
			return "name contains invalid characters and shall contain letters only";
		}
		
		String nationalId = userInfoCreateModel.getNationalId();
		if (nationalId == null) {
			return "nationalId is required";
		}
		nationalId = nationalId.trim();
		if (nationalId.length() == 0) {
			return "nationalId is required";
		}		
		if (nationalId.length() > 14) {
			return "nationalId length exceeded 14 characters";
		}		
		if (!nationalId.matches("[0-9]*")) {
			return "nationalId contains invalid characters and shall contain digits only";
		}
		
		String dateOfBirthStr = userInfoCreateModel.getDateOfBirth();
		if (dateOfBirthStr == null) {
			return "dateOfBirth is required";
		}
		DateFormat dateFormat = new SimpleDateFormat();		
		Date dateOfBirth = dateFormat.parse(dateOfBirthStr);
		if (dateOfBirth == null) {
			return "dateOfBirth is wrongly formatted and shall be formatted as dd-MM-yyyy";
		}
		
		String cellPhone = userInfoCreateModel.getCellPhone();
		if (cellPhone == null) {
			return "cellPhone is required";
		}
		cellPhone = cellPhone.trim();
		if (cellPhone.length() == 0) {
			return "cellPhone is required";
		}
		if (!cellPhone.matches("(07)[0-9]{9}")) {
			return "cellPhone contains invalid characters and shall be in this form 071234567";
		}
		
		String email = userInfoCreateModel.getEmail();
		if (email == null) {
			return "email is required";
		}
		email = email.trim();
		if (email.length() == 0) {
			return "email is required";
		}
		if (!email.matches("[a-zA-Z0-9_\\.]+(\\@)[a-zA-Z0-9]+(\\.)[a-zA-Z]+")) {
			return "email format is invalid";
		}
		
		String city = userInfoCreateModel.getCity();
		if (city == null) {
			return "city is required";
		}
		city = city.trim();
		if (city.length() == 0) {
			return "city is required";
		}		
		if (city.length() > 50) {
			return "city length exceeded 50 characters";
		}		
		
		String region = userInfoCreateModel.getRegion();
		if (region == null) {
			return "region is required";
		}
		region = region.trim();
		if (region.length() == 0) {
			return "region is required";
		}		
		if (region.length() > 50) {
			return "region length exceeded 50 characters";
		}		
		
		String buildingNumber = userInfoCreateModel.getBuildingNumber();
		if (buildingNumber == null) {
			return "buildingNumber is required";
		}
		buildingNumber = buildingNumber.trim();
		if (buildingNumber.length() == 0) {
			return "buildingNumber is required";
		}		
		if (buildingNumber.length() > 50) {
			return "buildingNumber length exceeded 50 characters";
		}		
		
		String postalCode = userInfoCreateModel.getPostalCode();
		if (postalCode == null) {
			return "postalCode is required";
		}
		postalCode = postalCode.trim();
		if (postalCode.length() == 0) {
			return "postalCode is required";
		}		
		if (postalCode.length() > 5) {
			return "postalCode length exceeded 5 characters";
		}		
		if (!postalCode.matches("[0-9]*")) {
			return "postalCode contains invalid characters and shall contain digits only";
		}
		
		return null;
	}
	
	public String validate(UserInfoUpdateModel userInfoUpdateModel) {
		
		String nationalId = userInfoUpdateModel.getNationalId();
		if (nationalId == null) {
			return "nationalId is required";
		}
		nationalId = nationalId.trim();
		if (nationalId.length() == 0) {
			return "nationalId is required";
		}		
		if (nationalId.length() > 14) {
			return "nationalId length exceeded 14 characters";
		}		
		if (!nationalId.matches("[0-9]*")) {
			return "nationalId contains invalid characters and shall contain digits only";
		}
		
		String cellPhone = userInfoUpdateModel.getCellPhone();
		if (cellPhone == null) {
			return "cellPhone is required";
		}
		cellPhone = cellPhone.trim();
		if (cellPhone.length() == 0) {
			return "cellPhone is required";
		}
		if (!cellPhone.matches("(07)[0-9]{9}")) {
			return "cellPhone contains invalid characters and shall be in this form 071234567";
		}
		
		String email = userInfoUpdateModel.getEmail();
		if (email == null) {
			return "email is required";
		}
		email = email.trim();
		if (email.length() == 0) {
			return "email is required";
		}
		if (!email.matches("[a-zA-Z0-9_\\.]+(\\@)[a-zA-Z0-9]+(\\.)[a-zA-Z]+")) {
			return "email format is invalid";
		}
		
		String city = userInfoUpdateModel.getCity();
		if (city == null) {
			return "city is required";
		}
		city = city.trim();
		if (city.length() == 0) {
			return "city is required";
		}		
		if (city.length() > 50) {
			return "city length exceeded 50 characters";
		}	
		
		String region = userInfoUpdateModel.getRegion();
		if (region == null) {
			return "region is required";
		}
		region = region.trim();
		if (region.length() == 0) {
			return "region is required";
		}		
		if (region.length() > 50) {
			return "region length exceeded 50 characters";
		}
		
		String buildingNumber = userInfoUpdateModel.getBuildingNumber();
		if (buildingNumber == null) {
			return "buildingNumber is required";
		}
		buildingNumber = buildingNumber.trim();
		if (buildingNumber.length() == 0) {
			return "buildingNumber is required";
		}		
		if (buildingNumber.length() > 50) {
			return "buildingNumber length exceeded 50 characters";
		}		
		
		String postalCode = userInfoUpdateModel.getPostalCode();
		if (postalCode == null) {
			return "postalCode is required";
		}
		postalCode = postalCode.trim();
		if (postalCode.length() == 0) {
			return "postalCode is required";
		}		
		if (postalCode.length() > 5) {
			return "postalCode length exceeded 5 characters";
		}		
		if (!postalCode.matches("[0-9]*")) {
			return "postalCode contains invalid characters and shall contain digits only";
		}
		
		return null;
	}
	
}
