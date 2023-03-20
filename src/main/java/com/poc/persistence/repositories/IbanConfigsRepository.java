package com.poc.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc.persistence.entities.IbanConfigs;

@Repository
public interface IbanConfigsRepository extends JpaRepository<IbanConfigs, Integer> {

}
