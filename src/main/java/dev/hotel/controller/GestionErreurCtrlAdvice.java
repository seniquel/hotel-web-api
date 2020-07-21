package dev.hotel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.hotel.dto.MessageErreurDto;
import dev.hotel.exception.ClientException;
import dev.hotel.exception.ClientNotFoundException;

@ControllerAdvice
public class GestionErreurCtrlAdvice {
	
	@ExceptionHandler(ClientException.class)
	public ResponseEntity<MessageErreurDto> quandClientException(ClientException ex) {
		return ResponseEntity.badRequest().body(ex.getMessageErreur());	
	}
	
	@ExceptionHandler(ClientNotFoundException.class)
	public ResponseEntity<MessageErreurDto> quandClientNotFoundException(ClientException ex) {
		return ((BodyBuilder) ResponseEntity.notFound()).body(ex.getMessageErreur());	
	}
}
