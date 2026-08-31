package com.esempio.gestionale_concessionaria;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class VeicoloControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getTuttiRestituisceListaVeicoli() throws Exception {
        mockMvc.perform(get("/api/veicoli"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getPerIdInesistenteRestituisce404() throws Exception {
        mockMvc.perform(get("/api/veicoli/999999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void postAutomobileCreaNuovoVeicolo() throws Exception {
        String corpoRichiesta = """
                {
                    "marca": "Fiat",
                    "modello": "Tipo",
                    "annoImmatricolazione": 2024,
                    "numeroPosti": 5
                }
                """;

        mockMvc.perform(post("/api/veicoli/automobili")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(corpoRichiesta))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.marca").value("Fiat"))
                .andExpect(jsonPath("$.tassaAnnuale").value(100.0));
    }
}
