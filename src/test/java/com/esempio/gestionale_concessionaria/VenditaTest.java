package com.esempio.gestionale_concessionaria;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class VenditaTest {
    @Test
    void prezzoNegativoLanciaEccezione() {
        Veicolo v = new Automobile("Fiat", "Panda", 2020, 5);
        Cliente c = new Cliente("Mario Rossi", "mario.rossi@example.com", "332345466");
        assertThrows(IllegalArgumentException.class, () -> new Vendita(v, c, LocalDate.now(), -100));
    }
}
