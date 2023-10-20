package com.poc.interfaces.rest.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DateHelper {
	
	@Bean
	public DateFormat dateFormat() {
		return new SimpleDateFormat("dd-MM-yyyy");
	}

}
