//package dev.hotel.dto;
//
//import java.util.UUID;
//
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//
//import dev.hotel.entite.Client;
//import dev.hotel.entite.Reservation;
//import dev.hotel.service.ReservationService;
//
//@Mapper(uses = {ReservationService.class}) 
//public interface ReservationMapper {
//
//	ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);
//	
//	@Mapping(source = "client", target = "clientId")
//    ReservationDto reservationToReservationDto(Reservation reservation);
//	
//	@Mapping(source = "client", target = "clientId")
//	UUID clientToClientId(Client client);
////	{
////		ReservationDto dto = new ReservationDto();
////		dto.setDateDebut(reservation.getDateDebut());
////		dto.setDateFin(reservation.getDateFin());
////		dto.setClientId(reservation.getClient().getUuid());
////		dto.setChambres(service.getChambresIds(reservation.getChambres()));
////		return dto;
////	}
//	@Mapping(source = "clientId", target = "client")
//    Reservation reservationDtoToReservation(ReservationDto reservationDto);
//	
//	//Client clientIdToClient(UUID clientId);
//}
