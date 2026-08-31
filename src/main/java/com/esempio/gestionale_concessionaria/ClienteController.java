package com.esempio.gestionale_concessionaria;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/clienti")
public class ClienteController {

    private final ClienteRepository repository;

    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Cliente> getTutti() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Cliente getPerId(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente non trovato: id " + id));
    }

    @PostMapping
    public ResponseEntity<Cliente> crea(@Valid @RequestBody NuovoClienteRequest richiesta) {
        Cliente nuovo = new Cliente(richiesta.nome(), richiesta.email(), richiesta.telefono());
        Cliente salvato = repository.save(nuovo);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvato);
    }
}
