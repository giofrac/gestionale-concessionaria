package com.esempio.gestionale_concessionaria;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VenditaRepository extends JpaRepository<Vendita, Long> {
    List<Vendita> findByVeicoloId(Long veicoloId);
}