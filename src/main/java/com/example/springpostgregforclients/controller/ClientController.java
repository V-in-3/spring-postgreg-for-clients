package com.example.springpostgregforclients.controller;

import com.example.springpostgregforclients.exception.NotFoundException;
import com.example.springpostgregforclients.repo.ClientRepository;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.springpostgregforclients.model.Client;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Api
@RestController

@RequestMapping("/api")
public class ClientController {

    private final ClientRepository clientRepository;
    final static Logger log = LogManager.getLogger(ClientController.class);

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClients() {
        log.debug("Getting data clients...");
        try {
            List<Client> clients = new ArrayList<>();
            clientRepository.findAll().forEach(clients::add);

            log.debug("Clients dataset is empty.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/clients/{id}")
    public Client getClientByID(@PathVariable Long id) {
        Optional<Client> optClient = clientRepository.findById(id);
        log.debug("Getting details client with id:" + id);
        if (optClient.isPresent()) {
            return optClient.get();
        } else {
            throw new NotFoundException("Client not found with id:" + id);
        }
    }

    @PostMapping("/clients")
    public Client createClient(@Valid @RequestBody Client client) {
        log.debug("Saving new client: " + client.getName());
        return clientRepository.save(client);
    }

    @PutMapping("/clients/{id}")
    public Client updateClient(@PathVariable Long id,
                               @Valid @RequestBody Client clientUpdated) {
        log.debug("Trying to change passport details for the client with id:" + id);
        return clientRepository.findById(id)
                .map(client -> {
                    client.setPassNum(clientUpdated.getPassNum());
                    client.setPassSer(clientUpdated.getPassSer());
                    return clientRepository.save(client);
                }).orElseThrow(() -> new NotFoundException("Client not found with id " + id));
    }

    @DeleteMapping("/clients/{id}")
    public String deleteClient(@PathVariable Long id) {
        return clientRepository.findById(id)
                .map(client -> {
                    clientRepository.delete(client);
                    return "Delete Successfully!";
                }).orElseThrow(() -> new NotFoundException("Client not found with id " + id));
    }
}
