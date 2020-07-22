package dev.hotel.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;
import dev.hotel.entite.Reservation;
import dev.hotel.repository.ChambreRepository;
import dev.hotel.repository.ClientRepository;
import dev.hotel.repository.ReservationRepository;

@Service
@Transactional
public class ReservationService {

	private ReservationRepository reservationRepository;
	private ClientRepository clientRepository;
	private ChambreRepository chambreRepository;

	public ReservationService(ReservationRepository reservationRepository, ClientRepository clientRepository, ChambreRepository chambreRepository) {
		this.reservationRepository = reservationRepository;
		this.clientRepository = clientRepository;
		this.chambreRepository = chambreRepository;
	}
	
	public List<Reservation> getListReservation(){
		return reservationRepository.findAll();
	}
	
	public boolean ClientExiste(UUID uuid) {
		return clientRepository.findById(uuid).isPresent();
	}
	
	public boolean ChambresExistent(List<UUID> chambres) {
		return chambres.stream().allMatch(id -> chambreRepository.findById(id).isPresent());
	}
	
	public List<UUID> getChambresIds(List<Chambre> chambres) {
		return chambres.stream().map(Chambre::getUuid).collect(Collectors.toList());
	}

	public Reservation creer(LocalDate dateDebut, LocalDate dateFin, UUID uuidClient, List<UUID> listeUuidChambres) {
		
		Reservation reservation = new Reservation(dateDebut, dateFin, 
				clientRepository.findById(uuidClient).get(), 
				chambreRepository.findAllById(listeUuidChambres));

		return reservationRepository.save(reservation);
	}

}
