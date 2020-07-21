package dev.hotel.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.dto.ClientDto;
import dev.hotel.dto.CodeErreur;
import dev.hotel.dto.CreerClientDto;
import dev.hotel.dto.MessageErreurDto;
import dev.hotel.entite.Client;
import dev.hotel.exception.ClientException;
import dev.hotel.exception.ClientNotFoundException;
import dev.hotel.service.ClientService;

@RestController
@RequestMapping("clients")
public class ClientController {
	
	private ClientService service;
	//private ClientRepository repository;

	public ClientController( ClientService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<Client>> getClients(
			@RequestParam @PositiveOrZero Integer start,
			@RequestParam @Positive Integer size) {	
		return ResponseEntity.status(HttpStatus.OK)
				.body(service.lister((PageRequest.of(start,size))).toList());
	}

	@GetMapping("{uuid}")
	public ResponseEntity<Optional<Client>> getClientByUUID(@PathVariable UUID uuid) {
		Optional<Client> client = service.getClient(uuid);
		if(client.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(client);
		}
		else {
			throw new ClientNotFoundException(new MessageErreurDto(CodeErreur.VALIDATION, "Données Ko pour la récupération d'un client"));
		}

	}

	@PostMapping
	public ResponseEntity<?> postClient(@RequestBody @Valid CreerClientDto client, BindingResult result) {
		
		if (result.hasErrors()) {
			throw new ClientException(new MessageErreurDto(CodeErreur.VALIDATION, "Données Ko pour la création d'un client"));
		}
		
		Client clientCreer = service.creer(client.getNom(), client.getPrenoms());
		ClientDto clientDto = new ClientDto();
		clientDto.setUuid(clientCreer.getUuid());
		clientDto.setNom(clientCreer.getNom());
		clientDto.setPrenoms(clientCreer.getPrenoms());

		return ResponseEntity.ok(clientDto);
	}
}
