package com.esempio.gestionale_concessionaria;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNome(String nome);
    List<Cliente> findByEmailContainingIgnoreCase(String frammento);
    Optional<Cliente> findByEmail(String email);
}
