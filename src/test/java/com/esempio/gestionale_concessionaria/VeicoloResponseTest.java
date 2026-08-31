package com.esempio.gestionale_concessionaria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VeicoloResponseTest {

    @Test
    void autoElettricaProduceRispostaCorretta() {
        AutoElettrica tesla = new AutoElettrica("Tesla", "Model S", 2022, 500);
        VeicoloResponse risposta = VeicoloResponse.from(tesla);

        assertEquals("AutoElettrica", risposta.tipo());
        assertEquals(500.0, risposta.autonomiaKm());
    }
}
