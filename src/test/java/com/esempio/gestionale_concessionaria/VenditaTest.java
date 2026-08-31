package com.esempio.gestionale_concessionaria;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class VenditaTest {
    @Test
    void prezzoNegativoLanciaEccezione() {
        Veicolo v = new Automobile("Fiat", "Panda", 2020, 5);
        assertThrows(IllegalArgumentException.class, () -> new Vendita(v, LocalDate.now(), -100));
    }
}
