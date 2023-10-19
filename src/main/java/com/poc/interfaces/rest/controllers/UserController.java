package com.poc.interfaces.rest.controllers;

import static com.poc.interfaces.rest.utils.DateHelper.DATE_FORMAT;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poc.domain.UserService;
import com.poc.interfaces.rest.models.UserInfoCreateModel;
import com.poc.interfaces.rest.models.UserInfoReadModel;
import com.poc.interfaces.rest.models.UserInfoUpdateModel;
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
		
		validator.validate(userInfoCreateModel);	
		
		UserInfo userInfo = new UserInfo();
		userInfo.setName(userInfoCreateModel.getName());
		userInfo.setNationalId(userInfoCreateModel.getNationalId());		
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);		
		userInfo.setDateOfBirth(dateFormat.parse(userInfoCreateModel.getDateOfBirth()));
		userInfo.setCellPhone(userInfoCreateModel.getCellPhone());
		userInfo.setEmail(userInfoCreateModel.getEmail());
		userInfo.setMailingAddress(userInfoCreateModel.getMailingAddress());
		
		userService.createUser(userInfo);
		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "{nationalId}")
	public ResponseEntity<UserInfoReadModel> getUserByNationalId(@PathVariable String nationalId) {
			
		MasterAccount masterAccount = userService.getUser(nationalId);
		
		UserInfoReadModel userInfoReadModel = new UserInfoReadModel();
		userInfoReadModel.setName(masterAccount.getUserInfo().getName());
		
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		String dateOfBirth = dateFormat.format(masterAccount.getUserInfo().getDateOfBirth());
		userInfoReadModel.setDateOfBirth(dateOfBirth);
		userInfoReadModel.setIban(masterAccount.getIban());
		userInfoReadModel.setBalance(masterAccount.getBalance());
		userInfoReadModel.setCurrency(masterAccount.getCurrency().getCode());		
		userInfoReadModel.setNationalId(masterAccount.getUserInfo().getNationalId());
		userInfoReadModel.setCellPhone(masterAccount.getUserInfo().getCellPhone());
		userInfoReadModel.setEmail(masterAccount.getUserInfo().getEmail());
		userInfoReadModel.setMailingAddress(masterAccount.getUserInfo().getMailingAddress());
		
		return new ResponseEntity<UserInfoReadModel>(userInfoReadModel, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Object> updateUser(@RequestBody UserInfoUpdateModel userInfoUpdateModel) {

		validator.validate(userInfoUpdateModel);
		
		userService.updateUser(userInfoUpdateModel);
		
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "{nationalId}")
	public ResponseEntity<Object> removeUser(@PathVariable String nationalId) {
		
		userService.removeUser(nationalId);
		
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserInfoReadModel>> getUsers(@RequestParam int pageIndex) {
			
		List<MasterAccount> masterAccounts = userService.getUsers(pageIndex);
		
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		List<UserInfoReadModel> userInfoReadModels = new ArrayList<UserInfoReadModel>();
		for (int cursor = 0; cursor < masterAccounts.size(); cursor++) {
			MasterAccount currentElement = masterAccounts.get(cursor);
			
			UserInfoReadModel userInfoReadModel = new UserInfoReadModel();
			userInfoReadModel.setName(currentElement.getUserInfo().getName());
						
			String dateOfBirth = dateFormat.format(currentElement.getUserInfo().getDateOfBirth());
			userInfoReadModel.setDateOfBirth(dateOfBirth);
			userInfoReadModel.setIban(currentElement.getIban());
			userInfoReadModel.setBalance(currentElement.getBalance());
			userInfoReadModel.setCurrency(currentElement.getCurrency().getCode());		
			userInfoReadModel.setNationalId(currentElement.getUserInfo().getNationalId());
			userInfoReadModel.setCellPhone(currentElement.getUserInfo().getCellPhone());
			userInfoReadModel.setEmail(currentElement.getUserInfo().getEmail());
			userInfoReadModel.setMailingAddress(currentElement.getUserInfo().getMailingAddress());
			
			userInfoReadModels.add(userInfoReadModel);
		}		
		
		return new ResponseEntity<List<UserInfoReadModel>>(userInfoReadModels, HttpStatus.OK);
	}

}
