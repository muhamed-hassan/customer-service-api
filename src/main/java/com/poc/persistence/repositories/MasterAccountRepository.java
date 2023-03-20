package com.poc.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc.persistence.entities.MasterAccount;

@Repository
public interface MasterAccountRepository extends JpaRepository<MasterAccount, Integer> {

}
