package com.poc.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poc.persistence.entities.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
	
	@Query("DELETE FROM UserInfo userInfo WHERE userInfo.nationalId = :nationalId")
	public void deleteByNationalId(String nationalId);
	
	public UserInfo findByNationalId(String nationalId);
	
}
