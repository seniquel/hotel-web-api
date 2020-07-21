package dev.hotel.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;

@Service
public class ClientService {

	private ClientRepository repository;

	public ClientService(ClientRepository repository) {
		this.repository = repository;
	}
	
	@Transactional
	public Client creer(String nom, String prenoms) {
	
		Client client = new Client(nom,prenoms);
		
		Client clientSauvegarde = this.repository.save(client);
		
		return clientSauvegarde;
	}
	
	public Page<Client> lister(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	public Optional<Client> getClient(UUID id){
		return repository.findByUuid(id);
	}
}
