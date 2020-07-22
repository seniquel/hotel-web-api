package dev.hotel.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;
import dev.hotel.entite.Reservation;
import dev.hotel.repository.ReservationRepository;

@Service
public class ReservationService {

	private ReservationRepository repository;

	public ReservationService(ReservationRepository repository) {
		this.repository = repository;
	}
	
	public boolean ClientExisteByUuid(UUID uuid) {
		return repository.findClientByUuid(uuid) != null;
	}
	
	public boolean ChambresExistent(List<UUID> listeUuidChambres) {
		return repository.findChambresByUuid(listeUuidChambres) != null;
	}
	
	public Reservation creer(LocalDate dateDebut, LocalDate dateFin, UUID uuidClient, List<UUID> listeUuidChambres) {
		
		Client client = repository.findClientByUuid(uuidClient);
		List<Chambre> chambres= repository.findChambresByUuid(listeUuidChambres);
		Reservation reservation = new Reservation(dateDebut, dateFin, client, chambres);

		Reservation reservationSauvegarde = this.repository.save(reservation);
		return reservationSauvegarde;
	}

}
