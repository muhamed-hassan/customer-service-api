package com.poc.domain;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poc.interfaces.rest.models.UserInfoUpdateModel;
import com.poc.persistence.entities.Currency;
import com.poc.persistence.entities.IbanConfigs;
import com.poc.persistence.entities.MasterAccount;
import com.poc.persistence.entities.UserInfo;
import com.poc.persistence.repositories.CurrencyRepository;
import com.poc.persistence.repositories.IbanConfigsRepository;
import com.poc.persistence.repositories.MasterAccountRepository;
import com.poc.persistence.repositories.UserInfoRepository;

@Service
public class UserService {

	@Autowired
	private IbanConfigsRepository ibanConfigsRepository;
	
	@Autowired
	private MasterAccountRepository masterAccountRepository;
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private CurrencyRepository currencyRepository;
	
	@Transactional
	public void createUser(UserInfo userInfo) {
		
		userInfo = userInfoRepository.save(userInfo);
		
		Currency gbp = currencyRepository.findByCode("GBP");
		
		MasterAccount masterAccount = new MasterAccount();
		masterAccount.setUserInfo(userInfo);
		masterAccount.setBalance(0);
		masterAccount.setCurrency(gbp);
		
		Random random = new Random(System.currentTimeMillis());        
		long generatedNumber = Math.abs(random.nextLong());		        
		String generatedNumberString = generatedNumber + "";
		String accountNumber = generatedNumberString.substring(0, 8);
		masterAccount.setAccountNumber(accountNumber);
		
		masterAccountRepository.save(masterAccount);
	}
	
	public MasterAccount getUser(String nationalId) {
		
		IbanConfigs ibanConfigs = ibanConfigsRepository.findOne(1);
		
		MasterAccount masterAccount = masterAccountRepository.getByNationalId(nationalId);
		masterAccount.toIban(ibanConfigs);
		
		return masterAccount;
	}
	
	@Transactional
	public void updateUser(UserInfoUpdateModel userInfoUpdateModel) {
		
		UserInfo userInfo = userInfoRepository.findByNationalId(userInfoUpdateModel.getNationalId());
		
		userInfo.setCellPhone(userInfoUpdateModel.getCellPhone());
		userInfo.setEmail(userInfoUpdateModel.getEmail());
		userInfo.setMailingAddress(userInfoUpdateModel.getMailingAddress());
		
		userInfoRepository.save(userInfo);
	}
	
	@Transactional
	public void removeUser(String nationalId) {	
		
		MasterAccount masterAccount = masterAccountRepository.getByNationalId(nationalId);
		masterAccountRepository.delete(masterAccount);
	}
	
}
