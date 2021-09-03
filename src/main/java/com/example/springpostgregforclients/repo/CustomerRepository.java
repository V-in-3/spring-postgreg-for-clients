package com.example.springpostgregforclients.repo;

import com.example.springpostgregforclients.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository  extends JpaRepository<Customer, Long> {}
