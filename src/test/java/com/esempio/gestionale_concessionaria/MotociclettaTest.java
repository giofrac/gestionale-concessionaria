package com.esempio.gestionale_concessionaria;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MotociclettaTest {
    @ParameterizedTest
    @CsvSource({
            "100, 25",
            "120, 26",
            "140, 27"
    })
    void calcolaTassaPerVarieCilindrate(int cilindrate, double tassaAttesa) {
        Motocicletta m = new Motocicletta("Yamaha", "YH1", 2020, cilindrate);
        assertEquals(tassaAttesa, m.calcolaTassaAnnuale());
    }
}
