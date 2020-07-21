package dev.hotel.exception;

import dev.hotel.dto.MessageErreurDto;

public class ClientException extends RuntimeException  {
	
	private MessageErreurDto messageErreur;

	public ClientException(MessageErreurDto messageErreur) {
		super(messageErreur.getMessage());
		this.messageErreur = messageErreur;
	}

	public MessageErreurDto getMessageErreur() {
		return messageErreur;
	}
	
	
}
