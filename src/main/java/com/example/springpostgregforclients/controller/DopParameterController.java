package com.example.springpostgregforclients.controller;

import com.example.springpostgregforclients.exception.NotFoundException;
import com.example.springpostgregforclients.model.DopParameter;
import com.example.springpostgregforclients.repo.ClientRepository;
import com.example.springpostgregforclients.repo.DopParameterRepository;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api
@RestController
@RequestMapping("/api")
public class DopParameterController {
    private final ClientRepository clientRepository;
    private final DopParameterRepository dopParameterRepository;
    final static Logger log = LogManager.getLogger(DopParameterController.class);

    public DopParameterController(DopParameterRepository dopParameterRepository, ClientRepository clientRepository) {
        this.dopParameterRepository = dopParameterRepository;
        this.clientRepository = clientRepository;
    }

    @GetMapping("/clients/{clientId}/parameters")
    public List getParametersByClientId(@PathVariable Long clientId) {
        if(!clientRepository.existsById(clientId)) {
            throw new NotFoundException("Client not found!");
        }
        return dopParameterRepository.findByClientId(clientId);
    }

    @PostMapping("/clients/{clientId}/parameters")
    public DopParameter addParameter(@PathVariable Long clientId,
                                      @Valid @RequestBody DopParameter parameter) {
        return clientRepository.findById(clientId)
                .map(client -> {
                    parameter.setClient(client);
                    return dopParameterRepository.save(parameter);
                }).orElseThrow(() -> new NotFoundException("Client not found!"));
    }

    @PutMapping("/clients/{clientId}/parameters/{paramId}")
    public DopParameter updateParameter(@PathVariable Long clientId,
                                       @PathVariable Long paramId,
                                       @Valid @RequestBody DopParameter parameterUpdated) {

        if(!clientRepository.existsById(clientId)) {
            throw new NotFoundException("Client not found!");
        }

        return dopParameterRepository.findById(paramId)
                .map(dopParam -> {
                    dopParam.setType(parameterUpdated.getType());
                    dopParam.setValue(parameterUpdated.getValue());
                    return dopParameterRepository.save(parameterUpdated);
                }).orElseThrow(() -> new NotFoundException("DopParameter not found!"));
    }

    @DeleteMapping("/clients/{clientId}/parameters/{paramId}")
    public String deleteAssignment(@PathVariable Long clientId,
                                   @PathVariable Long paramId) {

        if(!clientRepository.existsById(clientId)) {
            throw new NotFoundException("Client not found!");
        }

        return dopParameterRepository.findById(paramId)
                .map(dopParam -> {
                    dopParameterRepository.delete(dopParam);
                    return "Deleted Successfully!";
                }).orElseThrow(() -> new NotFoundException("Param not found!"));
    }
}
