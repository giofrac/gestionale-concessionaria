package com.esempio.gestionale_concessionaria;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
public class VenditaService {

    private final VenditaRepository venditaRepository;
    private final VeicoloRepository veicoloRepository;
    private final ClienteRepository clienteRepository;

    public VenditaService(VenditaRepository venditaRepository, VeicoloRepository veicoloRepository, ClienteRepository clienteRepository) {
        this.venditaRepository = venditaRepository;
        this.veicoloRepository = veicoloRepository;
        this.clienteRepository = clienteRepository;
    }

    public Vendita registraVendita(Long veicoloId, Long clienteId, double prezzo) {
        Veicolo veicolo = veicoloRepository.findById(veicoloId)
                .orElseThrow(() -> new NoSuchElementException("Veicolo non trovato: id " + veicoloId));
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new NoSuchElementException("Cliente non trovato: id " + clienteId));

        if (!venditaRepository.findByVeicoloId(veicoloId).isEmpty()) {
            throw new IllegalStateException("Veicolo già venduto: id " + veicoloId);
        }

        return venditaRepository.save(new Vendita(veicolo, cliente, LocalDate.now(), prezzo));
    }
}
