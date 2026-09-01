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
        String token = jwtService.generaToken("mario");
        this.mockMvc.perform(post("/api/veicoli/automobili")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "marca": "Fiat",
                                    "modello": "Tipo",
                                    "annoImmatricolazione": 2024,
                                    "numeroPosti": 5
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.marca").value("Fiat"))
                .andExpect(jsonPath("$.tassaAnnuale").value(100.0));
    }

    @Autowired
    private JwtService jwtService;

    @Test
    void adminReportSenzaTokenRestituisce401() throws Exception {
        mockMvc.perform(get("/api/admin/report"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void adminReportConTokenUtenteRestituisce403() throws Exception {
        String token = jwtService.generaToken("mario");

        mockMvc.perform(get("/api/admin/report")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isForbidden());
    }

    @Test
    void adminReportConTokenAdminRestituisce200() throws Exception {
        String token = jwtService.generaToken("admin");

        mockMvc.perform(get("/api/admin/report")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string("Dati riservati"));
    }
}
