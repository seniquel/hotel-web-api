package dev.hotel.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreerReservationDto {

	/** dateDebut */
	@NotNull
	@JsonProperty("dateDebut")
	private LocalDate dateDebut;

	/** dateFin */
	@NotNull
	@JsonProperty("dateFin")
	private LocalDate dateFin;

	/** clientUuid */
	@NotNull
	@JsonProperty("clientId")
	private UUID clientId;

	@NotNull
	@JsonProperty("chambres")
	private List<UUID> chambres = new ArrayList<>();

	/**
	 * Getter
	 * 
	 * @return dateDebut
	 */
	public LocalDate getDateDebut() {
		return dateDebut;
	}

	/**
	 * Setter
	 * 
	 * @param dateDebut to set
	 */
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * Getter
	 * 
	 * @return dateFin
	 */
	public LocalDate getDateFin() {
		return dateFin;
	}

	/**
	 * Setter
	 * 
	 * @param dateFin to set
	 */
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * Getter
	 * 
	 * @return clientUuid
	 */
	public UUID getClientId() {
		return clientId;
	}

	/**
	 * Setter
	 * 
	 * @param clientUuid to set
	 */
	public void setClientId(UUID clientUuid) {
		this.clientId = clientUuid;
	}

	/**
	 * Getter
	 * 
	 * @return chambres
	 */
	public List<UUID> getChambres() {
		return chambres;
	}

	/**
	 * Setter
	 * 
	 * @param chambres to set
	 */
	public void setChambres(List<UUID> chambres) {
		this.chambres = chambres;
	}

}