package dev.hotel.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.dto.CodeErreur;
import dev.hotel.dto.CreerReservationDto;
import dev.hotel.dto.MessageErreurDto;
import dev.hotel.dto.ReservationDto;
import dev.hotel.dto.ReservationMapper;
import dev.hotel.entite.Reservation;
import dev.hotel.exception.ChambreNotFoundException;
import dev.hotel.exception.ClientNotFoundException;
import dev.hotel.exception.ReservationException;
import dev.hotel.service.ReservationService;

@RestController
@RequestMapping("reservations")
public class ReservationController {

	private ReservationService service;
	
	@PostMapping
	public ResponseEntity<?> postReservation(@RequestBody @Valid CreerReservationDto reservation, BindingResult result) {
		if (result.hasErrors()) {
			throw new ReservationException(new MessageErreurDto(CodeErreur.VALIDATION, "Données invalides pour la création d'une réservation"));
		}
		if(!service.ClientExisteByUuid(reservation.getClientId())) {
			throw new ClientNotFoundException(new MessageErreurDto(CodeErreur.VALIDATION, "Il n'existe pas de client avec cet UUID"));
		}
		if(!service.ChambresExistent(reservation.getChambres())) {
			throw new ChambreNotFoundException(new MessageErreurDto(CodeErreur.VALIDATION, "Il n'existe pas de client avec cet UUID"));
		}
		Reservation reservationCreee = service.creer(reservation.getDateDebut(), reservation.getDateFin(), reservation.getClientId(), reservation.getChambres());
		//ReservationDto reservationDto = ReservationMapper.INSTANCE.reservationToReservationDto(reservationCreee);
		ReservationDto reservationDto = null;
		return ResponseEntity.ok(reservationDto);
	}
}
