package com.esempio.gestionale_concessionaria;

import java.time.LocalDate;

public record Vendita(Veicolo veicolo, LocalDate dataVendita, double prezzo) {
    public Vendita {
        if (prezzo < 0) {
            throw new IllegalArgumentException("Il prezzo non può essere negativo");
        }
    }
}