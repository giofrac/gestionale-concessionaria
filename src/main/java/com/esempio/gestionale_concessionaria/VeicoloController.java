package com.esempio.gestionale_concessionaria;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/veicoli")
public class VeicoloController {

    private final VeicoloRepository repository;

    public VeicoloController(VeicoloRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<VeicoloResponse> getTutti() {
        return repository.findAll().stream().map(VeicoloResponse::from).toList();
    }

    @GetMapping("/{id}")
    public VeicoloResponse getPerId(@PathVariable Long id) {
        Veicolo veicolo = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Veicolo non trovato: id " + id));
        return VeicoloResponse.from(veicolo);
    }

    @PostMapping("/automobili")
    public ResponseEntity<VeicoloResponse> creaAutomobile(@Valid @RequestBody NuovaAutomobileRequest richiesta) {
        Automobile salvata = repository.save(new Automobile(richiesta.marca(), richiesta.modello(), richiesta.annoImmatricolazione(), richiesta.numeroPosti()));
        return ResponseEntity.status(HttpStatus.CREATED).body(VeicoloResponse.from(salvata));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}