package com.devsuperior.dscatalog.resource;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.dscatalog.resource.exceptions.StandardError;
import com.devsuperior.dscatalog.services.exceptions.ResoucerNotFoundException;


@ControllerAdvice
public class ResourceExecptionHandler {
	
	@ExceptionHandler(ResoucerNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResoucerNotFoundException e, HttpServletRequest request){
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("Resouce not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		
	}

}
