package dev.hotel.exception;

import dev.hotel.dto.MessageErreurDto;

public class ChambreException extends RuntimeException {

	private MessageErreurDto messageErreur;

	public ChambreException(MessageErreurDto messageErreur) {
		super(messageErreur.getMessage());
		this.messageErreur = messageErreur;
	}

	public MessageErreurDto getMessageErreur() {
		return messageErreur;
	}
	
}
