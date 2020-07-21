package dev.hotel.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such Client")
public class ClientNotFoundException extends ClientException {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	public ClientNotFoundException(String message) {
		super(message);
	}
}
