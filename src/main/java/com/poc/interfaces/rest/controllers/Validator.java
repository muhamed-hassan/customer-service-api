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
		if (!name.matches("[a-zA-Z\\ ]{5,250}")) {
			return "name contains invalid characters and shall contain letters only with maximum of 250";
		}
		
		String nationalId = userInfoCreateModel.getNationalId();
		if (nationalId == null) {
			return "nationalId is required";
		}
		nationalId = nationalId.trim();
		if (!nationalId.matches("[0-9]{14}")) {
			return "nationalId contains invalid characters and shall contain 14 digits only";
		}
		
		String dateOfBirthStr = userInfoCreateModel.getDateOfBirth();
		if (dateOfBirthStr == null) {
			return "dateOfBirth is required";
		}
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");		
		Date dateOfBirth = dateFormat.parse(dateOfBirthStr);
		if (dateOfBirth == null) {
			return "dateOfBirth is wrongly formatted and shall be formatted as dd-MM-yyyy";
		}
		
		String cellPhone = userInfoCreateModel.getCellPhone();
		if (cellPhone == null) {
			return "cellPhone is required";
		}
		cellPhone = cellPhone.trim();
		if (!cellPhone.matches("(07)[0-9]{9}")) {
			return "cellPhone contains invalid characters and shall be in this form 07123456789";
		}
		
		String email = userInfoCreateModel.getEmail();
		if (email == null) {
			return "email is required";
		}
		email = email.trim();
		if (!email.matches("[a-zA-Z0-9_\\.]+(\\@)[a-zA-Z0-9]+(\\.)[a-zA-Z]+")) {
			return "email format is invalid";
		}
		
		String city = userInfoCreateModel.getCity();
		if (city == null) {
			return "city is required";
		}
		city = city.trim();
		if (!city.matches("[a-zA-Z\\ ]{3,50}")) {
			return "city contains invalid characters and shall contain letters only with maximum of 50";
		}	
		
		String region = userInfoCreateModel.getRegion();
		if (region == null) {
			return "region is required";
		}
		region = region.trim();
		if (!region.matches("[a-zA-Z\\ ]{5,50}")) {
			return "region contains invalid characters and shall contain letters only with maximum of 50";
		}		
		
		String buildingNumber = userInfoCreateModel.getBuildingNumber();
		if (buildingNumber == null) {
			return "buildingNumber is required";
		}
		buildingNumber = buildingNumber.trim();
		if (!buildingNumber.matches("[a-zA-Z0-9\\ ]{5,50}")) {
			return "buildingNumber contains invalid characters and shall contain alphanumeric characters only with maximum of 50";
		}		
		
		String postalCode = userInfoCreateModel.getPostalCode();
		if (postalCode == null) {
			return "postalCode is required";
		}
		postalCode = postalCode.trim();	
		if (!postalCode.matches("[a-zA-Z0-9]{5}")) {
			return "postalCode contains invalid characters and shall contain digits only with maximum of 5";
		}
		
		return null;
	}
	
	public String validate(UserInfoUpdateModel userInfoUpdateModel) {
		
		String nationalId = userInfoUpdateModel.getNationalId();
		if (nationalId == null) {
			return "nationalId is required";
		}
		nationalId = nationalId.trim();
		if (!nationalId.matches("[0-9]{14}")) {
			return "nationalId contains invalid characters and shall contain 14 digits only";
		}
		
		String cellPhone = userInfoUpdateModel.getCellPhone();
		if (cellPhone == null) {
			return "cellPhone is required";
		}
		cellPhone = cellPhone.trim();
		if (!cellPhone.matches("(07)[0-9]{9}")) {
			return "cellPhone contains invalid characters and shall be in this form 071234567";
		}
		
		String email = userInfoUpdateModel.getEmail();
		if (email == null) {
			return "email is required";
		}
		email = email.trim();
		if (!email.matches("[a-zA-Z0-9_\\.]+(\\@)[a-zA-Z0-9]+(\\.)[a-zA-Z]+")) {
			return "email format is invalid";
		}
		
		String city = userInfoUpdateModel.getCity();
		if (city == null) {
			return "city is required";
		}
		city = city.trim();
		if (!city.matches("[a-zA-Z\\ ]{3,50}")) {
			return "city contains invalid characters and shall contain letters only with maximum of 50";
		}	
		
		String region = userInfoUpdateModel.getRegion();
		if (region == null) {
			return "region is required";
		}
		region = region.trim();
		if (!region.matches("[a-zA-Z\\ ]{5,50}")) {
			return "region contains invalid characters and shall contain letters only with maximum of 50";
		}		
		
		String buildingNumber = userInfoUpdateModel.getBuildingNumber();
		if (buildingNumber == null) {
			return "buildingNumber is required";
		}
		buildingNumber = buildingNumber.trim();
		if (!buildingNumber.matches("[a-zA-Z0-9\\ ]{5,50}")) {
			return "buildingNumber contains invalid characters and shall contain alphanumeric characters only with maximum of 50";
		}		
		
		String postalCode = userInfoUpdateModel.getPostalCode();
		if (postalCode == null) {
			return "postalCode is required";
		}
		postalCode = postalCode.trim();	
		if (!postalCode.matches("[a-zA-Z0-9]{5}")) {
			return "postalCode contains invalid characters and shall contain digits only with maximum of 5";
		}
		
		return null;
	}
	
}
