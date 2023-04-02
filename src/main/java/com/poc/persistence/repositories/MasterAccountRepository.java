package com.poc.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.poc.persistence.entities.MasterAccount;

@Repository
public interface MasterAccountRepository extends JpaRepository<MasterAccount, Integer> {
	
	@Query("SELECT masterAccount "
			+ "FROM MasterAccount masterAccount "
			+ "WHERE masterAccount.userInfo.nationalId = :nationalId")
	public MasterAccount getByNationalId(@Param("nationalId") String nationalId);
	
}
