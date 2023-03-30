package com.poc.interfaces.rest.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poc.domain.UserService;
import com.poc.interfaces.rest.models.UserInfoCreateModel;
import com.poc.interfaces.rest.models.UserInfoReadModel;
import com.poc.interfaces.rest.models.UserInfoUpdateModel;
import com.poc.persistence.entities.Address;
import com.poc.persistence.entities.MasterAccount;
import com.poc.persistence.entities.UserInfo;

@RequestMapping("v1/users")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Validator validator; 
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> createUser(@RequestBody UserInfoCreateModel userInfoCreateModel) throws ParseException {
		
		String errorMessage = validator.validate(userInfoCreateModel);
		if (errorMessage != null) {			
			Map<String, String> error = new HashMap<String, String>(1);
			error.put("error", errorMessage);			
			return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
		}		
		
		UserInfo userInfo = new UserInfo();
		userInfo.setName(userInfoCreateModel.getName());
		userInfo.setNationalId(userInfoCreateModel.getNationalId());		
		DateFormat dateFormat = new SimpleDateFormat();		
		userInfo.setDateOfBirth(dateFormat.parse(userInfoCreateModel.getDateOfBirth()));
		userInfo.setCellPhone(userInfoCreateModel.getCellPhone());
		userInfo.setEmail(userInfoCreateModel.getEmail());
		Address address = new Address();
		address.setCity(userInfoCreateModel.getCity());
		address.setRegion(userInfoCreateModel.getRegion());
		address.setBuildingNumber(userInfoCreateModel.getBuildingNumber());
		address.setPostalCode(userInfoCreateModel.getPostalCode());
		userInfo.setAddress(address);
		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "{nationalId}")
	public ResponseEntity<UserInfoReadModel> getUserByNationalId(@PathVariable String nationalId) {
			
		MasterAccount masterAccount = userService.getUser(nationalId);
		
		UserInfoReadModel userInfoReadModel = new UserInfoReadModel();
		userInfoReadModel.setName(masterAccount.getUserInfo().getName());
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String dateOfBirth = dateFormat.format(masterAccount.getUserInfo().getDateOfBirth());
		userInfoReadModel.setDateOfBirth(dateOfBirth);
		userInfoReadModel.setIban(masterAccount.getIban());
		userInfoReadModel.setBalance(masterAccount.getBalance());
		userInfoReadModel.setCurrency(masterAccount.getCurrency().getCode());		
		userInfoReadModel.setNationalId(masterAccount.getUserInfo().getNationalId());
		userInfoReadModel.setCellPhone(masterAccount.getUserInfo().getCellPhone());
		userInfoReadModel.setEmail(masterAccount.getUserInfo().getEmail());
		userInfoReadModel.setCity(masterAccount.getUserInfo().getAddress().getCity());
		userInfoReadModel.setRegion(masterAccount.getUserInfo().getAddress().getRegion());
		userInfoReadModel.setBuildingNumber(masterAccount.getUserInfo().getAddress().getBuildingNumber());
		userInfoReadModel.setPostalCode(masterAccount.getUserInfo().getAddress().getPostalCode());
		
		return new ResponseEntity<UserInfoReadModel>(userInfoReadModel, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Object> updateUser(@RequestBody UserInfoUpdateModel userInfoUpdateModel) {

		String errorMessage = validator.validate(userInfoUpdateModel);
		if (errorMessage != null) {			
			Map<String, String> error = new HashMap<String, String>(1);
			error.put("error", errorMessage);			
			return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
		}
		
		userService.updateUser(userInfoUpdateModel);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "{nationalId}")
	public ResponseEntity<Object> removeUser(@PathVariable String nationalId) {
		userService.removeUser(nationalId);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

}
