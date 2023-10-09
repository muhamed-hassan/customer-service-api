package com.poc.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc.persistence.entities.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
	
	Currency findByCode(String code);

}
