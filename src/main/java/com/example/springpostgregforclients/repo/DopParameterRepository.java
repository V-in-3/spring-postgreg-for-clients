package com.example.springpostgregforclients.repo;

import com.example.springpostgregforclients.model.DopParameter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DopParameterRepository extends JpaRepository<DopParameter, Long> {
    List<DopParameter> findByClientId(Long clientId);
}
