package com.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class APINotFound extends RuntimeException {

	public APINotFound(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	
	

}
