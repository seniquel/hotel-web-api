package dev.hotel.exception;

import dev.hotel.dto.MessageErreurDto;

public class ChambreNotFoundException extends ChambreException {

	public ChambreNotFoundException(MessageErreurDto messageErreur) {
		super(messageErreur);
	}

}
