package com.esempio.gestionale_concessionaria;

import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class VeicoloService {

    private final VeicoloRepository veicoloRepository;

    public VeicoloService(VeicoloRepository veicoloRepository) {
        this.veicoloRepository = veicoloRepository;
    }

    public double calcolaTassaTotaleFlotta() {
        return veicoloRepository.findAll().stream()
                .mapToDouble(Veicolo::calcolaTassaAnnuale)
                .sum();
    }

    public String descrizioneVeicolo(Long id) {
        Veicolo veicolo = veicoloRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Veicolo non trovato: id " + id));
        return veicolo.getMarca() + " " + veicolo.getModello() + " " + veicolo.getAnnoImmatricolazione();
    }

    public Automobile registraNuovaAutomobile(String marca, String modello, int anno, int numeroPosti) {
        return veicoloRepository.save(new Automobile(marca, modello, anno, numeroPosti));
    }
}
