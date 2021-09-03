package com.example.springpostgregforclients.repo;

import com.example.springpostgregforclients.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long>{}
