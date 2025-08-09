package com.example.central.repository;

import com.example.central.model.Agricultor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgricultorRepository extends JpaRepository<Agricultor, Long> {
}
