package dev.hotel.exception;

import dev.hotel.dto.MessageErreurDto;

public class ClientNotFoundException extends ClientException {

	public ClientNotFoundException(MessageErreurDto messageErreur) {
		super(messageErreur);
	}

}
