package com.munizmiranda.helpdesk.repository;

import com.munizmiranda.helpdesk.model.Atendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AtendenteRepository extends JpaRepository<Atendente, Long> {
    Optional<Atendente> findByEmail(String email);
}