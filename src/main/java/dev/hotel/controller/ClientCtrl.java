package dev.hotel.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;

@RestController
@RequestMapping("clients")
public class ClientCtrl {
	
	@Autowired
	private ClientRepository repository;
	
	public ClientCtrl(ClientRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public ResponseEntity<List<Client>> getClients(
			@RequestParam Integer start,
			@RequestParam Integer size) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(repository.findAll(PageRequest.of(start,size)).toList());
	}
	
//	@GetMapping("{id}")
//	public ResponseEntity<Client> getClientByUUID(@PathVariable UUID id) {
//		repository.findByUUID
//		
//	}
}