package com.daytonatec.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class AbstractController {
	
	@ExceptionHandler
	public ResponseEntity<String> globalEceptionHandller(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	} 
}
