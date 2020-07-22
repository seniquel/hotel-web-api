package dev.hotel.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import dev.hotel.entite.Client;

@Mapper
public interface ClientMapper {

	ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
	
    ClientDto clientToClientDto(Client client);
    Client clientDtoToClient(ClientDto clientDto);
}
