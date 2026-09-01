package com.esempio.gestionale_concessionaria;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vendite")
public class VenditaController {

    private final VenditaService venditaService;

    public VenditaController(VenditaService venditaService) {
        this.venditaService = venditaService;
    }

    @PostMapping
    public ResponseEntity<VenditaResponse> registra(@Valid @RequestBody NuovaVenditaRequest richiesta) {
        Vendita vendita = venditaService.registraVendita(richiesta.veicoloId(), richiesta.clienteId(), richiesta.prezzo());
        return ResponseEntity.status(HttpStatus.CREATED).body(VenditaResponse.from(vendita));
    }
}
