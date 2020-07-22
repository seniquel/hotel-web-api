package dev.hotel.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import dev.hotel.entite.Client;
import dev.hotel.service.ClientService;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	ClientService service;

	@Test
	public void getClientsTest() throws Exception {

		List<Client> clients = new ArrayList<>();

		Client client = new Client("Odd", "Ross");
		client.setUuid(UUID.fromString("dcf129f1-a2f9-47dc-8265-1d844244b192"));
		clients.add(client);

		client = new Client("Don", "Duck");
		client.setUuid(UUID.fromString("f9a18170-9605-4fe6-83c8-d03a53e08bfe"));
		clients.add(client);

		client = new Client("Etienne", "Joly");
		client.setUuid(UUID.fromString("91defde0-9ad3-4e4f-886b-f5f06f601a0d"));
		clients.add(client);

		Page<Client> page = new PageImpl<>(clients);
		Mockito.when(service.lister(PageRequest.of(0,3))).thenReturn(page);

		mockMvc.perform(MockMvcRequestBuilders.get("/clients?start=0&size=3"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].uuid").value("dcf129f1-a2f9-47dc-8265-1d844244b192"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].uuid").value("f9a18170-9605-4fe6-83c8-d03a53e08bfe"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[2].uuid").value("91defde0-9ad3-4e4f-886b-f5f06f601a0d"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].nom").value("Odd"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].nom").value("Don"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[2].nom").value("Etienne"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].prenoms").value("Ross"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].prenoms").value("Duck"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[2].prenoms").value("Joly"));
	}

	@Test
	public void getClientByUUIDValide() throws Exception {

		UUID idValide = UUID.fromString("dcf129f1-a2f9-47dc-8265-1d844244b192");
		Client client = new Client("Odd", "Ross");
		client.setUuid(idValide);

		Optional<Client> opt = Optional.of(client);

		Mockito.when(service.getClient(idValide)).thenReturn(opt);

		mockMvc.perform(MockMvcRequestBuilders.get("/clients/"+idValide))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.uuid").value(idValide.toString()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Odd"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.prenoms").value("Ross"));
	}

	@Test
	public void getClientByUUIDInvalide() throws Exception {

		UUID idInvalide = UUID.fromString("dcf129f1-a2f9-47dc-8265-1d844244b191");
		Optional<Client> opt = Optional.empty();
		
		Mockito.when(service.getClient(idInvalide)).thenReturn(opt);

		mockMvc.perform(MockMvcRequestBuilders.get("/clients/"+idInvalide))
		.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	public void postClientValide() throws Exception {
		Client clientValide = new Client("ALBERT","Dimitri");
		UUID uuid = UUID.randomUUID();
		clientValide.setUuid(uuid);
		String jsonValide = "{\"nom\" : \"ALBERT\",\"prenoms\" : \"Dimitri\"}";
		Mockito.when(service.creer(clientValide.getNom(), clientValide.getPrenoms())).thenReturn(clientValide);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/clients").contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON).content(jsonValide))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.uuid").value(uuid.toString()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("ALBERT"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.prenoms").value("Dimitri"));
	}
	
	@Test
	public void postClientNomInvalide() throws Exception {
		String jsonInvalide = "{\"nom\" : \"a\",\"prenoms\" : \"a\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/clients").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(jsonInvalide))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void postClientVide() throws Exception {
		String jsonInvalide = "{}";
		mockMvc.perform(MockMvcRequestBuilders.post("/clients").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(jsonInvalide))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
}
