package dev.hotel.exception;

import dev.hotel.dto.MessageErreurDto;

public class ReservationException extends RuntimeException {
	private MessageErreurDto messageErreur;

	public ReservationException(MessageErreurDto messageErreur) {
		super(messageErreur.getMessage());
		this.messageErreur = messageErreur;
	}

	public MessageErreurDto getMessageErreur() {
		return messageErreur;
	}
	
}
