package com.esempio.gestionale_concessionaria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AutomobileTest {

    private Automobile fiat;

    @BeforeEach
    void setUp() {
        fiat = new Automobile("Fiat", "Panda", 2020, 5);
    }

    @Test
    void calcolaTassaCorrettamente() {
        assertEquals(100.0, fiat.calcolaTassaAnnuale());
    }

    @Test
    void marcaCorretta() {
        assertEquals("Fiat", fiat.getMarca());
    }
}
