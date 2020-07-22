package dev.hotel.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;
import dev.hotel.entite.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
	
	@Query("select c from Reservation r join r.client c where c.id=?1")
	Client findClientByUuid(UUID uuidClient);
	
	@Query("select c from Reservation r join r.chambres c where c.id in ?1")
	List<Chambre> findChambresByUuid(List<UUID> listeUuidChambres);
}