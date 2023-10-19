package com.poc.interfaces.rest.error_handler.exceptions;

public class NoDataFoundException extends RuntimeException {

	public NoDataFoundException() {
		super("no data found");
	}	

}
