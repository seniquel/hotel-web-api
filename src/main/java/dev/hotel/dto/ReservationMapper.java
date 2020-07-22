package dev.hotel.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import dev.hotel.entite.Reservation;

@Mapper
public interface ReservationMapper {

	ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);
	//@Mapping(source = "client", target = "clientId")
    //ReservationDto reservationToReservationDto(Reservation reservation) {
	//@Mapping(source = "clientId", target = "clientId")
    //Reservation reservationDtoToReservation(ReservationDto reservationDto);
}
