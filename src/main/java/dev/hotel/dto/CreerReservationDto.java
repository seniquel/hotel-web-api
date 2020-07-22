package dev.hotel.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

public class CreerReservationDto {
	
	@NotNull
	@NotBlank
    private LocalDate dateDebut;
	@NotNull
	@NotBlank
    private LocalDate dateFin;
	@NotNull
	@NotBlank
    private UUID clientId;
	@NotNull
	@NotBlank
    private List<UUID> chambres;
    
	/** Getter
	 * @return the dateDebut
	 */
	public LocalDate getDateDebut() {
		return dateDebut;
	}
	/** Setter
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}
	/** Getter
	 * @return the dateFin
	 */
	public LocalDate getDateFin() {
		return dateFin;
	}
	/** Setter
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}
	/** Getter
	 * @return the clientId
	 */
	public UUID getClientId() {
		return clientId;
	}
	/** Setter
	 * @param clientId the clientId to set
	 */
	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}
	/** Getter
	 * @return the chambres
	 */
	public List<UUID> getChambres() {
		return chambres;
	}
	/** Setter
	 * @param chambres the chambres to set
	 */
	public void setChambres(List<UUID> chambres) {
		this.chambres = chambres;
	}
    
}
